package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.entity.Order;

import java.util.List;

public interface OrderService {
    int tryToCreate(Order order);
    List<Order> getOrdersByUserId(int id);
    boolean isUserHaveOrder(int uId,int oId);
    List<Order> getOrdersByStatusId(int id);
}
