package com.finalproject.HomeInventory.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.finalproject.HomeInventory.DAO.UserDAOImpl;
import com.finalproject.HomeInventory.model.User;

@Component
public class UserValidator {
    @Autowired
    private UserDAOImpl userService;

    public boolean existUser(String username){
        User user = userService.findByUsername(username);
        if(user != null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean confirmPasswordValidate(String password, String confirmPassword){
        if(confirmPassword.equals(password)){
            return true;
        }else{
            return false;
        }
        
    }
}