package com.finalproject.HomeInventory.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.finalproject.HomeInventory.DAO.CategoryDAOImpl;
import com.finalproject.HomeInventory.DAO.UserDAOImpl;
import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.model.Category;
import com.finalproject.HomeInventory.model.User;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserDAOImpl userService;
    
    @Autowired
    CategoryDAOImpl categoryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        // Create default account
        if (userService.getAllUser() == null || userService.getAllUser().isEmpty()) {
            User admin = new User("admin", "password",
                    "cprg352+admin@gmail.com", "Admin", "Admin",
                    Constants.ACTIVE, Constants.ROLE_ADMIN);
            User admin2 = new User("admin2", "password",
                    "cprg352+admin2@gmail.com", "Admin2", "Admin2",
                    Constants.DEACTIVE, Constants.ROLE_ADMIN);
            User user1 = new User("anne", "password",
                    "cprg352+anne@gmail.com", "Anne", "Annerson",
                    Constants.ACTIVE, Constants.ROLE_USER);
            User user2 = new User("barb", "password",
                    "cprg352+barb@gmail.com", "Barb", "Barber",
                    Constants.DEACTIVE, Constants.ROLE_USER);
            User user3 = new User("carl", "password",
                    "cprg352+carl@gmail.com", "Carl", "Carler",
                    Constants.ACTIVE, Constants.ROLE_USER);
            User user4 = new User("don", "password",
                    "cprg352+don@gmail.com", "Don", "Donner",
                    Constants.ACTIVE, Constants.ROLE_USER);
            userService.savebyadmin(admin);
            userService.savebyadmin(admin2);
            userService.savebyadmin(user1);
            userService.savebyadmin(user2);
            userService.savebyadmin(user3);
            userService.savebyadmin(user4);
        }
        
        if(categoryService.getAllCategories() == null || categoryService.getAllCategories().isEmpty()){
            Category category1 = new Category("kitchen");
            Category category2 = new Category("bathroom");
            Category category3 = new Category("living room");
            Category category4 = new Category("basement");
            Category category5 = new Category("bedrooom");
            Category category6 = new Category("garage");
            Category category7 = new Category("office");
            Category category8 = new Category("utility room");
            Category category9 = new Category("storage");
            Category category10 = new Category("other");
            categoryService.saveCategory(category1);
            categoryService.saveCategory(category2);
            categoryService.saveCategory(category3);
            categoryService.saveCategory(category4);
            categoryService.saveCategory(category5);
            categoryService.saveCategory(category6);
            categoryService.saveCategory(category7);
            categoryService.saveCategory(category8);
            categoryService.saveCategory(category9);
            categoryService.saveCategory(category10);
        }
    }
    @PostConstruct
    public void init() {
    }
    
}
