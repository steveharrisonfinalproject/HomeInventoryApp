package com.finalproject.HomeInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.HomeInventory.DAO.CategoryDAOImpl;
import com.finalproject.HomeInventory.DAO.ItemDAOImpl;
import com.finalproject.HomeInventory.DAO.UserDAOImpl;
import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.config.AuthenticationManager;
import com.finalproject.HomeInventory.model.Category;
import com.finalproject.HomeInventory.model.Items;
import com.finalproject.HomeInventory.model.User;

@Controller
public class ItemController {

    @Autowired
    ItemDAOImpl itemService;

    @Autowired
    CategoryDAOImpl categoryService;
    
    @Autowired
    UserDAOImpl userService;

    @RequestMapping(value = "user/items", method = RequestMethod.GET)
    public String getItem(Model model) {
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        if(Constants.ROLE_USER != user.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("user",user);
        List<Items> listItems = itemService.getItemByUser(username);
        List<Category> listCategory = categoryService.getAllCategories();
        if (!listItems.isEmpty()) {
            for (Items item : listItems) {
                if(item.getCategory() == null){
                    item.setCategory(categoryService.findCategory(item.getCategoryid()));
                }
            }
            model.addAttribute("listItems", listItems);
        }
        if (!listCategory.isEmpty()) {
            model.addAttribute("listCategory", listCategory);
        }
        return "/user/items";
    }

    @RequestMapping(value = "user/additem", method = RequestMethod.POST)
    public String addItem(Model model,
            @RequestParam(value = "itemname", required = false) String itemname,
            @RequestParam(value ="categoryid", required = false) int categoryid,
            @RequestParam(value = "price", required = false) double price) {
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        Items item  = new Items();
        item.setOwner(username);
        item.setItemname(itemname);
        item.setCategoryid(categoryid);
        item.setPrice(price);
        itemService.saveItem(item);
        return "redirect:/user/items";
    }
    
    @RequestMapping(value = "user/deleteitem", method = RequestMethod.POST)
    public String deleteItem(Model model,
            @RequestParam(value = "itemid", required = false) int itemid) {
        Items itemdelete = itemService.findItem(itemid);
        itemService.deleteItem(itemdelete);
        return "redirect:/user/items";
    }
    
    @RequestMapping(value = "user/updateitem", method = RequestMethod.POST)
    public String updateItem(Model model,
            @RequestParam(value = "itemid", required = false) int itemid,
            @RequestParam(value = "categoryid", required = false) int categoryid,
            @RequestParam(value = "itemname", required = false) String itemname,
            @RequestParam(value = "price", required = false) double price){
        Items itemUpdate = itemService.findItem(itemid);
        itemUpdate.setCategoryid(categoryid);
        itemUpdate.setItemname(itemname);
        itemUpdate.setPrice(price);
        itemService.updateItem(itemUpdate);
        return "redirect:/user/items";
    }
}
