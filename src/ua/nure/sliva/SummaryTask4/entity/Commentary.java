package ua.nure.sliva.SummaryTask4.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Commentary implements Serializable {
    private int id;
    private int products_id;
    private String content;
    private Integer users_id;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProducts_id() {
        return products_id;
    }

    public void setProducts_id(int products_id) {
        this.products_id = products_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Integer users_id) {
        this.users_id = users_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", products_id=" + products_id +
                ", content='" + content + '\'' +
                ", users_id=" + users_id +
                ", date=" + date +
                '}';
    }
}
