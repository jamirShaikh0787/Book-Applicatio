package com.example.bookapplication.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bookapplication.R;
import com.example.bookapplication.data.localdatabase.entity.BookEntity;
import com.example.bookapplication.data.remote.Status;
import com.example.bookapplication.databinding.FragmentListBooksBinding;
import com.example.bookapplication.utils.Constants;
import com.example.bookapplication.utils.FragmentUtils;
import com.example.bookapplication.view.adapter.BookListAdapter;
import com.example.bookapplication.view.base.BaseFragment;
import com.example.bookapplication.view.callbacks.BookListCallback;
import com.example.bookapplication.viewmodel.BookListViewModel;


/**
 * The book list fragment which will list the popular articles

 */
public class BookListFragment extends BaseFragment<BookListViewModel, FragmentListBooksBinding> implements BookListCallback {

    public static BookListFragment newInstance() {
        Bundle args = new Bundle();
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<BookListViewModel> getViewModel() {
        return BookListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_books;
    }

    @Override
    public void onBookClicked(BookEntity bookEntity) {
        if(null != getActivity()){
            Bundle args = new Bundle();
            args.putString(Constants.BUNDLE_KEY_BOOK, bookEntity.getBookImage());
            BookDetailFragment detailFragment = new BookDetailFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new BookListAdapter(this));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel.getBooks()
                .observe(getViewLifecycleOwner(), listResource -> {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.loginProgress.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

    }
}
