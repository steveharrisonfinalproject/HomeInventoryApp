package com.finalproject.HomeInventory.model;

public class Category{

    private int categoryid;
    private String categoryname;

    public Category(String categoryname) {
        super();
        this.categoryname = categoryname;
    }

    public Category() {
        super();
    }


    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }    
    
    @Override
    public String toString() {
        return "Category [categoryid= " +categoryid +",categoryname = " + categoryname +"]";
    }

}
