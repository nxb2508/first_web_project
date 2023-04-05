/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.ProductDAO;
import java.util.*;

/**
 *
 * @author Bach
 */
public class CartModel {

    private List<ItemModel> items;

    public CartModel() {
        items = new ArrayList<>();
    }

    public CartModel(String cookieTxt) {
        items = new ArrayList<>();
        try {
            if (cookieTxt != null && cookieTxt.length() > 0) {
                String cookies[] = cookieTxt.split("#");
                for (String cookie : cookies) {
                    if (cookie != null && !cookie.equals("null&null")) {
                        String[] keyValue = cookie.split("&");
                        int productId = Integer.parseInt(keyValue[0]);
                        ProductModel product = new ProductDAO().getProductById(productId);
                        int itemQuantity = Integer.parseInt(keyValue[1]);
                        ItemModel item = new ItemModel(product, itemQuantity, product.getPrice());
                        addItem(item);
                    }
                }
            }
        } catch (NumberFormatException e) {

        }
    }

    //lay ra danh sach san pham co trong gio hang
    public List<ItemModel> getItems() {
        return items;
    }

    //lay item trong gio hang theo productId
    private ItemModel getItemByProductId(int productId) {
        for (ItemModel item : items) {
            if (item.getProduct().getId() == productId) {
                return item;
            }
        }
        return null;
    }

    //lay so luong item co trong gio hang theo productId
    public int getItemQuantityByProductId(int productId) {
        return getItemByProductId(productId).getQuantity();
    }

    //them item vao gio hang
    public void addItem(ItemModel item) {
        if (getItemByProductId(item.getProduct().getId()) != null) {
            ItemModel existedItem = getItemByProductId(item.getProduct().getId());
            existedItem.setQuantity(existedItem.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    //xoa item khoi gio hang
    public void removeItem(int productId) {
        if (getItemByProductId(productId) != null) {
            items.remove(getItemByProductId(productId));
        }
    }

    //lay tong tien cua gio hang
    public int getTotalMoney() {
        int totalMoney = 0;
        for (ItemModel item : items) {
            totalMoney += (item.getQuantity() * item.getPrice());
        }
        return totalMoney;
    }
    
    //lay tong so san pham
    public int getTotalItems() {
        int totalItems = 0;
        for (ItemModel item : items) {
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    //tra ve cookieTxt moi
    public String getCookieTxt() {
        String cookieTxt = "";
        if (items.size() > 0) {
            cookieTxt = items.get(0).getProduct().getId() + "&" + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                cookieTxt += "#" + items.get(i).getProduct().getId() + "&" + items.get(i).getQuantity();
            }
        }
        return cookieTxt;
    }

}
