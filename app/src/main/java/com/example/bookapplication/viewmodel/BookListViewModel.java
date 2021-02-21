package com.example.bookapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.bookapplication.data.localdatabase.entity.BookEntity;
import com.example.bookapplication.data.remote.Resource;
import com.example.bookapplication.data.remote.repository.BookRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Book List view model
 * <p>

 */
public class BookListViewModel extends ViewModel {
    private final LiveData<Resource<List<BookEntity>>> popularArticles;

    @Inject
    public BookListViewModel(BookRepository bookRepository) {
        popularArticles = bookRepository.loadBooks();
    }

    public LiveData<Resource<List<BookEntity>>> getBooks() {
        return popularArticles;
    }
}
