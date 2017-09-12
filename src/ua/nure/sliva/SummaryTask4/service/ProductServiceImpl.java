package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private TransactionPool transactionPool;
    public ProductServiceImpl(ProductDAO productDAO,TransactionPool transactionPool){
        this.productDAO = productDAO;
        this.transactionPool = transactionPool;
    }
    @Override
    public List<Product> getProductsByCategory(final int categoryId) {
        final List<Product> products =
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        List<Product> products1 = productDAO.getProductsByCategoryId(categoryId);
                        return products1;
                    }
                });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return products;
    }

    @Override
    public Product getProductById(final int id) {
        Product product = transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                return productDAO.getById(id);
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return product;
    }

    @Override
    public int addProduct(Product product) {
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                int id = productDAO.create(product);
                product.setId(id);
                return product;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return product.getId();
    }

    @Override
    public List<Product> getPaginableProducts(int start, int end, int category) {
        List<Product> products = new ArrayList<>();
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                products.addAll(productDAO.getList(start,end,false,"",category));
                return null ;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return products;
    }

    @Override
    public int getCountProductsInCategory(int categoryId) {
        final int[] count = {0};
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                int count2 = productDAO.getCountProductsByCategoryId(categoryId);
                count[0] = count2;
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return count[0];
    }

    @Override
    public List<Product> getProductsBySql(String sql, int start) {
        List<Product> products = new ArrayList<>();
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                products.addAll(productDAO.getProductsBySql(sql,start));
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return products;
    }

    @Override
    public int getCountProductsBySql(String sql) {
        final int[] count = {0};
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                count[0] = productDAO.getCountProductsBySql(sql);
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return count[0];
    }

    @Override
    public List<Product> getProductsByOrderId(int id) {
        List<Product> products = new ArrayList<>();
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                products.addAll(productDAO.getProductsByOrderId(id));
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return products;
    }

    @Override
    public List<Product> getProductsThatLagestInOrder(Cart<Product> cart) {
        List<Product> errors = new ArrayList<>();
        transactionPool.execute(new Transaction<Product>() {
            @Override
            public Product execute() throws SQLException {
                for (Map.Entry<Product,Integer> entry:cart.entrySet()){
                    Product product = productDAO.getById(entry.getKey().getId());
                    if(product.getCount() < entry.getValue()){
                        errors.add(product);
                    }
                }
                return null;
            }
        });
        transactionPool.closeConnection(ThreadLocaleHandler.getConnection());
        ThreadLocaleHandler.setConnecion(null);
        return errors;
    }
}
