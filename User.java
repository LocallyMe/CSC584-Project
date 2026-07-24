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
import model.InventoryStock;
import model.StockTransaction;
import util.DBConnection;

public class InventoryDAO {

    // READ: Get current stock for a specific product in a warehouse
    public InventoryStock getStockByProductAndWarehouse(int productId, int warehouseId) {
        InventoryStock stock = null;
        String query = "SELECT * FROM Inventory_Stock WHERE product_id = ? AND warehouse_id = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, productId);
            ps.setInt(2, warehouseId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = new InventoryStock();
                    stock.setInventoryId(rs.getInt("inventory_id"));
                    stock.setQuantityHand(rs.getInt("quantity_hand"));
                    stock.setWarehouseId(rs.getInt("warehouse_id"));
                    stock.setProductId(rs.getInt("product_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    // UPDATE: Update stock levels when inventory changes
    public boolean updateStock(int productId, int warehouseId, int newQuantity) {
        boolean isSuccess = false;
        String query = "UPDATE Inventory_Stock SET quantity_hand = ? WHERE product_id = ? AND warehouse_id = ?";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.setInt(3, warehouseId);

            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // CREATE: Record a stock transaction (In / Out)
    public boolean recordTransaction(StockTransaction transaction) {
        boolean isSuccess = false;
        String query = "INSERT INTO Stock_Transaction (product_id, type, transaction_date, quantity) VALUES (?, ?, NOW(), ?)";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, transaction.getProductId());
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getQuantity());

            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // READ: Get full transaction history (Useful for your Dashboard metrics)
    public List<StockTransaction> getAllTransactions() {
        List<StockTransaction> list = new ArrayList<>();
        String query = "SELECT * FROM Stock_Transaction ORDER BY transaction_date DESC";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                StockTransaction st = new StockTransaction();
                st.setTransactionId(rs.getInt("transaction_id"));
                st.setProductId(rs.getInt("product_id"));
                st.setType(rs.getString("type"));
                st.setTransactionDate(rs.getTimestamp("transaction_date"));
                st.setQuantity(rs.getInt("quantity"));
                list.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
