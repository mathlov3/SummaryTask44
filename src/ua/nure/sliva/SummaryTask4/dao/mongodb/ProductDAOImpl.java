package ua.nure.sliva.SummaryTask4.dao.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;

import java.util.Base64;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private MongoDatabase mongoDatabase;
    public ProductDAOImpl(MongoDatabase mongoDatabase){
        this.mongoDatabase = mongoDatabase;
    }
    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return null;
    }

    @Override
    public int getCountProductsByCategoryId(int categoryId) {
        return 0;
    }

    @Override
    public List<Product> getProductsBySql(String sql, int start) {
        return null;
    }

    @Override
    public int getCountProductsBySql(String sql) {
        return 0;
    }

    @Override
    public List<Product> getProductsByOrderId(int id) {
        return null;
    }

    @Override
    public List<Product> getNewProducts() {
        return null;
    }

    @Override
    public void voteForProduct(int pId, int uId, int vote) {

    }

    @Override
    public int getProductVote(int pId) {
        return 0;
    }

    @Override
    public List<Image> getImagesById(int id) {
        return null;
    }

    @Override
    public int addProductImage(byte[] image, int productId) {
        return 0;
    }

    @Override
    public void dropImage(int imageId) {

    }

    @Override
    public Product getById(int id) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("products");
        Document document = new Document().append("_id",id);
        Document result = collection.find(document).first();
        Product product = new Product();
        product.setId((Integer) result.get("_id"));
        product.setName((String) result.get("name"));
        product.setCount((Integer) result.get("count"));
        product.setCategoryId((Integer) result.get("categoryId"));
        product.setPrice((Double) result.get("price"));
        product.setImgInBase64((String) result.get("img64"));
        product.setAllDesc((String) result.get("alldesc"));
        product.setDescription((String) result.get("description"));

        return product;
    }

    @Override
    public int create(Product product) {
        int id;
        if (product.getImg() != null) {
            product.setImgInBase64(Base64.getEncoder().encodeToString(product.getImg()));
        }
        MongoCollection<Document> collection = mongoDatabase.getCollection("products");
        id = Integer.parseInt(getNextSequence("productid"));
        Document document = new Document()
                .append("_id",id)
                .append("name",product.getName())
                .append("description",product.getDescription())
                .append("count",id)
                .append("price",product.getPrice())
                .append("categoryId",product.getCategoryId())
                .append("img64",product.getImgInBase64())
                .append("alldesc",product.getAllDesc());
        collection.insertOne(document);
        return id;
    }

    @Override
    public int update(Product entity) {
        return 0;
    }

    @Override
    public int delete(Product entity) {
        return 0;
    }

    @Override
    public List<Product> getList(int start, int end, boolean ascending, String orderColumn, int category) {
        return null;
    }
    public static String getNextSequence(String name){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("testDB");
        MongoCollection<Document> collection = mongoDatabase.getCollection("counters");
        Document find = new Document();
        find.append("_id", name);
        Document update = new Document();
        update.append("$inc", new Document("seq", 1));
        Document obj =  collection.findOneAndUpdate(find, update);
        return obj.get("seq").toString();
    }
}
