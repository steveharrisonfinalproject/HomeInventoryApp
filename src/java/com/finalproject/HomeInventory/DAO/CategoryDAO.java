/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.HomeInventory.DAO;

import com.finalproject.HomeInventory.model.Category;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CategoryDAO {
    
    public Category findCategory(int CategoryId);
    
    public List<Category> getAllCategories();
    
    public void saveCategory(Category category);
    
    public void updateCategory(Category category);
}
