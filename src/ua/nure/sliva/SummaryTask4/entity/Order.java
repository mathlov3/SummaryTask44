package ua.nure.sliva.SummaryTask4.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    private int id;
    private double price;
    private int usersId;
    private int orders_status_id;
    private Map<Product,Integer> products = new HashMap<>();

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public int getOrders_status_id() {
        return orders_status_id;
    }

    public void setOrders_status_id(int orders_status_id) {
        this.orders_status_id = orders_status_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", usersId=" + usersId +
                ", orders_status_id=" + orders_status_id +
                '}';
    }
}
