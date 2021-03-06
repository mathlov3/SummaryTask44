package ua.nure.sliva.SummaryTask4.constants;

public abstract class Sql {

    private Sql(){};
    //User
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    public static final String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password=?";
    public static final String GET_ALL_USERS = "SELECT * FROM users ORDER";
    public static final String GET_ALL_USERS_WITH_TOTALSUM = "SELECT * ,(SELECT IFNULL(SUM(price),0) FROM orders WHERE users.id=orders.users_id) FROM users ORDER BY (SELECT IFNULL(SUM(price),0) FROM orders WHERE users.id=orders.users_id) DESC";
    public static final String CREATE_USER = "INSERT INTO users(`login`,`password`,`name`,`e-mail`,`roles_id`) VALUES(?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE `users` SET `id`=? ,`login`=?,`password`=?,`name`=?,`e-mail`=?,`roles_id`=?,`blocked`=? WHERE id = ?";
    public static final String GET_WAITING_USERS_BY_PRODUCT_ID = "SELECT users.id, users.login, users.password,users.name,users.`e-mail`,users.roles_id,users.blocked FROM users,waiting WHERE waiting.products_id = ? AND waiting.users_id = users.id AND waiting.`wait` = 1 ";
    public static final String ADD_WAITING_USER = "INSERT INTO waiting(`users_id`,`products_id`) VALUES(?,?)";
    public static final String INVALIDATE_WAITING_USER = "UPDATE waiting SET `wait` = 0 WHERE `wait` = 1 AND products_id = ?";

    //Product
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    public static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE categories_id = ?";
    public static final String GET_PRODUCTS_BY_RESTRICTS = "SELECT * FROM products WHERE categories_id = ? ORDER BY id LIMIT ?,?";
    public static final String CREATE_PRODUCT = "INSERT INTO `products`(`name`,`description`,`price`,`count`,`categories_id`,`image`,`alldesc`) VALUES(?,?,?,?,?,?,?)";
    public static final String GET_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM products WHERE categories_id = ?";
    public static final String GET_PRODUCTS_BY_ORDER_ID = "SELECT p.id,p.name,p.description,p.price,op.count,p.categories_id,p.image,p.alldesc FROM products p,orders_product op,orders o WHERE op.orders_id=o.id AND op.products_id=p.id AND o.id=?";
    public static final String GET_NEW_FOUR_PRODUCTS = "SELECT * FROM products WHERE count>=1 ORDER BY id DESC LIMIT 0,4";
    public static final String GET_POPULAR_FIVE_PRODUCTS = "SELECT p.id,p.name,p.description,p.price,p.count,p.categories_id,p.image,sum(op.count) FROM products p,orders_product op WHERE p.id = op.products_id GROUP BY p.id ORDER BY sum(op.count) DESC LIMIT 0,5;";
    public static final String VOTE_FOR_PRODUCT = "INSERT INTO `votes`(`users_id`, `products_id`, `value`) VALUES (?,?,?)";
    public static final String GET_PRODUCT_VOTE = "SELECT IFNULL(AVG(value+0),0) FROM votes WHERE products_id =?";
    public static final String UPDATE_PRODUCT = "UPDATE `products` SET `id`=?,`name`=?,`description`=?,`price`=?,`count`=?,`categories_id`=?,`image`=?, `alldesc`=? WHERE id = ?";
    public static final String GET_IMAGES_BY_PRODUCT_ID = "SELECT * FROM images WHERE products_id = ?";
    public static final String ADD_IMAGE_FOR_PRODUCT = "INSERT INTO `images`(`image`, `products_id`) VALUES (?,?)";

    //Role
    public static final String GET_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    //Category
    public static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";


    //Order
    public static final String CREATE_ORDER = "INSERT INTO orders(`price`,`users_id`,`orders_status_id`) VALUES(?,?,?)";
    public static final String ADD_PRODUCT_TO_ORDER = "INSERT INTO orders_product(`orders_id`,`products_id`,`count`) VALUES(?,?,?)";
    public static final String GET_ORDERS_BY_USER_ID = "SELECT * FROM ORDERS WHERE users_id = ? ORDER BY id DESC";
    public static final String GET_ORDER_BY_ID = "SELECT * FROM ORDERS WHERE ID = ?";
    public static final String GET_NEW_ORDERS = "SELECT * FROM orders WHERE orders_status_id = 1 ORDER BY id";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders ORDER BY id DESC";
    public static final String GET_ACCEPTED_ORDERS_ORDERS = "SELECT * FROM orders WHERE orders_status_id=2 ORDER BY id DESC";
    public static final String GET_DISABLED_ORDERS_ORDERS = "SELECT * FROM orders WHERE orders_status_id=3 ORDER BY id DESC";
    public static final String UPDATE_ORDER_STATUS = "UPDATE orders SET orders_status_id = ?, note = ? WHERE id = ?";

    //OrderStatus

    //Commentary
    public static final String CREATE_COMMENTARY = "INSERT INTO `commentaries`(`products_id`, `content`, `users_id`) VALUES (?,?,?)";
    public static final String GET_COMMENTARIES_BY_PRODUCT_ID = "SELECT * FROM commentaries WHERE products_id = ? ORDER BY id DESC";

    public static final String DELETE_IMAGE = "DELETE FROM `images` WHERE id = ?";

}
