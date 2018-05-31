package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        /* Sandwich JSON Object keys */
        final String NAME = "name";
        final String IMAGE = "image";
        final String MAINNAME = "mainName";
        final String ORIGIN = "placeOfOrigin";
        final String ALIASES = "alsoKnownAs";
        final String DESCRIPTION = "description";
        final String INGREDIENTS = "ingredients";

        /* Parsing JSON Object into separate variables */
        JSONObject sandwichJson = new JSONObject(json);
        JSONObject nameJson = sandwichJson.getJSONObject(NAME);
        String mainName = nameJson.getString(MAINNAME);
        JSONArray alsoKnownAs = nameJson.getJSONArray(ALIASES);
        List<String> alsoKnownAsList = jsonArraytoList(alsoKnownAs);
        String origin = sandwichJson.getString(ORIGIN);
        String description = sandwichJson.getString(DESCRIPTION);
        String image = sandwichJson.getString(IMAGE);
        JSONArray ingredients = sandwichJson.getJSONArray(INGREDIENTS);
        List<String> ingredientsList = jsonArraytoList(ingredients);

        return new Sandwich(mainName, alsoKnownAsList, origin, description, image, ingredientsList);
    }

    /* Helper method to return List<String> for JSONArray */
    private static List<String> jsonArraytoList(JSONArray jsonArray) throws JSONException {
        List<String> listData = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++)
                listData.add(jsonArray.getString(i));
        }
        return listData;
    }
}
