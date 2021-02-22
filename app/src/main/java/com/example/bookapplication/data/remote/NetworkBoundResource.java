package com.example.bookapplication.data.remote;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.bookapplication.BookApp;
import com.example.bookapplication.R;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * This class act as the decider to cache the response/ fetch from the service always
 */
public abstract class NetworkBoundResource<T, V> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        result.setValue(Resource.loading(null));

        // Always load the data from DB intially so that we have
        LiveData<T> dbSource = loadFromDb();

        // Fetch the data from network and add it to the resource
        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);
            if (shouldFetch()) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    if(null != newData)
                        result.setValue(Resource.success(newData)) ;
                });
            }
        });
    }

    /**
     * This method fetches the data from remoted service and save it to local db
     * @param dbSource - Database source
     */
    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));
        createCall().enqueue(new Callback<V>() {
            @Override
            public void onResponse(@NonNull Call<V> call, @NonNull Response<V> response) {
                result.removeSource(dbSource);
                new TaskRunner().executeAsync(response.body(),call);
                //saveResultAndReInit(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<V> call, @NonNull Throwable t) {
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(getCustomErrorMessage(t), newData)));
            }
        });
    }

    private String getCustomErrorMessage(Throwable error){

        if (error instanceof SocketTimeoutException) {
            return BookApp.getAppContext().getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return  BookApp.getAppContext().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
             return  BookApp.getAppContext().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return BookApp.getAppContext().getString(R.string.unknownError);
        }

    }

    @WorkerThread
    protected abstract void saveCallResult(V item);

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Call<V> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }

    public class TaskRunner {
        private final Executor executor = Executors.newSingleThreadExecutor(); // change according to your requirements
        private final Handler handler = new Handler(Looper.getMainLooper());



        public <R> void executeAsync(V callable, Call<V> callback) {
            executor.execute(() -> {
                saveCallResult(callable);
                handler.post(() -> {
                    result.addSource(loadFromDb(), newData -> {
                        if (null != newData)
                            result.setValue(Resource.success(newData));
                    });
                });

            });
        }
    }


}
