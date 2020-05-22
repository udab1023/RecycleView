package vn.edu.ntu.nguyenngoctien.controller;

import java.util.ArrayList;

import vn.edu.ntu.nguyenngoctien.model.Product;

public interface ICartController {
    public ArrayList<Product> getListProduct();
    public  boolean addToShoppingCart(Product p);
    public ArrayList<Product> getShoppingCart();
    public void clearShoppingCart();
}
