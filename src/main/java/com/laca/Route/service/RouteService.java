package com.laca.Route.service;
import com.laca.Route.Route;

import jakarta.transaction.Transactional;
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
                route.setRouteID(resultSet.getLong("id"));
                route.setName(resultSet.getString("name"));
                route.setDescription(resultSet.getString("description"));
                route.setStartPointID(resultSet.getInt("startPoint"));
                route.setEndPointID(resultSet.getInt("endPoint"));
                route.setShippingCost(resultSet.getDouble("shippingPoint"));
                route.setApproved(resultSet.getBoolean("approve"));
                route.setDurationType(resultSet.getString("durationType"));
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
            String query = "INSERT INTO Routes (name, description, startPoint, endPoint, shippingCost, approved, durationtype) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, route.getName());
            statement.setString(2, route.getDescription());
            statement.setInt(3, route.getStartPointID());
            statement.setInt(4, route.getEndPointID());
            statement.setDouble(5, route.getShippingCost());
            statement.setBoolean(6, route.isApproved());
            statement.setString(7, route.getDurationType());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    route.setRouteID(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving new route");
        }
        return route;
    }

    @Transactional
    public Route updateRoute(int routeId, Route updatedRoute) {
        try (Connection connection = dataSource.getConnection()) {
            String storedProcedureCall = "{call update_Routes(?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storedProcedureCall);

            statement.setInt(1, routeId);
            statement.setString(2, updatedRoute.getName());
            statement.setString(3, updatedRoute.getDescription());
            statement.setInt(4, updatedRoute.getStartPointID());
            statement.setInt(5, updatedRoute.getEndPointID());
            statement.setDouble(6, updatedRoute.getShippingCost());
            statement.setBoolean(7, updatedRoute.isApproved());
            statement.setString(8, updatedRoute.getDurationType());

            boolean hasResults = statement.execute();

            if (!hasResults) {
                throw new RuntimeException("Error updating route: No results from the stored procedure.");
            }

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int updatedRouteId = resultSet.getInt("id");
                String updatedName = resultSet.getString("name");
                String updatedDescription = resultSet.getString("description");
                int updatedStartPointID = resultSet.getInt("startPointID");
                int updatedEndPointID = resultSet.getInt("endPointID");
                double updatedShippingCost = resultSet.getDouble("shippingCost");
                boolean updatedApproved = resultSet.getBoolean("approved");
                String updatedDurationType = resultSet.getString("durationType");

                // Crea un nuevo Route con los datos actualizados y devuélvelo
                updatedRoute.setRouteID((long) updatedRouteId);
                updatedRoute.setName(updatedName);
                updatedRoute.setDescription(updatedDescription);
                updatedRoute.setStartPointID(updatedStartPointID);
                updatedRoute.setEndPointID(updatedEndPointID);
                updatedRoute.setShippingCost(updatedShippingCost);
                updatedRoute.setApproved(updatedApproved);
                updatedRoute.setDurationType(updatedDurationType);

                return updatedRoute;
            } else {
                throw new RuntimeException("Route not found by ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating route: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Route getRouteById(Long routeId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT id, name, company FROM Routes WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, routeId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Route route = new Route();
                route.setRouteID(resultSet.getLong("id"));
                route.setName(resultSet.getString("name"));
                route.setDescription(resultSet.getString("description"));
                route.setStartPointID(resultSet.getInt("startPoint"));
                route.setEndPointID(resultSet.getInt("endPoint"));
                route.setShippingCost(resultSet.getDouble("shippingPoint"));
                route.setApproved(resultSet.getBoolean("approve"));
                route.setDurationType(resultSet.getString("durationType"));
                return route;
            } else {
                throw new RuntimeException("Route not found with ID: " + routeId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving route: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Boolean deleteRoute(Long routeId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM Routes where Routes.id  = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, routeId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting route: " + e.getMessage(), e);
        }
    }

}
