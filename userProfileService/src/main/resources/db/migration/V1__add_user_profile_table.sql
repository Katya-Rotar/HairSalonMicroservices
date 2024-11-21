CREATE TABLE `t_user_profile`
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `profile_picture` VARCHAR(255),
    PRIMARY KEY (`id`)
);
