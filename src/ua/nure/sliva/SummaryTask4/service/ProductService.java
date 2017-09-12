package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByCategory(int categoryId);

    Product getProductById(int id);

    int addProduct(Product product);

    List<Product> getPaginableProducts(int start,int end,int category);

    int getCountProductsInCategory(int categoryId);

    List<Product> getProductsBySql(String sql,int start);

    int getCountProductsBySql(String sql);

    List<Product> getProductsByOrderId(int id);
}
