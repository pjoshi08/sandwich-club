package com.udacity.sandwichclub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import javax.inject.Inject;

public class MyAdapter extends ArrayAdapter<String> {

    @Inject
    public MyAdapter(@NonNull Context context, int resource, @NonNull String[] data) {
        super(context, resource, data);
    }
}
