package com.example.bookapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookapplication.data.localdatabase.entity.BookEntity;
import com.example.bookapplication.databinding.ItemBookBinding;
import com.example.bookapplication.view.base.BaseAdapter;
import com.example.bookapplication.view.callbacks.BookListCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the Book list recyclerview adapter

 */
public class BookListAdapter extends BaseAdapter<BookListAdapter.ArticleViewHolder, BookEntity>
implements Filterable{

    private List<BookEntity> articleEntities;

    private List<BookEntity> articleEntitiesFiltered;

    private final BookListCallback bookListCallback;

    public BookListAdapter(@NonNull BookListCallback bookListCallback) {
        articleEntities = new ArrayList<>();
        articleEntitiesFiltered = new ArrayList<>();
        this.bookListCallback = bookListCallback;
    }

    @Override
    public void setData(List<BookEntity> entities) {
        this.articleEntities = entities;
        this.articleEntitiesFiltered = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ArticleViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, bookListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int i) {
        viewHolder.onBind(articleEntitiesFiltered.get(i));
    }

    @Override
    public int getItemCount() {
        return articleEntitiesFiltered.size();
    }

    @Override
    public Filter getFilter() {
         return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    articleEntitiesFiltered = articleEntities;
                } else {
                    List<BookEntity> filteredList = new ArrayList<>();
                    for (BookEntity row : articleEntities) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getPublisher().toLowerCase().contains(charString.toLowerCase())
                                || row.getDescription().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    articleEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = articleEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articleEntitiesFiltered = (ArrayList<BookEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private static ArticleViewHolder create(LayoutInflater inflater, ViewGroup parent, BookListCallback callback) {
            ItemBookBinding itemMovieListBinding = ItemBookBinding.inflate(inflater, parent, false);
            return new ArticleViewHolder(itemMovieListBinding, callback);
        }

        final ItemBookBinding binding;

        private ArticleViewHolder(ItemBookBinding binding, BookListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onBookClicked(binding.getBook()));
        }

        private void onBind(BookEntity bookEntity) {
            binding.setBook(bookEntity);
            binding.executePendingBindings();
        }
    }
}
