package com.company;
//+
public final class Adress {
    final String cityName;//1
    final int zipCode;//2
    final String streetName;
    final int buildingNumber;
    final char buildingLetter;
    final int apartmentNumber;
    public String getCityName(){
        return cityName;
    }
    public String getStreetName(){
        return streetName;
    }
    public int getZipCode(){
        return zipCode;
    }
    public int getBuildingNumber(){
        return buildingNumber;
    }
    public int getApartmentNumber(){
        return apartmentNumber;
    }
    public char getBuildingLetter() {
        return buildingLetter;
    }
    public Adress(String cityName, int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartmentNumber){
    this.cityName = cityName;
    this.zipCode = zipCode;
    this.streetName = streetName;
    this.buildingNumber = buildingNumber;
    this.buildingLetter = buildingLetter;
    this.apartmentNumber = apartmentNumber;
    }
}

