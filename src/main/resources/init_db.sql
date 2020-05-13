CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`users`
(
    `user_id`  BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(225) NOT NULL,
    `password` VARCHAR(225) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user name_UNIQUE` (`login` ASC) VISIBLE
);

INSERT INTO `internet_shop`.`users` (`login`, `password`)
VALUES ('bob', '1');

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(225) NOT NULL,
    `price`      DOUBLE       NOT NULL,
    PRIMARY KEY (`product_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internet_shop`.`orders`
(
    `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id`  BIGINT(11) NULL,
    PRIMARY KEY (`order_id`),
    INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `orders_users_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internet_shop`.`orders_products`
(
    `product_id` BIGINT(11) NOT NULL,
    `order_id`   BIGINT(11) NOT NULL,
    INDEX `orders_id_fk_idx` (`order_id` ASC) VISIBLE,
    INDEX `products_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `orders_id_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internet_shop`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `products_id_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `shopping_cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id`         BIGINT(11) NOT NULL,
    PRIMARY KEY (`shopping_cart_id`, `user_id`),
    INDEX `shoppingcart_user_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `shoppingcart_user_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `cart_id`    BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
    INDEX `shopping_cart_fk_idx` (`cart_id` ASC) VISIBLE,
    INDEX `product_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `shopping_cart_fk`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`shoppingcart_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `product_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


