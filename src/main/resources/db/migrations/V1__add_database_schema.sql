CREATE
    TABLE
        users(
            id VARCHAR(100) NOT NULL PRIMARY KEY DEFAULT(
                UUID()
            ),
            email_address VARCHAR(50) NOT NULL UNIQUE,
            phone_number_ VARCHAR(15),
            municipality_id VARCHAR(50),
            status VARCHAR(15)
        );