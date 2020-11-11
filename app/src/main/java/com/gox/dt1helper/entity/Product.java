package com.gox.dt1helper.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Product {

    private String productName;
    private String brand;
    private float sugars;
    private float sugarValue;
    private float sugar100g;
    private float carbohydrates;
    private float carbohydratesValue;
    private float carbohydrates100g;

    public Product(JSONObject json) throws JSONException {
        String nameFr = json.getString("product_name_fr");
        if(nameFr != null && !nameFr.isEmpty()){
            this.productName = nameFr;
        } else {
            String name = json.getString("product_name");
            this.productName = name;
        }
        this.brand = json.getString("brands");
        JSONObject nutriments = json.getJSONObject("nutriments");
        this.sugars = getFloatValue(nutriments.getDouble("sugars"));
        this.sugarValue = getFloatValue(nutriments.getDouble("sugars_value"));
        this.sugar100g = getFloatValue(nutriments.getDouble("sugars_100g"));
        this.carbohydrates = getFloatValue(nutriments.getDouble("carbohydrates"));
        this.carbohydratesValue = getFloatValue(nutriments.getDouble("carbohydrates_value"));
        this.carbohydrates100g = getFloatValue(nutriments.getDouble("carbohydrates_100g"));
    }

    private float getFloatValue(Double value){
        return BigDecimal.valueOf(value).floatValue();
    }

    @Override
    public String toString() {
        return productName + " (" + brand + ")\n" +
                "carbohydrates: " + carbohydrates + '\n' +
                "carbohydrates value: " + carbohydratesValue + '\n' +
                "carbohydrates 100g: " + carbohydrates100g + '\n' +
                "sugars: " + sugars + '\n' +
                "sugar value: " + sugarValue + '\n' +
                "sugar 100g: " + sugar100g;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getSugars() {
        return sugars;
    }

    public void setSugars(float sugars) {
        this.sugars = sugars;
    }

    public float getSugarValue() {
        return sugarValue;
    }

    public void setSugarValue(float sugarValue) {
        this.sugarValue = sugarValue;
    }

    public float getSugar100g() {
        return sugar100g;
    }

    public void setSugar100g(float sugar100g) {
        this.sugar100g = sugar100g;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public float getCarbohydratesValue() {
        return carbohydratesValue;
    }

    public void setCarbohydratesValue(float carbohydratesValue) {
        this.carbohydratesValue = carbohydratesValue;
    }

    public float getCarbohydrates100g() {
        return carbohydrates100g;
    }

    public void setCarbohydrates100g(float carbohydrates100g) {
        this.carbohydrates100g = carbohydrates100g;
    }
}
