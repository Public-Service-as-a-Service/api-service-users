CREATE
    TABLE
        users(
            guid VARCHAR(100) NOT NULL DEFAULT(
                UUID()
            ),
            email_address VARCHAR(50) NOT NULL PRIMARY KEY,
            phone_number_ VARCHAR(15),
            municipality_id VARCHAR(50),
            status ENUM(
                'ACTIVE',
                'SUSPENDED',
                'INACTIVE'
            )
        );