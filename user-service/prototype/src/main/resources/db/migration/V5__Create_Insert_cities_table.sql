-- テーブルの作成
CREATE TABLE cities (
    id SERIAL PRIMARY KEY,
    prefecture_id SMALLINT,
    name VARCHAR(128),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- データの挿入
INSERT INTO cities (prefecture_id, name) VALUES
(15, '糸魚川'),
(13, '新宿区');