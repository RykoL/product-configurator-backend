create table environment
(
    id   SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL
);

INSERT INTO environment(name) VALUES ('Studio');
INSERT INTO environment(name) VALUES ('Beach');
