package com.finalproject.HomeInventory.DAO;

import java.util.List;

import com.finalproject.HomeInventory.model.Items;

public interface ItemDAO {

    public void saveItem(Items item);
    
    public void updateItem(Items item);
    
    public List<Items> getAllItem();
    
    public List<Items> getItemByUser(String username);
    
    public void deleteItem(Items item);
    
    public Items findItem(int itemid);
}
