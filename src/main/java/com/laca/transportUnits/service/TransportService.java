package com.laca.transportUnits.service;

import com.laca.transportUnits.Transport;
import com.laca.transportUnits.enums.TransportType;
import com.laca.transportUnits.pattern.builder.ITransport;
import com.laca.transportUnits.pattern.builder.TransportBuilder;
import com.laca.transportUnits.pattern.director.TransportBuilderDirector;
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

    @Transactional
    public List<Transport> getAllTransports() {
        List<Transport> transports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM TransportUnits";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ITransport iTransport = new TransportBuilder();
                TransportBuilderDirector transportBuilderDirector = new TransportBuilderDirector();
                transportBuilderDirector.pickValues(resultSet.getInt("UnitID"), resultSet.getString("Name"), resultSet.getString("Plate"), resultSet.getInt("SizeHeight"), resultSet.getInt("SizeWidth"), resultSet.getInt("MaxWeight"), resultSet.getInt("CarrierID"));

                if (resultSet.getString("Type").equals("Pickup")) {
                    transportBuilderDirector.buildPickup(iTransport);
                } else if (resultSet.getString("Type").equals("Truck")) {
                    transportBuilderDirector.buildTruck(iTransport);
                } else if (resultSet.getString("Type").equals("Motorcycle")) {
                    transportBuilderDirector.buildMotorcycle(iTransport);
                } else if (resultSet.getString("Type").equals("On_Foot")) {
                    transportBuilderDirector.buildOnFoot(iTransport);
                }
                Transport tras = iTransport.build();
                transports.add(tras);
            }
            return transports;
        } catch (SQLException e) {

        }
        return transports;
    }

    @Transactional
    public Transport saveTransport(Transport transport) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO TransportUnits (Name, Plate, SizeHeight, SizeWidth, Type, MaxWeight, CarrierID) VALUES (?, ?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, transport.getName());
            statement.setString(2, transport.getPlate());
            statement.setInt(3, transport.getSizeHeight());
            statement.setInt(4, transport.getSizeWidth());
            statement.setString(5, transport.getType());
            statement.setInt(6, transport.getMaxWeight());
            statement.setInt(7, transport.getCarrierID());
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
            String storedProcedureCall = "{call update_TransportUnits(?, ?, ?, ?, ?, ?, ?, ?)}";
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
                updatedTransport.setType(updateType);
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
                transport.setType(resultSet.getString("Type"));
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
            String query = "DELETE FROM TransportUnits where UnitID  = ?";
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