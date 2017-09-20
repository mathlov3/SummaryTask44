package ua.nure.sliva.SummaryTask4.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Image implements Serializable {
    private int id;
    private byte[] byteImage;
    private int products_id;
    private String base64Img;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", byteImage=" + Arrays.toString(byteImage) +
                ", products_id=" + products_id +
                ", base64Img='" + base64Img + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public String getBase64Img() {
        return base64Img;
    }

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }
}
