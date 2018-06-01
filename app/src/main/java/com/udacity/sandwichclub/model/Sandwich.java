package com.udacity.sandwichclub.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Sandwich implements Parcelable{

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * Parcelable constructor for use in serialization
     */
    private Sandwich(Parcel in) {
        this.mainName = in.readString();
        this.alsoKnownAs = in.createStringArrayList();
        this.placeOfOrigin = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.ingredients = in.createStringArrayList();
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mainName);
        parcel.writeStringList(this.alsoKnownAs);
        parcel.writeString(this.placeOfOrigin);
        parcel.writeString(this.description);
        parcel.writeString(this.image);
        parcel.writeList(this.ingredients);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Sandwich createFromParcel(Parcel in){
            return new Sandwich(in);
        }

        public Sandwich[] newArray(int size){
            return new Sandwich[size];
        }
    };
}
