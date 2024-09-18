-- users table
-- pgcryptoを有効化（パスワードハッシュ化）
CREATE EXTENSION IF NOT EXISTS pgcrypto;
-- uuidを有効化
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- サンプルユーザーデータの挿入
INSERT INTO users (display_name, user_name, email, password, prefecture, icon_url,  last_login_time)
VALUES
  ('山田太郎', 'yamada_taro', 'yamada@example.com', crypt('password123', gen_salt('bf')), 13, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Donald_Trump_official_portrait.jpg/250px-Donald_Trump_official_portrait.jpg' , CURRENT_TIMESTAMP),
  ('佐藤花子', 'sato_hanako', 'sato@example.com', crypt('securepass456', gen_salt('bf')), 27, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Donald_Trump_official_portrait.jpg/250px-Donald_Trump_official_portrait.jpg' , CURRENT_TIMESTAMP - INTERVAL '1 day'),
  ('鈴木一郎', 'suzuki_ichiro', 'suzuki@example.com', crypt('strongpw789', gen_salt('bf')), 40, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Donald_Trump_official_portrait.jpg/250px-Donald_Trump_official_portrait.jpg' , CURRENT_TIMESTAMP - INTERVAL '2 days'),
  ('田中美咲', 'tanaka_misaki', 'tanaka@example.com', crypt('safepassword321', gen_salt('bf')), 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Donald_Trump_official_portrait.jpg/250px-Donald_Trump_official_portrait.jpg' , CURRENT_TIMESTAMP - INTERVAL '3 days'),
  ('伊藤健太', 'ito_kenta', 'ito@example.com', crypt('securepw987', gen_salt('bf')), 23, 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Donald_Trump_official_portrait.jpg/250px-Donald_Trump_official_portrait.jpg' , CURRENT_TIMESTAMP - INTERVAL '4 days');

-- posts table
INSERT INTO posts 
(uid, user_id, url, title, city_id, description, camera_id, latlng, snap_time, angle, iso, f_value, shutter_speed) VALUES
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Sunset in Tokyo', 1, 'Beautiful sunset view from Tokyo Tower', 1, ST_SetSRID(ST_MakePoint(139.7456, 35.6586), 4326)::geography, '2024-08-28 18:30:00+09', 0, '100', 5.6, '1/250'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Central Park in Spring', 2, 'Cherry blossoms in Central Park', 2, ST_SetSRID(ST_MakePoint(-73.9654, 40.7829), 4326)::geography, '2024-08-28 10:00:00-04', 45, '200', 4.0, '1/500');