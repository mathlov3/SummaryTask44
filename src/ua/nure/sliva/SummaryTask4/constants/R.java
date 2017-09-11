package ua.nure.sliva.SummaryTask4.constants;

public abstract class R {
    //User
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password=?";
    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String CREATE_USER = "INSERT INTO users(`login`,`password`,`name`,`e-mail`,`roles_id`) VALUES(?,?,?,?,?)";

    //Product
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    public static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE categories_id = ?";
    public static final String GET_PRODUCTS_BY_RESTRICTS = "SELECT * FROM products WHERE categories_id = ? ORDER BY id LIMIT ?,?";
    public static final String CREATE_PRODUCT = "INSERT INTO `products`(`name`,`description`,`price`,`count`,`categories_id`) VALUES(?,?,?,?,?)";
    public static final String GET_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM products WHERE categories_id = ?";

    //Role
    public static final String GET_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    //Category
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";


    //Order
    public static final String CREATE_ORDER = "INSERT INTO orders(`price`,`users_id`,`orders_status_id`) VALUES(?,?,?)";
    public static final String ADD_PRODUCT_TO_ORDER = "INSERT INTO orders_product(`orders_id`,`products_id`,`count`) VALUES(?,?,?)";
    public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET orders_status_id = ? WHERE id = ?";




    //OrderStatus
}
