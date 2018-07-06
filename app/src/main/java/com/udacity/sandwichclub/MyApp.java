package com.udacity.sandwichclub;

import android.app.Activity;
import android.app.Application;

import com.udacity.sandwichclub.di.components.DaggerAppComponent;
import com.udacity.sandwichclub.di.modules.AppModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApp extends Application implements HasActivityInjector {

    //private AppComponent appComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        /*appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();*/

        //DaggerMyAppComponent.create().inject(this);
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

    }

    /*public AppComponent getAppComponent(){
        return appComponent;
    }*/

    @Override
        public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
