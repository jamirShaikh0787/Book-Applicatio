package com.example.bookapplication.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.bookapplication.R;
import com.example.bookapplication.databinding.ActivityMainBinding;
import com.example.bookapplication.utils.FragmentUtils;
import com.example.bookapplication.view.base.BaseActivity;
import com.example.bookapplication.view.fragment.BookListFragment;

import static com.example.bookapplication.utils.FragmentUtils.TRANSITION_NONE;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, BookListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

}
