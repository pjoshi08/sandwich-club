package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;
    private int position;
    private TextView originTV, aliasesTV, descriptionTV, ingredientsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        originTV = findViewById(R.id.origin_tv);
        aliasesTV = findViewById(R.id.aliases_tv);
        descriptionTV = findViewById(R.id.description_tv);
        ingredientsTV = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        try {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
            if (position == DEFAULT_POSITION) {
                // EXTRA_POSITION not found in intent
                closeOnError();
                return;
            }
            String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
            String json = sandwiches[position];
            sandwich = JsonUtils.parseSandwichJson(json);

        }catch (Exception e){
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        //Toast.makeText(this, "Data to be populated", Toast.LENGTH_SHORT).show();
        originTV.setText(sandwich.getPlaceOfOrigin());
        populateListArrays(sandwich.getAlsoKnownAs(), aliasesTV);
        descriptionTV.setText(sandwich.getDescription());
        populateListArrays(sandwich.getIngredients(), ingredientsTV);
    }

    private void populateListArrays(List<String> list, TextView tv){
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < list.size(); i++)
            builder.append('\u2022').append(' ').append(list.get(i)).append('\n');

        tv.setText(builder.toString());
    }
}
