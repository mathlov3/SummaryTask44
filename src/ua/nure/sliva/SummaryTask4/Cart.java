package ua.nure.sliva.SummaryTask4;

import ua.nure.sliva.SummaryTask4.entity.Product;

import java.io.Serializable;
import java.util.HashMap;

public class Cart <T extends Product> extends HashMap<T,Integer> implements Serializable {


    public void add(T product,int count){
        put(product,count);
    }

    public void remove(T product){
        super.remove(product);
    }

    public double getPrice(){
        double price = 0;
        for (T p:keySet()){
            price += p.getPrice() * get(p);
        }
        return price;
    }
}
