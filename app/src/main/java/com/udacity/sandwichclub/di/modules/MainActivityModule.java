package com.udacity.sandwichclub.di.modules;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import com.udacity.sandwichclub.adapter.MyAdapter;
import com.udacity.sandwichclub.di.scope.PerActivity;
import com.udacity.sandwichclub.ui.DetailActivity;
import com.udacity.sandwichclub.ui.MainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    public MyAdapter adapter(MainActivity activity, @LayoutRes int resource, String[] data){
        return new MyAdapter(activity, resource, data);
    }

    @Provides
    public Intent intent(MainActivity mainActivity, Class<DetailActivity> detailActivityClass){
        return new Intent(mainActivity, detailActivityClass);
    }

    @Provides
    public int resource(){
        return android.R.layout.simple_list_item_1;
    }

    @Provides
    Class<DetailActivity> detailActivityClass(){
        return DetailActivity.class;
    }

}
