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

public class Supplier implements Serializable {
    private int supplierId;
    private String supplierName;
    private String phone;
    private String email;

    public Supplier() {}

    public int getSupplierId() { 
        return supplierId; }
    
    public void setSupplierId(int supplierId) { 
        this.supplierId = supplierId; }

    public String getSupplierName() { 
        return supplierName; }
    
    public void setSupplierName(String supplierName) { 
        this.supplierName = supplierName; }

    public String getPhone() { 
        return phone; }
    
    public void setPhone(String phone) { 
        this.phone = phone; }

    public String getEmail() { 
        return email; }
    
    public void setEmail(String email) { 
        this.email = email; }
}
