package com.laca.service;
import com.laca.entity.Route.Route;
import com.laca.entity.Transporter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {
    private final DataSource dataSource;

    public RouteService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM routes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getLong("id"));
                route.setStartingPoint(resultSet.getInt("startingPoint"));
                route.setPointArrival(resultSet.getInt("pointArrival"));
                route.setName(resultSet.getString("name"));
                route.setDescription(resultSet.getString("description"));
                routes.add(route);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        }
        return routes;
    }

    @Transactional
    public Route saveRoute(Route route) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO routes (startingPoint, pointArrival, name, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, route.getStartingPoint());
            statement.setInt(2, route.getPointArrival());
            statement.setString(1, route.getName());
            statement.setString(2, route.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    route.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new transporter");
        }
        return route;
    }



}
