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
import model.Category;
import util.DBConnection;

public class CategoryDAO {

    // READ: Fetch all categories to populate the Add Product dropdown menu
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM Category";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
                categoryList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    // CREATE: Add a new category (optional, for Admin use)
    public boolean addCategory(Category category) {
        boolean isSuccess = false;
        String query = "INSERT INTO Category (category_name, description) VALUES (?, ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
