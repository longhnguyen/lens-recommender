-- =========================
-- CAMERA TABLE
-- =========================
CREATE TABLE camera (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(255),
    mount_type VARCHAR(50),
    sensor_size VARCHAR(50),
    crop_factor DOUBLE PRECISION
);

-- =========================
-- CAMERA ALIAS TABLE
-- =========================
CREATE TABLE camera_alias (
id SERIAL PRIMARY KEY,
alias VARCHAR(255) NOT NULL,
camera_id INTEGER NOT NULL,

CONSTRAINT fk_camera_alias_camera
    FOREIGN KEY (camera_id)
        REFERENCES camera (id)
            ON DELETE CASCADE
);

-- Index for performance (important for lookup by camera_id)
CREATE INDEX idx_camera_alias_camera_id
    ON camera_alias(camera_id);

-- =========================
-- LENS TABLE
-- =========================
CREATE TABLE lens (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50),
    name VARCHAR(255),
    mount_type VARCHAR(50),
    compatibility_flag VARCHAR(50),
    focal_length_min INTEGER,
    focal_length_max INTEGER,
    max_aperture DOUBLE PRECISION,
    weight INTEGER,
    is_zoom BOOLEAN
);