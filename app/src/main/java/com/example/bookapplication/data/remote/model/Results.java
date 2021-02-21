
package com.example.bookapplication.data.remote.model;

import com.example.bookapplication.data.localdatabase.entity.BookEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class Results {

    @SerializedName("list_name")
    @Expose
    private String listName;
    @SerializedName("list_name_encoded")
    @Expose
    private String listNameEncoded;
    @SerializedName("bestsellers_date")
    @Expose
    private String bestsellersDate;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("published_date_description")
    @Expose
    private String publishedDateDescription;
    @SerializedName("next_published_date")
    @Expose
    private String nextPublishedDate;
    @SerializedName("previous_published_date")
    @Expose
    private String previousPublishedDate;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("normal_list_ends_at")
    @Expose
    private Long normalListEndsAt;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("books")
    @Expose
    private List<BookEntity> books = null;
    @SerializedName("corrections")
    @Expose
    private List<Object> corrections = null;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListNameEncoded() {
        return listNameEncoded;
    }

    public void setListNameEncoded(String listNameEncoded) {
        this.listNameEncoded = listNameEncoded;
    }

    public String getBestsellersDate() {
        return bestsellersDate;
    }

    public void setBestsellersDate(String bestsellersDate) {
        this.bestsellersDate = bestsellersDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublishedDateDescription() {
        return publishedDateDescription;
    }

    public void setPublishedDateDescription(String publishedDateDescription) {
        this.publishedDateDescription = publishedDateDescription;
    }

    public String getNextPublishedDate() {
        return nextPublishedDate;
    }

    public void setNextPublishedDate(String nextPublishedDate) {
        this.nextPublishedDate = nextPublishedDate;
    }

    public String getPreviousPublishedDate() {
        return previousPublishedDate;
    }

    public void setPreviousPublishedDate(String previousPublishedDate) {
        this.previousPublishedDate = previousPublishedDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getNormalListEndsAt() {
        return normalListEndsAt;
    }

    public void setNormalListEndsAt(Long normalListEndsAt) {
        this.normalListEndsAt = normalListEndsAt;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public List<Object> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<Object> corrections) {
        this.corrections = corrections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Results)) return false;
        Results results = (Results) o;
        return Objects.equals(getListName(), results.getListName()) &&
                Objects.equals(getListNameEncoded(), results.getListNameEncoded()) &&
                Objects.equals(getBestsellersDate(), results.getBestsellersDate()) &&
                Objects.equals(getPublishedDate(), results.getPublishedDate()) &&
                Objects.equals(getPublishedDateDescription(), results.getPublishedDateDescription()) &&
                Objects.equals(getNextPublishedDate(), results.getNextPublishedDate()) &&
                Objects.equals(getPreviousPublishedDate(), results.getPreviousPublishedDate()) &&
                Objects.equals(getDisplayName(), results.getDisplayName()) &&
                Objects.equals(getNormalListEndsAt(), results.getNormalListEndsAt()) &&
                Objects.equals(getUpdated(), results.getUpdated()) &&
                Objects.equals(getBooks(), results.getBooks()) &&
                Objects.equals(getCorrections(), results.getCorrections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListName(), getListNameEncoded(), getBestsellersDate(), getPublishedDate(), getPublishedDateDescription(), getNextPublishedDate(), getPreviousPublishedDate(), getDisplayName(), getNormalListEndsAt(), getUpdated(), getBooks(), getCorrections());
    }

    @Override
    public String toString() {
        return "Results{" +
                "listName='" + listName + '\'' +
                ", listNameEncoded='" + listNameEncoded + '\'' +
                ", bestsellersDate='" + bestsellersDate + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", publishedDateDescription='" + publishedDateDescription + '\'' +
                ", nextPublishedDate='" + nextPublishedDate + '\'' +
                ", previousPublishedDate='" + previousPublishedDate + '\'' +
                ", displayName='" + displayName + '\'' +
                ", normalListEndsAt=" + normalListEndsAt +
                ", updated='" + updated + '\'' +
                ", books=" + books +
                ", corrections=" + corrections +
                '}';
    }
}
