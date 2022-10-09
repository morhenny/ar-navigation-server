CREATE TABLE IF NOT EXISTS PLACES (
    id              VARCHAR(60) DEFAULT RANDOM_UUID()  PRIMARY KEY,
    name            VARCHAR     NOT NULL,
    lat             double      NOT NULL,
    lng             double      NOT NULL,
    alt             double      NOT NULL,
    heading         double      NOT NULL,
    description     VARCHAR     NOT NULL,
    author          VARCHAR     NOT NULL,
    ardata          VARCHAR     NOT NULL
    );