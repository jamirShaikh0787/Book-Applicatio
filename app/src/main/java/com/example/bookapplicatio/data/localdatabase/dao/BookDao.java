package com.example.bookapplicatio.data.localdatabase.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.bookapplicatio.data.localdatabase.entity.BookEntity;

import java.util.List;


@Dao
public interface BookDao {
    @Query("SELECT * FROM books")
    LiveData<List<BookEntity>> loadBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveBooks(List<BookEntity> bookEntities);

}
