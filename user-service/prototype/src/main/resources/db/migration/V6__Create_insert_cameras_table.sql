-- 作成
CREATE TABLE cameras (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(64) NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- データ挿入
INSERT INTO cameras (brand, name) VALUES 
('CANON', 'EOS Kiss X10'),
('SONY', 'α7');