package com.example.housingsocietyapp.Model.LocalModel;

public class UserSettings {

    private String display_name;
    private String display_photo;

    public UserSettings(String name, String displayName, String display_photo) {
        this.display_name = display_name;
        this.display_photo = display_photo;
    }


    public UserSettings() {
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDisplay_photo() {
        return display_photo;
    }

    public void setDisplay_photo(String display_photo) {
        this.display_photo = display_photo;
    }

}
