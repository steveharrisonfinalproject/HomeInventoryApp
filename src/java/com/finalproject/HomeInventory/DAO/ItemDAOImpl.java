package com.finalproject.HomeInventory.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.finalproject.HomeInventory.model.Items;

public class ItemDAOImpl implements ItemDAO{
    
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void saveItem(Items item) {
       String sql = "INSERT items(categoryid,Item_Name,Price,Owner) value (?,?,?,?)";
       KeyHolder keyHolder = new GeneratedKeyHolder();
       jdbcTemplate.update(
               new PreparedStatementCreator() {
           public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
               PreparedStatement ps
                       = connection.prepareStatement(sql, new String[]{"itemid"});
               ps.setInt(1, item.getCategoryid());
               ps.setString(2, item.getItemname());
               ps.setDouble(3, item.getPrice());
               ps.setString(4, item.getOwner());
               return ps;
           }
       },
               keyHolder);
        
    }

    @Override
    public List<Items> getAllItem() {
        String sql = "SELECT * FROM items";
        List<Items> items = jdbcTemplate.query(sql, new ItemMapper());
        return items;
    }

    @Override
    public List<Items> getItemByUser(String username) {
        String sql = "SELECT * FROM items i WHERE Owner = '" + username + "'";
        List<Items> items = jdbcTemplate.query(sql, new ItemMapper());
        return items;
    }

    @Override
    public void deleteItem(Items item) {
        String sql = "DELETE FROM items WHERE itemid = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { item.getItemid()});
        
    }

    @Override
    public Items findItem(int itemid) {
        String sql = "SELECT * FROM items WHERE itemid = '" + itemid + "'";
        List<Items> items = jdbcTemplate.query(sql, new ItemMapper());
        if(!items.isEmpty()){
            return items.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void updateItem(Items item) {
        String sql = "UPDATE items i SET i.categoryid = ?, i.Item_Name = ?, i.Price = ? WHERE i.itemid = ?";
        jdbcTemplate.update(
                sql,
                new Object[] { item.getCategoryid(), item.getItemname(),
                        item.getPrice(), item.getItemid() });

    }

}

class ItemMapper implements RowMapper<Items>{
    public Items mapRow(ResultSet rs, int arg1) throws SQLException{
        Items item = new Items();
        item.setItemid(rs.getInt("itemid"));
        item.setCategoryid(rs.getInt("categoryid"));
        item.setItemname(rs.getString("Item_name"));
        item.setPrice(rs.getDouble("Price"));
        item.setOwner(rs.getString("Owner"));
        return item;
    }
}
