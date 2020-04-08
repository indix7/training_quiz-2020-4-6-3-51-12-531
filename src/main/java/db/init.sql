CREATE TABLE parking_a
(
    no  INT         NOT NULL,
    car VARCHAR(10) NOT NULL,
    PRIMARY KEY (no)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

CREATE TABLE parking_b
(
    no  INT         NOT NULL,
    car VARCHAR(10) NOT NULL,
    PRIMARY KEY (no)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

CREATE TABLE parking_capacity
(
    parking  VARCHAR(10) NOT NULL,
    capacity INT         NOT NULL,
    PRIMARY KEY (parking)
) ENGINE = InnoDB
  CHARSET = utf8mb4;

