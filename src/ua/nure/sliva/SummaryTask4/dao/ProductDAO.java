package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.util.Pagenable;

import java.util.Collection;
import java.util.List;

public interface ProductDAO extends GenericDAO<Product>,Pagenable<Product> {
    List<Product> getProductsByCategoryId(int categoryId);

    List<Category> getAllCategory();

    int getCountProductsByCategoryId(int categoryId);

    List<Product> getProductsBySql(String sql,int start);

    int getCountProductsBySql(String sql);

    List<Product> getProductsByOrderId(int id);

}
