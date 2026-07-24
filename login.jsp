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
import java.sql.Timestamp;

public class StockTransaction implements Serializable {
    private int transactionId;
    private int productId;
    private String type;
    private Timestamp transactionDate; 
    private int quantity;

    public StockTransaction() {}

    public int getTransactionId() { 
        return transactionId; }
    
    public void setTransactionId(int transactionId) { 
        this.transactionId = transactionId; }

    public int getProductId() { 
        return productId; }
    
    public void setProductId(int productId) { 
        this.productId = productId; }

    public String getType() { 
        return type; }
    
    public void setType(String type) { 
        this.type = type; }

    public Timestamp getTransactionDate() { 
        return transactionDate; }
    
    public void setTransactionDate(Timestamp transactionDate) { 
        this.transactionDate = transactionDate; }

    public int getQuantity() { 
        return quantity; }
    
    public void setQuantity(int quantity) { 
        this.quantity = quantity; }
}
