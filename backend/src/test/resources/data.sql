DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    title              VARCHAR(5 char)   NOT NULL,
    first_name         VARCHAR(50 char) NOT NULL,
    last_name          VARCHAR(50 char) NOT NULL,
    username           VARCHAR(25 char)  NOT NULL,
    password           VARCHAR(500 char)  NOT NULL,
    email              VARCHAR(100 char) NOT NULL,
    cell_phone         VARCHAR(11 char),
    is_admin           VARCHAR(5 char),
    created_by         VARCHAR(25 char)  NOT NULL,
    created_date       DATETIME2         NOT NULL,
    last_modified_by   VARCHAR(25 char)  NOT NULL,
    last_modified_date DATETIME2         NOT NULL,
);
