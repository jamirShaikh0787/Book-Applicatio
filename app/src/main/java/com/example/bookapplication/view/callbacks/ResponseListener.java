package com.example.bookapplication.view.callbacks;


import com.example.bookapplication.data.localdatabase.entity.BookEntity;

public interface ResponseListener {

    void onSuccess(BookEntity data);
    void onFailure();
}
