/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author Bach
 */
public class ProductModel implements Comparable<ProductModel>{
    private int id;
    private CategoryModel category;
    private String name, description;
    private int price;
    private List<GaleryModel> galeries;

    public ProductModel() {
    }

    public ProductModel(int id, CategoryModel category, String name, String description, int price, List<GaleryModel> galeries) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.galeries = galeries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<GaleryModel> getGaleries() {
        return galeries;
    }

    public void setGaleries(List<GaleryModel> galeries) {
        this.galeries = galeries;
    }

    

    @Override
    public int compareTo(ProductModel o) {
        return this.price - o.price;
    }
    
    
}
