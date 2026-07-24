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
import model.Warehouse;
import util.DBConnection;

public class WarehouseDAO {

    // READ: Get all warehouses
    public List<Warehouse> getAllWarehouses() {
        List<Warehouse> warehouseList = new ArrayList<>();
        String query = "SELECT * FROM Warehouse";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Warehouse w = new Warehouse();
                w.setWarehouseId(rs.getInt("warehouse_id"));
                w.setName(rs.getString("name"));
                w.setAddress(rs.getString("address"));
                warehouseList.add(w);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warehouseList;
    }

    // CREATE: Add a new warehouse
    public boolean addWarehouse(Warehouse warehouse) {
        boolean isSuccess = false;
        String query = "INSERT INTO Warehouse (name, address) VALUES (?, ?)";

        try (Connection con = DBConnection.createConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, warehouse.getName());
            ps.setString(2, warehouse.getAddress());

            isSuccess = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
