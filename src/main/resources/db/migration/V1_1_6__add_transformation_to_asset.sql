-- Row Major 4x4 transformation matrix
ALTER TABLE asset
    ADD COLUMN transform real[4][4] DEFAULT '{{1, 0 , 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0 , 0 , 0, 1}}';
