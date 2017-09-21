-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Сен 22 2017 г., 00:30
-- Версия сервера: 5.5.53
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `FP`
--

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `commentaries`
--

CREATE TABLE `commentaries` (
  `id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  `content` text,
  `users_id` int(11) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `images`
--

CREATE TABLE `images` (
  `id` int(11) NOT NULL,
  `image` mediumblob NOT NULL,
  `products_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `price` double NOT NULL,
  `users_id` int(11) NOT NULL,
  `orders_status_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Триггеры `orders`
--
DELIMITER $$
CREATE TRIGGER `orders_AFTER_UPDATE` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN
  IF new.orders_status_id = 3 THEN
    UPDATE products PP
    SET count = count +
                (SELECT OP.count
                 FROM (SELECT * FROM products) AS PR, orders_product OP
                 WHERE
                   PR.id = OP.`products_id`
                   AND OP.orders_id = new.id
                   AND PP.id = PR.id limit 1)
    WHERE
      id = ANY (SELECT PR.id
                FROM (SELECT * FROM products) AS PR, orders_product OP
                WHERE
                  PR.id = OP.`products_id`
                  AND OP.orders_id = new.id );
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `orders_product`
--

CREATE TABLE `orders_product` (
  `orders_id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  `count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Триггеры `orders_product`
--
DELIMITER $$
CREATE TRIGGER `orders_product_BEFORE_INSERT` BEFORE INSERT ON `orders_product` FOR EACH ROW BEGIN
  UPDATE products
  SET count = count - NEW.count
  WHERE
    id = new.products_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `orders_status`
--

CREATE TABLE `orders_status` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` double UNSIGNED NOT NULL,
  `count` int(11) UNSIGNED NOT NULL,
  `categories_id` int(11) NOT NULL,
  `image` mediumblob,
  `alldesc` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Триггеры `products`
--
DELIMITER $$
CREATE TRIGGER `products_BEFORE_INSERT` BEFORE INSERT ON `products` FOR EACH ROW BEGIN
  IF new.count < 0 THEN SIGNAL SQLSTATE '01000';
  END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `products_BEFORE_UPDATE` BEFORE UPDATE ON `products` FOR EACH ROW BEGIN
  IF new.count < 0 THEN SIGNAL SQLSTATE '01000';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(45) NOT NULL,
  `e-mail` varchar(45) NOT NULL,
  `roles_id` int(11) NOT NULL,
  `blocked` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Триггеры `users`
--
DELIMITER $$
CREATE TRIGGER `users_BEFORE_INSERT` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
  SET new.password = MD5(new.password);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `votes`
--

CREATE TABLE `votes` (
  `users_id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `waiting`
--

CREATE TABLE `waiting` (
  `id` int(11) NOT NULL,
  `users_id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  `wait` tinyint(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `commentaries`
--
ALTER TABLE `commentaries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_commentaries_products1_idx` (`products_id`),
  ADD KEY `fk_commentaries_users1_idx` (`users_id`);

--
-- Индексы таблицы `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_images_products1_idx` (`products_id`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_orders_users1_idx` (`users_id`),
  ADD KEY `fk_orders_orders_status1_idx1` (`orders_status_id`);

--
-- Индексы таблицы `orders_product`
--
ALTER TABLE `orders_product`
  ADD KEY `fk_orders_product_orders1_idx` (`orders_id`),
  ADD KEY `fk_orders_product_products1_idx` (`products_id`);

--
-- Индексы таблицы `orders_status`
--
ALTER TABLE `orders_status`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_products_categories1_idx` (`categories_id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login_UNIQUE` (`login`),
  ADD KEY `fk_users_roles_idx` (`roles_id`);

--
-- Индексы таблицы `votes`
--
ALTER TABLE `votes`
  ADD PRIMARY KEY (`users_id`,`products_id`),
  ADD KEY `fc_votes_products_idx` (`products_id`);

--
-- Индексы таблицы `waiting`
--
ALTER TABLE `waiting`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_table1_users1_idx` (`users_id`),
  ADD KEY `fk_table1_products1_idx` (`products_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT для таблицы `commentaries`
--
ALTER TABLE `commentaries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT для таблицы `images`
--
ALTER TABLE `images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT для таблицы `orders_status`
--
ALTER TABLE `orders_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;
--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT для таблицы `waiting`
--
ALTER TABLE `waiting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `commentaries`
--
ALTER TABLE `commentaries`
  ADD CONSTRAINT `fk_commentaries_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_commentaries_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `images`
--
ALTER TABLE `images`
  ADD CONSTRAINT `fk_images_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `fk_orders_orders_status1` FOREIGN KEY (`orders_status_id`) REFERENCES `orders_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_orders_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `orders_product`
--
ALTER TABLE `orders_product`
  ADD CONSTRAINT `fk_orders_product_orders1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_orders_product_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_categories1` FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `votes`
--
ALTER TABLE `votes`
  ADD CONSTRAINT `fc_votes_products` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fc_votes_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ограничения внешнего ключа таблицы `waiting`
--
ALTER TABLE `waiting`
  ADD CONSTRAINT `fk_table1_products1` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_table1_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO roles(`id`,`name`) VALUES (1,'admin');
INSERT INTO roles(`id`,`name`) VALUES (2,'client');

INSERT INTO `categories` (`id`, `name`) VALUES
  (1, 'Фермы'),
  (2, 'Материнские платы'),
  (3, 'Оперативная память'),
  (4, 'Процессоры'),
  (5, 'Видеокарты'),
  (6, 'Блоки питания');

INSERT INTO `orders_status` (`id`, `name`) VALUES
  (1, 'new'),
  (2, 'accepted'),
  (3, 'disabled');


INSERT INTO users(`id`,`login`,`password`,`name`,`e-mail`,`roles_id`,`blocked`) VALUES
  (1,'admin','admin','eugene','vzrivpaket22@gmail.com',1,0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
