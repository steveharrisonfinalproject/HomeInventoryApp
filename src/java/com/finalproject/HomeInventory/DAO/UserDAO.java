package com.finalproject.HomeInventory.DAO;

import java.util.List;

import com.finalproject.HomeInventory.model.User;

public interface UserDAO {

    public List<User> getAllUser();
    public void save(User user);
    public void savebyadmin(User user);
    public void updateUser(User user);
    public void deactiveUser(User user);
    public void activeUser(String username);
    public void promoteUser(String username);
    public void demoteUser(String username);
    public void changePassword(User user);
    public User findByUsername(String username);
    public void deleteUserByAdmin(User user);
}
