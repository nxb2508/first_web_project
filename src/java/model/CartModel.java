/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.InventoryDAO;
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
                        String[] tuple = cookie.split("&");
                        int inventoryId = Integer.parseInt(tuple[0]);
                        int itemQuantity = Integer.parseInt(tuple[1]);
                        InventoryModel inventory = new InventoryDAO().getInventoryById(inventoryId);
                        ItemModel item = new ItemModel(inventory, itemQuantity, inventory.getProduct().getPrice());
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
    private ItemModel getItemByInventoryId(int inventoryId) {
        for (ItemModel item : items) {
            if (item.getInventory().getId() == inventoryId) {
                return item;
            }
        }
        return null;
    }

    //lay so luong item co trong gio hang theo productId
    public int getItemQuantityByInventoryId(int inventoryId) {
        return getItemByInventoryId(inventoryId).getQuantity();
    }

    //them item vao gio hang
    public void addItem(ItemModel item) {
        if (getItemByInventoryId(item.getInventory().getId()) != null) {
            ItemModel existedItem = getItemByInventoryId(item.getInventory().getId());
            existedItem.setQuantity(existedItem.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }
    }

    //xoa item khoi gio hang
    public void removeItem(int inventoryId) {
        if (getItemByInventoryId(inventoryId) != null) {
            items.remove(getItemByInventoryId(inventoryId));
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
        if (!items.isEmpty()) {
            cookieTxt = items.get(0).getInventory().getId() + "&" + items.get(0).getQuantity();
            for (int i = 1; i < items.size(); i++) {
                cookieTxt += "#" + items.get(i).getInventory().getId() + "&" + items.get(i).getQuantity();
            }
        }
        return cookieTxt;
    }

}
