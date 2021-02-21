package com.example.bookapplication.data.remote.repository;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;


import com.example.bookapplication.data.localdatabase.dao.BookDao;
import com.example.bookapplication.data.localdatabase.entity.BookEntity;
import com.example.bookapplication.data.remote.ApiService;
import com.example.bookapplication.data.remote.NetworkBoundResource;
import com.example.bookapplication.data.remote.Resource;
import com.example.bookapplication.data.remote.model.BookResponse;
import com.example.bookapplication.view.callbacks.ResponseListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

/**
 * The book repository which has access to local and remote data fetching services
 */
public class BookRepository {

    private final BookDao bookDao;
    private final ApiService apiService;

    @Inject
    BookRepository(BookDao dao, ApiService service) {
        this.bookDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the popular books from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return List of books
     */
    public LiveData<Resource<List<BookEntity>>> loadBooks() {
        return new NetworkBoundResource<List<BookEntity>, BookResponse>() {

            @Override
            protected void saveCallResult(BookResponse item) {
                if(null != item)
                    bookDao.saveBooks(item.getResults().getBooks());
            }

            @NonNull
            @Override
            protected LiveData<List<BookEntity>> loadFromDb() {
                return bookDao.loadBooks();
            }

            @NonNull
            @Override
            protected Call<BookResponse> createCall() {
                return apiService.loadBooks();
            }
        }.getAsLiveData();
    }


    /**
     * This method fetches the HTML comntent from the url and parses it and fills the model
     * @param url url to be fetched
     * @param responseListener callback
     */
    @SuppressLint("CheckResult")
    public void loadArticleDetails(String url, ResponseListener responseListener) {
        BookEntity bookDetails = new BookEntity();
        Observable.fromCallable(() -> {
            Document document = Jsoup.connect(url).get();
            bookDetails.setTitle(document.title());
            bookDetails.setContent(document.select("p").text());
            return false;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> responseListener.onSuccess(bookDetails),
                 (error -> responseListener.onFailure(error.getMessage())));

    }

}
