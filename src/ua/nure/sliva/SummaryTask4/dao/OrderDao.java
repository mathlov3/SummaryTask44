package ua.nure.sliva.SummaryTask4.dao;

import ua.nure.sliva.SummaryTask4.entity.Order;
import ua.nure.sliva.SummaryTask4.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDao extends GenericDAO<Order> {
    boolean addProductsToOrder(Map<Product,Integer> products,int orderId);
    List<Order> getOrdersByUserId(int id);

    List<Order> getOrdersByStatusId(int id);
}
