CREATE TABLE `t_hairdresser_locations`
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `hairdresser_id` BIGINT NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `city_id` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`hairdresser_id`) REFERENCES `t_hairdressers`(`id`) ON DELETE CASCADE
);
