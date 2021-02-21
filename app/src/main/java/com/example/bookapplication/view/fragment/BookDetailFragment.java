package com.example.bookapplication.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.bookapplication.R;
import com.example.bookapplication.databinding.FragmentBookDetailsBinding;
import com.example.bookapplication.utils.Constants;
import com.example.bookapplication.view.base.BaseFragment;
import com.example.bookapplication.viewmodel.BookDetailsViewModel;


/**
 * The book list fragment which is responsible for showing the book details

 */
public class BookDetailFragment extends BaseFragment<BookDetailsViewModel, FragmentBookDetailsBinding> {
    @Override
    protected Class<BookDetailsViewModel> getViewModel() {
        return BookDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_book_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(null != args) {
            viewModel.setUrl(args.getString(Constants.BUNDLE_KEY_BOOK));
            viewModel.loadArticleDetails();
        }
        viewModel.getArticleEntityMutableLiveData().observe(getViewLifecycleOwner(), articleEntity -> {
            if(null != articleEntity && null != args) {
                dataBinding.loadingProgress.setVisibility(View.GONE);
            }
        });

        viewModel.getErrorMessageRecieved().observe(this, message ->{
            dataBinding.loadingProgress.setVisibility(View.GONE);
        });
    }
}
