package com.finalproject.HomeInventory.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.model.User;

public class UserDAOImpl implements UserDAO{
    
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM Users";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        return  users;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(Constants.ACTIVE);
        user.setIsadmin(Constants.ROLE_USER);
        String sql = "INSERT Users values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                new Object[] { user.getUsername(), user.getPassword(),user.getEmail(),
                        user.getFirstname(), user.getLastname(),user.getActive(),user.getIsadmin() });
        
    }

    @Override
    public void savebyadmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(Constants.ACTIVE);
        String sql = "INSERT Users values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                new Object[] { user.getUsername(), user.getPassword(),user.getEmail(),
                        user.getFirstname(), user.getLastname(),user.getActive(),user.getIsadmin() });
    }

    @Override
    public void updateUser(User user) {
        String sql ="UPDATE Users u SET u.email = ?, u.first_name = ?, u.last_name = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { user.getEmail(), user.getFirstname(),user.getLastname(),user.getUsername() });
    }

    @Override
    public void deactiveUser(User user) {
        user.setActive(Constants.DEACTIVE);
        String sql ="UPDATE Users u SET u.active = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { user.getActive(), user.getUsername() });
    }

    @Override
    public void activeUser(String username) {
        String sql ="UPDATE Users u SET u.active = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { Constants.ACTIVE, username });
        
    }

    @Override
    public void promoteUser(String username) {
        String sql ="UPDATE Users u SET u.is_admin = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { Constants.ROLE_ADMIN, username });
    }

    @Override
    public void demoteUser(String username) {
        String sql ="UPDATE Users u SET u.is_admin = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { Constants.ROLE_USER, username });
        
    }

    @Override
    public void changePassword(User user) {
        String sql = "UPDATE Users u SET u.password = ? WHERE u.username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { bCryptPasswordEncoder.encode(user.getPassword()), user.getUsername() });
        
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = '" + username + "'";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        if(!users.isEmpty()){
            return users.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void deleteUserByAdmin(User user) {
        String sql = "DELETE FROM Users WHERE username = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { user.getUsername() });
    }
    
}

class UserMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setFirstname(rs.getString("First_Name"));
        user.setLastname(rs.getString("Last_Name"));
        user.setEmail(rs.getString("Email"));
        user.setIsadmin(rs.getInt("Is_Admin"));
        user.setActive(rs.getInt("Active"));
        return user;
    }
}
