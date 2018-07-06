package com.udacity.sandwichclub.di.components;

import android.app.Application;
import com.udacity.sandwichclub.MyApp;
import com.udacity.sandwichclub.di.modules.AppModule;
import com.udacity.sandwichclub.di.modules.InjectorsModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Singleton
@Component(modules = { AndroidInjectionModule.class, AppModule.class, InjectorsModule.class})
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Application application);
}