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

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;
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
            int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
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
        /* Handle for no data */
        if(sandwich.getPlaceOfOrigin().equals("")){
            originTV.setText(getResources().getString(R.string.detail_error_message));
        } else originTV.setText(sandwich.getPlaceOfOrigin());
        if(sandwich.getAlsoKnownAs().size() == 0){
            aliasesTV.setText(getResources().getString(R.string.detail_error_message));
            aliasesTV.append(getResources().getString(R.string.new_line_character));
        }else populateListArrays(sandwich.getAlsoKnownAs(), aliasesTV);
        descriptionTV.setText(sandwich.getDescription());
        populateListArrays(sandwich.getIngredients(), ingredientsTV);
    }

    /* Helper method to populate List<String> into respective TextView */
    private void populateListArrays(List<String> list, TextView tv){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < list.size(); i++)
            builder.append(getResources().getString(R.string.bullet))
                    .append(list.get(i))
                    .append(getResources().getString(R.string.new_line_character));

        tv.setText(builder.toString());
    }
}
