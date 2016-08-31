package com.example.jefri.myapplication;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 4/3/2016.
 * Organization "The Tuna Group" - Kerala
 */
public class UserDetails {
    private String Name;
    private String City;
    private String Country;

    public UserDetails(String name,String city,String country){
        this.Name = name;
        this.City = city;
        this.Country = country;
    }

    public String getName() {
        return Name;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
