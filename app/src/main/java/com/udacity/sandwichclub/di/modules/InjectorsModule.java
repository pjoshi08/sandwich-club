package com.udacity.sandwichclub.di.modules;

import com.udacity.sandwichclub.di.scope.PerActivity;
import com.udacity.sandwichclub.ui.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class InjectorsModule {
    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    @PerActivity
    abstract MainActivity mainActivity();
}
