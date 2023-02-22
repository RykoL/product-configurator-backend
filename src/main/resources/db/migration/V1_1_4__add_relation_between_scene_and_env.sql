ALTER TABLE scene ADD COLUMN "environment_id" BIGINT NOT NULL
    CONSTRAINT scene_environment_id_fkey REFERENCES environment(id) ;