package com.example.bookapplicatio.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bookapplicatio.data.localdatabase.entity.BookEntity;
import com.example.bookapplicatio.data.remote.repository.BookRepository;
import com.example.bookapplicatio.utils.SingleLiveEvent;
import com.example.bookapplicatio.view.callbacks.ResponseListener;

import javax.inject.Inject;

/**
 * ArticleDetails view model
 * <p>

 */

public class BookDetailsViewModel extends ViewModel {

    private String url;

    private BookRepository bookRepository;

    private MutableLiveData<BookEntity> articleEntityMutableLiveData = new MutableLiveData<>();

    private SingleLiveEvent<Void> errorMessageRecieved = new SingleLiveEvent<>();

    public MutableLiveData<BookEntity> getArticleEntityMutableLiveData() {
        return articleEntityMutableLiveData;
    }

    public void setArticleEntityMutableLiveData(MutableLiveData<BookEntity> articleEntityMutableLiveData) {
        this.articleEntityMutableLiveData = articleEntityMutableLiveData;
    }

    public SingleLiveEvent<Void> getErrorMessageRecieved() {
        return errorMessageRecieved;
    }

    public void setErrorMessageRecieved(SingleLiveEvent<Void> errorMessageRecieved) {
        this.errorMessageRecieved = errorMessageRecieved;
    }

    @Inject
    BookDetailsViewModel(BookRepository artRepository) {
        this.bookRepository = artRepository;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void loadArticleDetails(){

        if(null != bookRepository) {
            bookRepository.loadArticleDetails(url, new ResponseListener() {
                @Override
                public void onSuccess(BookEntity data) {
                    articleEntityMutableLiveData.setValue(data);
                }

                @Override
                public void onFailure(String message) {
                    // Send event to UI to show thw error
                    errorMessageRecieved.call();
                }
            });
        }
    }
}
