-- postgisを有効化
CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    uid VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    url VARCHAR(255) NOT NULL,
    title VARCHAR(128) NOT NULL,
    city_id INTEGER,
    description VARCHAR(255),
    camera_id INTEGER NOT NULL,
    latlng GEOGRAPHY NOT NULL,
    snap_time TIMESTAMPTZ, 
    angle INTEGER,
    iso VARCHAR(32),
    f_value NUMERIC(5, 2),
    shutter_speed VARCHAR(32),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP 
);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER update_posts_updated_at
    BEFORE UPDATE ON posts
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();