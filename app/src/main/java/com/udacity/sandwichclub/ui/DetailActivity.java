package com.udacity.sandwichclub.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;
import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;

    /* Binding Views with ButterKnife */
    @BindView(R.id.origin_tv) TextView originTV;
    @BindView(R.id.aliases_tv) TextView aliasesTV;
    @BindView(R.id.description_tv) TextView descriptionTV;
    @BindView(R.id.ingredients_tv) TextView ingredientsTV;
    @BindView(R.id.image_iv) ImageView ingredientsIv;

    /* Binding resources with ButterKnife */
    @BindString(R.string.parcelable_key) String parceableKey;
    @BindArray(R.array.sandwich_details) String[] sandwiches;
    @BindString(R.string.detail_error_message) String detailErrorMsg;
    @BindString(R.string.new_line_character) String newLineChar;
    @BindString(R.string.bullet) String bullet;
    @BindDrawable(R.drawable.loading) Drawable loadingPlaceholder;
    @BindDrawable(R.drawable.error_image2) Drawable imageError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if(savedInstanceState != null){
            sandwich = savedInstanceState.getParcelable(parceableKey);
            populateUI();
            return;
        }

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
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, detailErrorMsg, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        loadImageWithPicasso();

        setTitle(sandwich.getMainName());

        /* Handle for no data */
        if(sandwich.getPlaceOfOrigin().equals("")){
            originTV.setText(detailErrorMsg);
        } else originTV.setText(sandwich.getPlaceOfOrigin());
        if(sandwich.getAlsoKnownAs().size() == 0){
            aliasesTV.setText(detailErrorMsg);
        }else populateListArrays(sandwich.getAlsoKnownAs(), aliasesTV);
        descriptionTV.setText(sandwich.getDescription());
        populateListArrays(sandwich.getIngredients(), ingredientsTV);
    }

    /* Helper method to populate List<String> into respective TextView */
    private void populateListArrays(List<String> list, TextView tv){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            if(i == list.size()-1)
                builder.append(bullet)
                    .append(list.get(i));
            else builder.append(bullet)
                    .append(list.get(i))
                    .append(newLineChar);
        }

        if(tv == ingredientsTV) builder.append(newLineChar);

        tv.setText(builder.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(parceableKey, sandwich);
    }

    private void loadImageWithPicasso(){
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(loadingPlaceholder)
                .error(imageError)
                .into(ingredientsIv);
    }
}
