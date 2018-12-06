/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.HomeInventory.DAO;
import com.finalproject.HomeInventory.model.Category;
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
/**
 *
 * @author Admin
 */
public class CategoryDAOImpl implements CategoryDAO{
    
    @Autowired
    DataSource datasource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Category findCategory(int CategoryId) {
        String sql = "SELECT * FROM categories WHERE categoryid = '" + CategoryId + "'";
        List<Category> categories = jdbcTemplate.query(sql, new CategoryMapper());
        if(!categories.isEmpty()){
            return categories.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";
        List<Category> categories = jdbcTemplate.query(sql, new CategoryMapper());
        return categories;
    }

    @Override
    public void saveCategory(Category category) {
        String sql = "INSERT categories(Category_Name) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps
                        = connection.prepareStatement(sql, new String[]{"categoryid"});
                ps.setString(1, category.getCategoryname());
                return ps;
            }
        },
                keyHolder);
    }

    @Override
    public void updateCategory(Category category) {
        String sql ="Update categories c SET c.Category_Name = ? WHERE c.categoryid = ?";
        jdbcTemplate.update(sql, new Object[]{category.getCategoryname(), category.getCategoryid()});
    }
    
}
class CategoryMapper implements RowMapper<Category>{
    public Category mapRow(ResultSet rs, int arg1) throws SQLException{
        Category category = new Category();
        category.setCategoryid(rs.getInt("categoryid"));
        category.setCategoryname(rs.getString("Category_Name"));
        return category;
    }   
}
