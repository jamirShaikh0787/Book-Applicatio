package com.example.bookapplicatio.data.localdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bookapplicatio.data.localdatabase.dao.BookDao;
import com.example.bookapplicatio.data.localdatabase.entity.BookEntity;


@Database(entities = {BookEntity.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
}