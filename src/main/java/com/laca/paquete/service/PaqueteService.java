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
            }

        } catch (SQLException e) {
//           throw new SQLException(e);
        }catch (IllegalArgumentException e ){
//            error
        }

        return paquetes;
    }
}
