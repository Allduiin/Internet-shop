CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `orders`
(
    `order_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id`  bigint DEFAULT NULL,
    PRIMARY KEY (`order_id`),
    KEY `orders_users_fk_idx` (`user_id`),
    CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;

CREATE TABLE `orders_products`
(
    `order_id`   bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `orders_fk_idx` (`order_id`),
    KEY `products_fk_idx` (`product_id`),
    CONSTRAINT `orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT `products_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `products`
(
    `product_id` bigint       NOT NULL AUTO_INCREMENT,
    `name`       varchar(225) NOT NULL,
    `price`      double       NOT NULL,
    PRIMARY KEY (`product_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles`
(
    `role_id` bigint       NOT NULL,
    `name`    varchar(225) NOT NULL,
    PRIMARY KEY (`role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `shopping_carts`
(
    `cart_id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`cart_id`, `user_id`),
    KEY `shoppingcart_user_fk_idx` (`user_id`),
    CONSTRAINT `shoppingcart_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  DEFAULT CHARSET = utf8;

CREATE TABLE `shopping_carts_products`
(
    `cart_id`    bigint NOT NULL,
    `product_id` bigint NOT NULL,
    KEY `shopping_cart_fk_idx` (`cart_id`),
    KEY `product_fk_idx` (`product_id`),
    CONSTRAINT `product_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
    CONSTRAINT `shopping_cart_fk` FOREIGN KEY (`cart_id`) REFERENCES `shopping_carts` (`cart_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users`
(
    `user_id`  bigint       NOT NULL AUTO_INCREMENT,
    `login`    varchar(225) NOT NULL,
    `password` varchar(225) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user name_UNIQUE` (`login`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 69
  DEFAULT CHARSET = utf8;

CREATE TABLE `users_roles`
(
    `user_id` bigint NOT NULL,
    `role_id` bigint NOT NULL,
    KEY `users_fk_idx` (`user_id`),
    KEY `role_fk_idx` (`role_id`),
    CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
    CONSTRAINT `users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
