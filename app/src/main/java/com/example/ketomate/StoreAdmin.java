package com.example.ketomate;

public class StoreAdmin {
    private String item_id;
    private String name;
    private String ingredients ;
    private Integer weight;
    private Integer calories;
    private Float cost;


    public StoreAdmin(String item_id, String name, String ingredients, Integer weight, Integer calories, Float cost) {
        this.item_id = item_id;
        this.name = name;
        this.ingredients = ingredients;
        this.weight = weight;
        this.calories = calories;
        this.cost = cost;
    }

    public StoreAdmin() {

    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
}
