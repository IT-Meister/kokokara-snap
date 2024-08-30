-- 作成
CREATE TABLE user_cameras (
    user_camera_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    camera_id INTEGER NOT NULL,
    is_primary BOOLEAN NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- データ挿入
INSERT INTO user_cameras (user_id , camera_id , is_primary) VALUES 
(1, 1, true),
(1, 2, false)