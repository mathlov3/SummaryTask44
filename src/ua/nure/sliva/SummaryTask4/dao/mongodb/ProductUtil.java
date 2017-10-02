package ua.nure.sliva.SummaryTask4.dao.mongodb;

import org.bson.Document;
import ua.nure.sliva.SummaryTask4.entity.Product;

public class ProductUtil {
    public Product map(Document document){
        Product product = new Product();
        product.setId((Integer) document.get("_id"));
        product.setName((String) document.get("name"));
        product.setDescription((String) document.get("description"));
        product.setCategoryId((Integer) document.get("categoryId"));
        product.setCount((Integer) document.get("count"));
        product.setPrice((Double) document.get("price"));
        product.setImgInBase64((String) document.get("img64"));
        product.setAllDesc((String) document.get("alldesc"));
        return product;
    }


    public Document unMap(Product product){
        Document document = new Document()
                .append("_id",product.getId())
                .append("name",product.getName())
                .append("description",product.getDescription())
                .append("categoryId",product.getCategoryId())
                .append("count",product.getCount())
                .append("price",product.getPrice())
                .append("img64",product.getImgInBase64())
                .append("alldesc",product.getAllDesc());
        return document;
    }
}
