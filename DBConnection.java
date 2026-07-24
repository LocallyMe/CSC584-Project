/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @AZRI
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Supplier;
import util.DBConnection;

public class SupplierDAO {

    // READ: Fetch all suppliers to populate the Add Product dropdown menu
    public List<Supplier> getAllSuppliers() {
        List<Supplier> supplierList = new ArrayList<>();
        String query = "SELECT * FROM Supplier";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setSupplierId(rs.getInt("supplier_id"));
                s.setSupplierName(rs.getString("supplier_name"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                supplierList.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplierList;
    }

    // CREATE: Add a new supplier (optional, for Admin use)
    public boolean addSupplier(Supplier supplier) {
        boolean isSuccess = false;
        String query = "INSERT INTO Supplier (supplier_name, phone, email) VALUES (?, ?, ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());
            
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
