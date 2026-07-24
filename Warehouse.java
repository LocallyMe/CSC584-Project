package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import util.DBConnection;

public class ProductDAO {

    public boolean addProduct(Product product) {
        boolean isSuccess = false;
        String query = "INSERT INTO Product (product_name, product_price, category_id, supplier_id) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getSupplierId());
            
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setProductPrice(rs.getDouble("product_price"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setSupplierId(rs.getInt("supplier_id"));
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getProductsByPage(int limit, int offset) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product LIMIT ? OFFSET ?";
        try (Connection conn = DBConnection.createConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setProductName(rs.getString("product_name"));
                    p.setProductPrice(rs.getDouble("product_price"));
                    p.setCategoryId(rs.getInt("category_id"));
                    p.setSupplierId(rs.getInt("supplier_id"));
                    productList.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public int getTotalProductCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Product";
        try (Connection conn = DBConnection.createConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Product getProductById(int productId) {
        Product p = null;
        String query = "SELECT * FROM Product WHERE product_id = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Product();
                    p.setProductId(rs.getInt("product_id"));
                    p.setProductName(rs.getString("product_name"));
                    p.setProductPrice(rs.getDouble("product_price"));
                    p.setCategoryId(rs.getInt("category_id"));
                    p.setSupplierId(rs.getInt("supplier_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public boolean updateProduct(Product product) {
        boolean isSuccess = false;
        String query = "UPDATE Product SET product_name = ?, product_price = ?, category_id = ?, supplier_id = ? WHERE product_id = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getProductPrice());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getSupplierId());
            ps.setInt(5, product.getProductId());
            
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteProduct(int productId) {
        boolean isSuccess = false;
        String query = "DELETE FROM Product WHERE product_id = ?";
        
        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, productId);
            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}