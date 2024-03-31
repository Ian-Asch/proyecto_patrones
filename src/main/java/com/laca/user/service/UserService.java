package com.laca.user.service;
import com.laca.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final DataSource dataSource;

    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("UserID"));
                user.setName(resultSet.getString("name"));
                user.setIdentificationNumber(resultSet.getString("name"));
                user.setCompanyName(resultSet.getString("CompanyName"));
                user.setUserType(resultSet.getString("UserType"));
                user.setPassword(resultSet.getString("Password"));
                users.add(user);   // setting all users in arraylist
            }
        } catch (SQLException e) {
//           throw new SQLException(e);
        }catch (IllegalArgumentException e ){
//            error
        }
        return users;
    }

    @Transactional
    public User saveUser(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Users (Name, IdentificationNumber, CompanyName, UserType, Password) VALUES (?, ?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getIdentificationNumber());
            statement.setString(3, user.getCompanyName());
            statement.setString(4, user.getUserType());
            statement.setString(5, user.getPassword());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 1) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setUserID(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Transactional
    public User updateUser(int userId, User updatedUser) {
        try (Connection connection = dataSource.getConnection()) {
            String storedProcedureCall = "{call update_Users(?, ?, ?, ?, ?, ?)}";
            CallableStatement statement = connection.prepareCall(storedProcedureCall);
            statement.setInt(1, userId);
            statement.setString(2, updatedUser.getName());
            statement.setString(3, updatedUser.getIdentificationNumber());
            statement.setString(4, updatedUser.getCompanyName());
            statement.setString(5, updatedUser.getUserType());
            statement.setString(6, updatedUser.getPassword());

            boolean hasResults = statement.execute();

            if (!hasResults) {
                throw new RuntimeException("Error updating users: No results from the stored procedure.");
            }

            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int updatedId = resultSet.getInt("userID");
                String updatedName = resultSet.getString("name");
                String updatedCompany = resultSet.getString("CompanyName");
                String updatedIdentificationNumber = resultSet.getString("IdentificationNumber");
                String updatedUserType = resultSet.getString("UserType");
                String updatedPassword = resultSet.getString("Password");

                // Crea un nuevo user con los datos actualizados y devuélvelo
                updatedUser.setUserID((int) updatedId);
                updatedUser.setName(updatedName);
                updatedUser.setCompanyName(updatedCompany);
                updatedUser.setIdentificationNumber(updatedIdentificationNumber);
                updatedUser.setUserType(updatedUserType);
                updatedUser.setPassword(updatedPassword);
                return updatedUser;
            } else {
                throw new RuntimeException("User not found by ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user: " + e.getMessage(), e);
        }
    }

    @Transactional
    public User getUserId(Long userId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Users WHERE UserID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("UserID"));
                user.setName(resultSet.getString("name"));
                user.setIdentificationNumber(resultSet.getString("name"));
                user.setCompanyName(resultSet.getString("CompanyName"));
                user.setUserType(resultSet.getString("UserType"));
                user.setPassword(resultSet.getString("Password"));

                return user;
            } else {
                throw new RuntimeException("Transporter not found with ID: " + userId);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving transporter: " + e.getMessage(), e);
        }
    }

    @Transactional
    public Boolean deleteUser(int userId) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM Users where UserID  = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting transporter: " + e.getMessage(), e);
        }
    }

}
