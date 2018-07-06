package com.udacity.sandwichclub.di.modules;

import android.app.Application;
import android.content.Context;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.di.qualifier.ApplicationContext;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    static Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public String[] data(@ApplicationContext Context context){
        return context.getResources().getStringArray(R.array.sandwich_names);
    }
}
