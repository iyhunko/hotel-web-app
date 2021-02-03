CREATE TABLE `users` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `password` VARCHAR(64) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE `rooms` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NOT NULL COMMENT 'type is varchar because we may have numbers like: 143-A, 13-B...',
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT(2000) NULL,
  `price` FLOAT UNSIGNED NOT NULL,
  `photo` TEXT(500) NULL,
  `floor` INT NOT NULL,
  `class` VARCHAR(45) NOT NULL,
  `size` INT UNSIGNED NOT NULL,
  `rooms_quantity` INT UNSIGNED NOT NULL,
  `adults_quantity` INT UNSIGNED NOT NULL,
  `children_quantity` INT UNSIGNED NOT NULL,
  `beds_quantity` INT UNSIGNED NOT NULL,
  `has_bar` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_tv` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_fridge` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_balcony` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `is_smoking_allowed` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `are_pets_allowed` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `requests` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `price_from` FLOAT UNSIGNED NULL,
  `price_to` FLOAT UNSIGNED NULL,
  `checkin_date` TIMESTAMP NOT NULL,
  `checkout_date` TIMESTAMP NOT NULL,
  `floor` INT NOT NULL,
  `class` VARCHAR(45) NOT NULL,
  `room_size` INT UNSIGNED NOT NULL,
  `rooms_quantity` INT UNSIGNED NOT NULL,
  `adults_quantity` INT UNSIGNED NOT NULL,
  `children_quantity` INT UNSIGNED NOT NULL,
  `beds_quantity` INT UNSIGNED NOT NULL,
  `has_bar` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_tv` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_fridge` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `has_balcony` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `is_smoking_allowed` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `are_pets_allowed` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `bookings` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `room_id` BIGINT(20) UNSIGNED NOT NULL,
  `payment_id` BIGINT(20) UNSIGNED NOT NULL,
  `checkin_date` TIMESTAMP NOT NULL,
  `checkout_date` TIMESTAMP NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


CREATE TABLE `payments` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `booking_id` BIGINT(20) UNSIGNED NOT NULL,
  `sum` INT UNSIGNED NOT NULL,
  `currency` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `expire_date` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


CREATE TABLE `roles` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


CREATE TABLE `users_roles` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `role_id` BIGINT(20) UNSIGNED NOT NULL);