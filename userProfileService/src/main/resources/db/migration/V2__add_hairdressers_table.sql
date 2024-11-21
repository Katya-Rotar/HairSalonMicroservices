CREATE TABLE `t_hairdressers`
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `portfolio` VARCHAR(255),
    `certificates` VARCHAR(255),
    `rating` DECIMAL(2, 1) DEFAULT 0.0,
    `is_verified` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`)
);
