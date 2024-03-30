package com.laca.service;

import com.laca.entity.transport.Transport;
import com.laca.entity.transport.enums.TransportType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportService {

    private final DataSource dataSource;

    public TransportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional // ¿Función add()?
    public List<Transport> getAllTransports() {
        List<Transport> transports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM TransportUnits";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transport transport = new Transport();
                transport.setUnitID(resultSet.getInt("UnitID"));
                transport.setName(resultSet.getString("Name"));
                transport.setPlate(resultSet.getString("Plate"));
                transport.setSizeHeight(resultSet.getInt("SizeHeight"));
                transport.setSizeWidth(resultSet.getInt("SizeWidth"));
                transport.setType(TransportType.valueOf(resultSet.getString("Type")));
                transport.setMaxWeight(resultSet.getInt("MaxWeight"));
                transport.setCarrierID(resultSet.getInt("CarrierID"));
                transports.add(transport);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        return transports;
    }

    @Transactional // Duda respecto a los índices
    public Transport saveTransport(Transport transport) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO TransportUnits (Name, Plate, SizeHeight, SizeWidth, Type, MaxWeight, CarrierID) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, transport.getName());
            statement.setString(2, transport.getPlate());
            statement.setInt(1, transport.getSizeHeight());
            statement.setInt(2, transport.getSizeWidth());
            statement.setString(1, String.valueOf(transport.getType()));
            statement.setInt(2, transport.getMaxWeight());
            statement.setInt(1, transport.getCarrierID());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    transport.setUnitID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new transport");
        }
        return transport;
    }

    @Transactional // Problemas de tipo de variables
    public Transport updateTransport(int UnitID, Transport updatedTransport) {
        try (Connection connection = dataSource.getConnection()) {
            String storedProcedureCall = "{call update_transport(?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storedProcedureCall);

            statement.setInt(1, UnitID);
            statement.setString(2, updatedTransport.getName());
            statement.setString(3, updatedTransport.getPlate());
            statement.setInt(4, updatedTransport.getSizeHeight());
            statement.setInt(5, updatedTransport.getSizeWidth());
            statement.setString(6, String.valueOf(updatedTransport.getType()));
            statement.setInt(7, updatedTransport.getMaxWeight());
            statement.setInt(8, updatedTransport.getCarrierID());

            boolean hasResults = statement.execute();

            if (!hasResults) {
                throw new RuntimeException("Error updating transport: No results from the stored procedure.");
            }

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int updatedId = resultSet.getInt("UnitID");
                String updatedName = resultSet.getString("Name");
                String updatedPlate = resultSet.getString("Plate");
                int updateSizeHeight = resultSet.getInt("SizeHeight");
                int updateSizeWidth = resultSet.getInt("SizeWidth");
                String updateType = resultSet.getString("Type");
                int updateMaxWeight = resultSet.getInt("MaxWeight");
                int updateCarrierID = resultSet.getInt("CarrierID");

                // Crea un nuevo Transporter con los datos actualizados y devuélvelo
                updatedTransport.setUnitID((int) updatedId);
                updatedTransport.setName(updatedName);
                updatedTransport.setPlate(updatedPlate);
                updatedTransport.setSizeHeight(updateSizeHeight);
                updatedTransport.setSizeWidth(updateSizeWidth);
                updatedTransport.setType(TransportType.valueOf(updateType));
                updatedTransport.setMaxWeight(updateMaxWeight);
                updatedTransport.setCarrierID(updateCarrierID);
                return updatedTransport;
            } else {
                throw new RuntimeException("Transport not found by ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating transport: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Transport getTransportById(int UnitID) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT UnitID, Name, Plate, SizeHeight, SizeWidth, Type, MaxWeight, CarrierID FROM TransportUnits WHERE UnitID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, UnitID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Transport transport = new Transport();
                transport.setUnitID(resultSet.getInt("UnitID"));
                transport.setName(resultSet.getString("Name"));
                transport.setPlate(resultSet.getString("Plate"));
                transport.setSizeHeight(resultSet.getInt("SizeHeight"));
                transport.setSizeWidth(resultSet.getInt("SizeWidth"));
                transport.setType(TransportType.valueOf(resultSet.getString("Type")));
                transport.setMaxWeight(resultSet.getInt("MaxWeight"));
                transport.setCarrierID(resultSet.getInt("CarrierID"));
                return transport;
            } else {
                throw new RuntimeException("Transport not found with ID: " + UnitID);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving transport: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Boolean deleteTransport(int UnitID) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM TransportUnits where transports.UnitID  = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, UnitID);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transport: " + e.getMessage(), e);
        }
    }
}