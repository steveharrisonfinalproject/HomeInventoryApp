package com.finalproject.HomeInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.config.AuthenticationManager;
import com.finalproject.HomeInventory.model.Category;
import com.finalproject.HomeInventory.model.User;
import com.finalproject.HomeInventory.DAO.CategoryDAOImpl;
import com.finalproject.HomeInventory.DAO.UserDAOImpl;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAOImpl categoryService;
    @Autowired 
    UserDAOImpl userService;
    
    @RequestMapping(value ="/admin/categories", method = RequestMethod.GET)
    public String category(Model model){
        SecurityContext sc = AuthenticationManager.getInstance().getSc();
        if(sc == null){
            return "redirect:/access-denied";
        }
        Authentication auth = sc.getAuthentication();
        String username = auth.getName();
        User admin = userService.findByUsername(username);
        if(Constants.ROLE_ADMIN!= admin.getIsadmin()){
            return "redirect:/access-denied";
        }
        model.addAttribute("admin", admin);
        List<Category> lsCategory = categoryService.getAllCategories();
        if(!lsCategory.isEmpty()){
            model.addAttribute("lsCategory", lsCategory);
        }
        return "/admin/category";
    }
    
    @RequestMapping(value="/admin/updatecategory", method = RequestMethod.POST)
    public String updateCategory(Model model,
            @RequestParam(value = "categoryidedit", required = false) int categoryidedit,
            @RequestParam(value = "categorynameedit", required = false) String categoryname) {
        Category category = categoryService.findCategory(categoryidedit);
        if(categoryname != null){
            category.setCategoryname(categoryname);
            categoryService.updateCategory(category);
        }
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/addcategory", method = RequestMethod.POST)
    public String addCategory(
            Model model,
            @RequestParam(value = "categoryname", required = false) String categoryname) {
        Category category = new Category();
        category.setCategoryname(categoryname);
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }
}
