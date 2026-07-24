/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @AZRI
 */

import java.io.Serializable;

public class InventoryStock implements Serializable {
    private int inventoryId;
    private int quantityHand;
    private int warehouseId;
    private int productId;

    public InventoryStock() {}

    public int getInventoryId() { 
        return inventoryId; }
    
    public void setInventoryId(int inventoryId) { 
        this.inventoryId = inventoryId; }

    public int getQuantityHand() { 
        return quantityHand; }
    
    public void setQuantityHand(int quantityHand) { 
        this.quantityHand = quantityHand; }

    public int getWarehouseId() { 
        return warehouseId; }
    
    public void setWarehouseId(int warehouseId) { 
        this.warehouseId = warehouseId; }

    public int getProductId() { 
        return productId; }
    
    public void setProductId(int productId) { 
        this.productId = productId; }
}
