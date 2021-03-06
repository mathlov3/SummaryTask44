package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.util.ProductParams;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(int id);

    int addProduct(Product product);

    List<Product> getPaginableProducts(int start, int end, int category);

    int getCountProductsInCategory(int categoryId);

    List<Product> getProductsBySql(ProductParams pp, int start);

    int getCountProductsBySql(String sql);

    List<Product> getProductsByOrderId(int id);

    List<Product> getProductsThatLagestInOrder(Cart<Product> cart);

    List<Product> getNewProducts();

    Product parseImageToBase64(Product product);

    List<Product> parseImagesToBase64(List<Product> products);

    void voteForProduct(int pId, int uId, int vote);

    int getProductVote(int pId);

    int updateProduct(Product product);

    int addImageForProduct(byte[] image, int productId);

    List<Image> getProductImages(int productId);

    void deleteImage(int imageId);
}
