package com.example.bookapplication.data.remote;


import com.example.bookapplication.data.remote.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * The APIService interface which will contain the semantics of all the REST calls.
 */
public interface ApiService {

    @GET("svc/books/v3/lists/current/hardcover-fiction.json")
    Call<BookResponse> loadBooks();
}
