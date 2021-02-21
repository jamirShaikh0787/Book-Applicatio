package com.example.bookapplicatio.view.callbacks;


import com.example.bookapplicatio.data.localdatabase.entity.BookEntity;

public interface ResponseListener {

    void onSuccess(BookEntity data);
    void onFailure(String message);
}
