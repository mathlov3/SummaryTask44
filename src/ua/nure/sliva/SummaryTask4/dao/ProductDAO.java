package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.util.Pagenable;
import ua.nure.sliva.SummaryTask4.util.ProductParams;

import java.util.Collection;
import java.util.List;

public interface ProductDAO extends GenericDAO<Product>,Pagenable<Product> {
    List<Product> getProductsByCategoryId(int categoryId);

    List<Category> getAllCategory();

    int getCountProductsByCategoryId(int categoryId);

    List<Product> getProductsBySql(ProductParams pp, int start);

    int getCountProductsBySql(String sql);

    List<Product> getProductsByOrderId(int id);

    List<Product> getNewProducts();

    void voteForProduct(int pId, int uId, int vote);

    int getProductVote(int pId);

    List<Image> getImagesById(int id);

    int addProductImage(byte[] image,int productId);

    void dropImage(int imageId);
}
