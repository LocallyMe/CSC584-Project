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

public class Warehouse implements Serializable {
    private int warehouseId;
    private String name;
    private String address;

    public Warehouse() {}

    public int getWarehouseId() { 
        return warehouseId; }
    
    public void setWarehouseId(int warehouseId) { 
        this.warehouseId = warehouseId; }

    public String getName() { 
        return name; }
    
    public void setName(String name) { 
        this.name = name; }

    public String getAddress() { 
        return address; }
    
    public void setAddress(String address) { 
        this.address = address; }
}
