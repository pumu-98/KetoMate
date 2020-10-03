package com.example.ketomate;

public class StoreAdmin {
    private String item_id;
    private String name;
    private String ingredients ;
//    private   Integer weight;
//    private Integer calories;
//    private Integer cost;
    private   String weight;
    private String calories;
    private String cost;




//    public StoreAdmin(String item_id, String name, String ingredients,   Integer weight, Integer calories, Integer cost) {
//        this.item_id = item_id;
//        this.name = name;
//        this.ingredients = ingredients;
//        this.weight = weight;
//        this.calories = calories;
//        this.cost = cost;
//    }


    public StoreAdmin(String item_id, String name, String ingredients, String weight, String calories, String cost) {
        this.item_id = item_id;
        this.name = name;
        this.ingredients = ingredients;
        this.weight = weight;
        this.calories = calories;
        this.cost = cost;
    }

    public StoreAdmin() {

    }

//    public String getItem_id() {
//        return item_id;
//    }
//
//    public void setItem_id(String item_id) {
//        this.item_id = item_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(String ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public Integer getWeight() {
//        return weight;
//    }
//
//    public void setWeight( Integer weight) {
//        this.weight = weight;
//    }
//
//    public Integer getCalories() {
//        return calories;
//    }
//
//    public void setCalories(Integer calories) {
//        this.calories = calories;
//    }
//
//    public int getCost() {
//        return cost;
//    }
//
//    public void setCost(Integer cost) {
//        this.cost = cost;
//    }


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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
