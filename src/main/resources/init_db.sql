CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internet_shop`.`users` (
  `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(225) NOT NULL,
  `password` VARCHAR(225) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user name_UNIQUE` (`login` ASC) VISIBLE);

INSERT INTO `internet_shop`.`users` (`login`, `password`) VALUES ('bob', '1');

CREATE TABLE `internet_shop`.`products` (
  `product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `internet_shop`.`orders` (
  `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(11) NULL,
  PRIMARY KEY (`order_id`),
  INDEX `orders_users_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `orders_users_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `internet_shop`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;