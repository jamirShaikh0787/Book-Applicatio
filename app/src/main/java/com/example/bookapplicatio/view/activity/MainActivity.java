package com.example.bookapplicatio.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.bookapplicatio.R;
import com.example.bookapplicatio.databinding.ActivityMainBinding;
import com.example.bookapplicatio.utils.FragmentUtils;
import com.example.bookapplicatio.view.base.BaseActivity;
import com.example.bookapplicatio.view.fragment.BookListFragment;

import static com.example.bookapplicatio.utils.FragmentUtils.TRANSITION_NONE;


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
