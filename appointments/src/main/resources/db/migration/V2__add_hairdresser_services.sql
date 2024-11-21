CREATE TABLE `t_hairdresser_services`
(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `hairdresser_id` bigint(20) NOT NULL,
    `service_id` bigint(20) NOT NULL,
    `service_price` decimal(10,2) NOT NULL,
    `service_duration` int(11) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`service_id`) REFERENCES `t_services`(`id`)
);
