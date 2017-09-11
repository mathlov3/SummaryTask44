package ua.nure.sliva.SummaryTask4.util;

import java.util.Map;

public class SqlBuilder {
    public String buildSqlProductWithRestrict(ProductParams pp) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE id >= 0");
        if (pp.getName() != null) {
            sql.append(" AND name LIKE '%").append(pp.getName()).append("%'");
        }
        if (pp.getMaxPrice() != 0) {
            sql.append(" AND price <= ").append(pp.getMaxPrice());
        }
        sql.append(" AND price >= ").append(pp.getMinPrice());
        if (!pp.getCategories().isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < pp.getCategories().size() - 1; i++) {
                sql.append("categories_id = ").append(pp.getCategories().get(i).getId()).append(" OR ");
            }
            sql.append("categories_id = ").append(pp.getCategories().get(pp.getCategories().size() - 1).getId()).append(")");
        }
        sql.append(" ORDER BY id LIMIT ?,9");
        return sql.toString();
    }
    public String buildSqlForCount(String sql){
        return sql.replace(" LIMIT ?,9","").replace(" * "," COUNT(*) ");
    }
}
    