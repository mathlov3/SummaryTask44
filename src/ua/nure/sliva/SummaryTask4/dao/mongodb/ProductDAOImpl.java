package ua.nure.sliva.SummaryTask4.dao.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import ua.nure.sliva.SummaryTask4.dao.ProductDAO;
import ua.nure.sliva.SummaryTask4.entity.Category;
import ua.nure.sliva.SummaryTask4.entity.Image;
import ua.nure.sliva.SummaryTask4.entity.Product;
import ua.nure.sliva.SummaryTask4.util.ProductParams;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private MongoDatabase mongoDatabase;
    private ProductUtil productUtil;
    public ProductDAOImpl(MongoDatabase mongoDatabase){
        this.mongoDatabase = mongoDatabase;
        productUtil = new ProductUtil();
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
        MongoCollection<Document> collection = mongoDatabase.getCollection("products");
        Document search = new Document().append("categoryId",categoryId);
        return (int) collection.count(search);
    }

    @Override
    public List<Product> getProductsBySql(ProductParams pp, int start) {
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
        List<Product> products = new ArrayList<>();
        MongoCollection<Document> productDocuments = mongoDatabase.getCollection("products");
        Document sort = new Document().append("_id",-1);
        MongoCursor<Document> cursor = productDocuments.find().sort(sort).limit(4).iterator();
        while (cursor.hasNext()){
            products.add(productUtil.map(cursor.next()));
        }
        return products;
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
        MongoCollection<Document> collection = mongoDatabase.getCollection("products");
        System.out.println(collection.find(new Document("_id",id)).first().get("images"));
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
        Product product = productUtil.map(result);
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
        product.setId(id);
        collection.insertOne(productUtil.unMap(product));
        return id;
    }

    @Override
    public int update(Product product) {
        Document search = new Document().append("_id",product.getId());
        if (product.getImg() != null) {
            product.setImgInBase64(Base64.getEncoder().encodeToString(product.getImg()));
        }
        Bson nDocument = productUtil.unMap(product);
        mongoDatabase.getCollection("products").updateOne(search,new Document("$set",nDocument));
        return product.getId();
    }

    @Override
    public int delete(Product entity) {
        return 0;
    }

    @Override
    public List<Product> getList(int start, int end, boolean ascending, String orderColumn, int category) {
        return null;
    }
    public static synchronized String getNextSequence(String name){
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
