CREATE TABLE users (
    guid VARCHAR(100) NOT NULL DEFAULT (UUID()),
    `email-address` VARCHAR(50) NOT NULL PRIMARY KEY,
    `phone-number` VARCHAR(15),
     `municipality-id` VARCHAR(50),
    `status` ENUM('ACTIVE', 'SUSPENDED', 'INACTIVE')
);