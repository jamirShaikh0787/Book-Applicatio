package com.example.bookapplicatio.di.builder;


import com.example.bookapplicatio.view.fragment.BookDetailFragment;
import com.example.bookapplicatio.view.fragment.BookListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This builder provides android injector service to fragments
 */
@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract BookListFragment contributeBookListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract BookDetailFragment contributeBookDetailFragment();

}
