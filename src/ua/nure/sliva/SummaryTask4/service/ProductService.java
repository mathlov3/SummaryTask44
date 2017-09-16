package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getProductsByCategory(int categoryId);

    Product getProductById(int id);

    int addProduct(Product product);

    List<Product> getPaginableProducts(int start, int end, int category);

    int getCountProductsInCategory(int categoryId);

    List<Product> getProductsBySql(String sql, int start);

    int getCountProductsBySql(String sql);

    List<Product> getProductsByOrderId(int id);

    List<Product> getProductsThatLagestInOrder(Cart<Product> cart);

    List<Product> getNewProducts();

    Product parseImageToBase64(Product product);

    List<Product> parseImagesToBase64(List<Product> products);

    void voteForProduct(int pId, int uId, int vote);

    int getProductVote(int pId);
}
