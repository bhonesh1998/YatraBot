package com.example.bhonesh.bot;

/**
 * Created by upesh on 19/3/18.
 */

public class position {

    String name;
    String lat;
    String lang;
    String rating;
    String types;
    String photo_ref;
    String width;

    public position(String name, String lat, String lang, String rating, String types, String photo_ref,String width) {
        this.name = name;
        this.lat = lat;
        this.lang = lang;
        this.rating = rating;
        this.types = types;
        this.photo_ref = photo_ref;
        this.width = width;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setPhoto_ref(String photo_ref) {
        this.photo_ref = photo_ref;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getName() {

        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLang() {
        return lang;
    }

    public String getRating() {
        return rating;
    }

    public String getTypes() {
        return types;
    }

    public String getPhoto_ref() {
        return photo_ref;
    }

    public String getWidth() {
        return width;
    }
}
