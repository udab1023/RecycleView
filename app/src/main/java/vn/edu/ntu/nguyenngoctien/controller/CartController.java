package vn.edu.ntu.nguyenngoctien.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.nguyenngoctien.model.Product;

public class CartController extends Application implements ICartController {
    @Override
    public boolean addToShoppingCart(Product p) {
        if (!shoppingCart.contains(p)){
            shoppingCart.add(p);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> shoppingCart = new ArrayList<>();
    public CartController() {
        listProduct.add(new Product("Buoi", 60000, "Buoi nam roi"));
        listProduct.add(new Product("Tao", 70000, "Tao Chau Doc"));
        listProduct.add(new Product("Le", 80000, "Le chau Phi"));
        listProduct.add(new Product("Quyt", 90000, "Quyt nhieu hat"));
        listProduct.add(new Product("Dua hau", 100000, "Dua hau nam moi"));
        listProduct.add(new Product("Man", 10000, "Man My Tho"));
        listProduct.add(new Product("Dua gang", 1110000, "Dua gang not tui ba gang"));
        listProduct.add(new Product("Cam ", 999999, "Cam sanh mong nuoc"));
        listProduct.add(new Product("Dua", 12345, "Dua tren cay"));
        listProduct.add(new Product("Dao", 1234231, "Dao tien"));
        listProduct.add(new Product("Khoai", 119, "Kho oai khoai"));
        listProduct.add(new Product("new fruits", 965486, "just new fruits"));
    }

    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }
}
