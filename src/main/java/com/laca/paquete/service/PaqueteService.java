package com.laca.paquete.service;

import com.laca.paquete.Paquete;
import com.laca.paquete.PaqueteFactory;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaqueteService {
    private final DataSource dataSource;

    public PaqueteService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<Paquete> getAllPaquetes() {
        List<Paquete> paquetes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Packages";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Paquete paquete = PaqueteFactory.crearPaquete(
                        resultSet.getInt("PackageID"),
                        // resultSet.getString("Type"), //Este valor no existe en el SQL
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("Weight"),
                        resultSet.getDouble("Price"),
                        resultSet.getDouble("SizeHeight"),
                        resultSet.getDouble("SizeWidth"),
                        resultSet.getInt("ClientID"),
                        resultSet.getInt("RouteID"),
                        resultSet.getString("Status"));
                paquetes.add(paquete);
            }
            return paquetes;

        } catch (Exception e) {
            throw new RuntimeException("Error updating package: " + e.getMessage(), e);
        }
    }


    @Transactional
    public Paquete savePaquete(Paquete paquete) {

        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Packages (PackageID," +
                    "Name," +
                    "Description," +
                    "Weight," +
                    "Price," +
                    "SizeHeight," +
                    "SizeWidth," +
                    "ClientID," +
                    "RouteID," +
                    "Status) VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1, paquete.getPackageID());
            statement.setString(2, paquete.getName());
            statement.setString(3, paquete.getDescription());
            statement.setDouble(4, paquete.getWeight());
            statement.setDouble(5, paquete.getPrice());
            statement.setDouble(6, paquete.getSizeHeight());
            statement.setDouble(7, paquete.getSizeWidth());
            statement.setInt(8, paquete.getClientID());
            statement.setInt(9, paquete.getRouteID());
            statement.setString(10, paquete.getStatus());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    paquete.setPackageID(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paquete;
    }


    @Transactional
    public void updatePaquete(int packageID, Paquete updatePaquete) {

        try (Connection connection = dataSource.getConnection()) {

            String query = "UPDATE Packages p set Name = ?," +
                "Description = ?," +
                "Weight = ?," +
                "Price = ?," +
                "SizeHeight = ?," +
                "SizeWidth = ?," +
                "ClientID = ?," +
                "RouteID = ?," +
                "Status = ?  WHERE p.PackageID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,updatePaquete.getName());
            statement.setString(2,updatePaquete.getDescription());
            statement.setDouble(3,updatePaquete.getWeight());
            statement.setDouble(4,updatePaquete.getPrice());
            statement.setDouble(5,updatePaquete.getSizeHeight());
            statement.setDouble(6,updatePaquete.getSizeWidth());
            statement.setInt(7,updatePaquete.getClientID());
            statement.setInt(8,updatePaquete.getRouteID());
            statement.setString(9,updatePaquete.getStatus());
            statement.setInt(10,packageID);

        } catch (SQLException e) {
            throw new RuntimeException("Error updating package: " + e.getMessage(), e);
        }
    }


    @Transactional
    public Paquete getPaqueteId(int packageID) {
        try (Connection connection = dataSource.getConnection()) {

            String query = "SELECT * FROM Packages WHERE PackageID = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, packageID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Paquete paquete = PaqueteFactory.crearPaquete(
                        resultSet.getInt("PackageID"),
                        // resultSet.getString("Type"), //Este valor no existe en el SQL
                        resultSet.getString("Name"),
                        resultSet.getString("Description"),
                        resultSet.getDouble("Weight"),
                        resultSet.getDouble("Price"),
                        resultSet.getDouble("SizeHeight"),
                        resultSet.getDouble("SizeWidth"),
                        resultSet.getInt("ClientID"),
                        resultSet.getInt("RouteID"),
                        resultSet.getString("Status"));
                return paquete;
            } else {
                throw new RuntimeException("Package not found with ID: " + packageID);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving package: " + e.getMessage(), e);
        }
    }


    public Boolean deletePaquete(int packageID) {
        try (Connection connection = dataSource.getConnection()) {

            String query = "DELETE FROM Packages WHERE PackageID = ?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, packageID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting package: " + e.getMessage(), e);
        }
    }
}