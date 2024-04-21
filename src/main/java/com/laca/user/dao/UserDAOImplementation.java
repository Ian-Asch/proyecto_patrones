package com.laca.user.dao;

import com.laca.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDAOImplementation implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setUserID(rs.getInt("UserID"));
        user.setName(rs.getString("Name"));
        user.setIdentificationNumber(rs.getString("IdentificationNumber"));
        user.setCompanyName(rs.getString("CompanyName"));
        user.setUserType(rs.getString("UserType"));
        user.setPassword(rs.getString("Password"));
        return user;
    };

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User getUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, userRowMapper);
    }

    @Override
    public User createUser(User user) {
        String sql = "INSERT INTO Users (Name, IdentificationNumber, CompanyName, UserType, Password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getIdentificationNumber(), user.getCompanyName(), user.getUserType(), user.getPassword());
        return user;
    }

    @Override
    public User updateUser(User user) {
        String sql = "UPDATE Users SET Name = ?, IdentificationNumber = ?, CompanyName = ?, UserType = ?, Password = ? WHERE UserID = ?";
        jdbcTemplate.update(sql, user.getName(), user.getIdentificationNumber(), user.getCompanyName(), user.getUserType(), user.getPassword(), user.getUserID());
        return user;
    }

    @Override
    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        jdbcTemplate.update(sql, userId);
    }
}