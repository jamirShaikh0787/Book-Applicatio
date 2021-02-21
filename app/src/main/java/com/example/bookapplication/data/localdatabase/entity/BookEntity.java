package com.example.bookapplication.data.localdatabase.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "books")
public class BookEntity {

    @PrimaryKey
    @SerializedName("primary_isbn10")
    private long primaryIsbn10;

    @SerializedName("title")
    private String title;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("description")
    private String description;

    @SerializedName("book_image")
    private String bookImage;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPrimaryIsbn10() {
        return primaryIsbn10;
    }

    public void setPrimaryIsbn10(long primaryIsbn10) {
        this.primaryIsbn10 = primaryIsbn10;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

}
