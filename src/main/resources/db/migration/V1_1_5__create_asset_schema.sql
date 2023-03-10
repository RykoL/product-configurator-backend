create type asset_type as enum ('environment');

create table asset
(
    id   SERIAL PRIMARY KEY,
    name varchar(255) NOT NULL,
    location varchar NOT NULL,
    asset_type varchar NOT NULL
);

INSERT INTO asset(name, location, asset_type) VALUES('brown_photostudio_02_4k.hdr', 'http://localhost:8081/assets/brown_photostudio_02_4k.hdr', 'Environment');
INSERT INTO asset(name, location, asset_type) VALUES('blouberg_sunrise_2_4k.hdr', 'http://localhost:8081/assets/blouberg_sunrise_2_4k.hdr', 'Environment');

ALTER TABLE environment ADD COLUMN asset_id BIGINT;

UPDATE environment SET asset_id = 1 WHERE id = 1;
UPDATE environment SET asset_id = 2 WHERE id = 2;

ALTER TABLE environment ALTER COLUMN asset_id SET NOT NULL;
ALTER TABLE environment ADD CONSTRAINT environment_asset_id_fkey FOREIGN KEY (asset_id) REFERENCES asset(id) ;