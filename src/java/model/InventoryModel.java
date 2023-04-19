/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bach
 */
public class InventoryModel {
    private int id;
    private ProductModel product;
    private SizeModel size;
    private int quantity;
    private int solds;

    public InventoryModel() {
    }

    public InventoryModel(int id, ProductModel product, SizeModel size, int quantity, int solds) {
        this.id = id;
        this.product = product;
        this.size = size;
        this.quantity = quantity;
        this.solds = solds;
    }

    public int getSolds() {
        return solds;
    }

    public void setSolds(int solds) {
        this.solds = solds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public SizeModel getSize() {
        return size;
    }

    public void setSize(SizeModel size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
