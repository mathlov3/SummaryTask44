package ua.nure.sliva.SummaryTask4.service;

import ua.nure.sliva.SummaryTask4.Cart;
import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.transaction.ThreadLocaleHandler;
import ua.nure.sliva.SummaryTask4.transaction.Transaction;
import ua.nure.sliva.SummaryTask4.transaction.TransactionPool;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private TransactionPool transactionPool;

    public ProductServiceImpl(ProductDAO productDAO, TransactionPool transactionPool) {
        this.productDAO = productDAO;
        this.transactionPool = transactionPool;
    }


    @Override
    public Product getProductById(final int id) {
        return parseImageToBase64(transactionPool.execute(new Transaction<Product>() {
                    @Override
                    public Product execute() throws SQLException {
                        return productDAO.getById(id);
                    }
                })
        );
    }

    @Override
    public int addProduct(Product product) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return productDAO.create(product);
            }
        });
    }

    @Override
    public List<Product> getPaginableProducts(int start, int end, int category) {
        return parseImagesToBase64(
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        return productDAO.getList(start, end, false, "", category);
                    }
                })
        );

    }

    @Override
    public int getCountProductsInCategory(int categoryId) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return productDAO.getCountProductsByCategoryId(categoryId);
            }
        });
    }

    @Override
    public List<Product> getProductsBySql(String sql, int start) {
        return parseImagesToBase64(
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        return productDAO.getProductsBySql(sql, start);
                    }
                })
        );
    }

    @Override
    public int getCountProductsBySql(String sql) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return productDAO.getCountProductsBySql(sql);
            }
        });
    }

    @Override
    public List<Product> getProductsByOrderId(int id) {
        return parseImagesToBase64(
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        return productDAO.getProductsByOrderId(id);
                    }
                })
        );
    }

    @Override
    public List<Product> getProductsThatLagestInOrder(Cart<Product> cart) {

        return parseImagesToBase64(
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        List<Product> errors = new ArrayList<>();
                        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                            Product product = productDAO.getById(entry.getKey().getId());
                            if (product.getCount() < entry.getValue()) {
                                errors.add(product);
                            }
                        }
                        return errors;
                    }
                })
        );
    }

    @Override
    public List<Product> getNewProducts() {
        return parseImagesToBase64(
                transactionPool.execute(new Transaction<List<Product>>() {
                    @Override
                    public List<Product> execute() throws SQLException {
                        return productDAO.getNewProducts();
                    }
                })
        );

    }

    @Override
    public Product parseImageToBase64(Product product) {
        if (product.getImg() != null) {
            product.setImgInBase64(Base64.getEncoder().encodeToString(product.getImg()));
        }
        return product;
    }

    @Override
    public List<Product> parseImagesToBase64(List<Product> products) {
        for (Product product : products) {
            parseImageToBase64(product);
        }
        return products;
    }

    @Override
    public void voteForProduct(int pId, int uId, int vote) {
        transactionPool.execute(new Transaction<Object>() {
            @Override
            public Object execute() throws SQLException {
                productDAO.voteForProduct(pId,uId,vote);
                return null;
            }
        });
    }

    @Override
    public int getProductVote(int pId) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return productDAO.getProductVote(pId);
            }
        });
    }

    @Override
    public int updateProduct(Product product) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                if(product.getImg() == null){
                    product.setImg(productDAO.getById(product.getId()).getImg());
                }
                return productDAO.update(product);
            }
        });
    }

    @Override
    public int addImageForProduct(byte[] image, int productId) {
        return transactionPool.execute(new Transaction<Integer>() {
            @Override
            public Integer execute() throws SQLException {
                return productDAO.addProductImage(image,productId);
            }
        });
    }

    @Override
    public List<Image> getProductImages(int productId) {
        return transactionPool.execute(new Transaction<List<Image>>() {
            @Override
            public List<Image> execute() throws SQLException {
                List<Image> images = productDAO.getImagesById(productId);
                for (Image image:images){
                    image.setBase64Img(Base64.getEncoder().encodeToString(image.getByteImage()));
                }
                return images;
            }
        });
    }

    @Override
    public void deleteImage(int imageId) {
        transactionPool.execute(new Transaction<Object>() {
            @Override
            public Object execute() throws SQLException {
                productDAO.dropImage(imageId);
                return null;
            }
        });
    }
}
