
CREATE TABLE `practice_unit` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

 CREATE TABLE `designation` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `employee` (
  `id` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `pu_id`int NOT NULL,
  `designation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pu_id_idx` (`pu_id`),
  KEY `fk_designation_id_idx` (`designation_id`),
  CONSTRAINT `fk_designation_id` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`id`),
  CONSTRAINT `fk_pu_id` FOREIGN KEY (`pu_id`) REFERENCES `practice_unit` (`id`)
);

CREATE TABLE `asset_type` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `asset_categories` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `manufacturing_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) DEFAULT NULL,
  `manufacturing_date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `waranty_start` date DEFAULT NULL,
  `waranty_end` date DEFAULT NULL,
  `vendor_id` int DEFAULT NULL,
  `vendor_name` varchar(45) DEFAULT NULL,
  `vendor_location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `common_attributes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(10) DEFAULT NULL,
  `model_number` varchar(10) DEFAULT NULL,
  `sku` int DEFAULT NULL,
  `colour` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `more_attributes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ram` varchar(10) DEFAULT NULL,
  `hard_disk` varchar(10) DEFAULT NULL,
  `operating_system` varchar(10) DEFAULT NULL,
  `screen_size` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `asset` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `type_id` int NOT NULL,
  `categories_id` int NOT NULL,
  `manufacturing_info_id` int NOT NULL,
  `common_attributes_id` int NOT NULL,
  `more_attribute_id` int NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_asset_1_idx` (`type_id`),
  KEY `fk_categories_id_idx` (`categories_id`),
  KEY `fk_manufacturing_id_idx` (`manufacturing_info_id`),
  KEY `fk_common_attribute_id_idx` (`common_attributes_id`),
  KEY `fk_more_attributes_id_idx` (`more_attribute_id`),
  CONSTRAINT `fk_categories_id` FOREIGN KEY (`categories_id`) REFERENCES `asset_categories` (`id`),
  CONSTRAINT `fk_common_attribute_id` FOREIGN KEY (`common_attributes_id`) REFERENCES `common_attributes` (`id`),
  CONSTRAINT `fk_manufacturing_id` FOREIGN KEY (`manufacturing_info_id`) REFERENCES `manufacturing_info` (`id`),
  CONSTRAINT `fk_more_attributes_id` FOREIGN KEY (`more_attribute_id`) REFERENCES `more_attributes` (`id`),
  CONSTRAINT `fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `asset_type` (`id`)
);

CREATE TABLE `asset_allocation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `employee_id` varchar(10) NOT NULL,
  `asset_id` int NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employee_id_idx` (`employee_id`),
  KEY `fk_asset_id_idx` (`asset_id`),
  CONSTRAINT `fk_asset_id_asset_allocation` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`),
  CONSTRAINT `fk_employee_id_asset_allocation` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
);

CREATE TABLE `asset_history` (
  `id` int NOT NULL,
  `date` date DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `asset_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_asset_id_idx` (`asset_id`),
  CONSTRAINT `fk_asset_id` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`)
);
