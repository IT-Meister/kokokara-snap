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
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Shibuya Crossing', 1, 'Busy pedestrian crossing', 2, ST_SetSRID(ST_MakePoint(139.7036, 35.6580), 4326)::geography, '2024-08-28 18:00:00+09', 30, '400', 4.0, '1/500'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Sensoji Temple', 1, 'Historic temple in Asakusa', 1, ST_SetSRID(ST_MakePoint(139.7966, 35.7117), 4326)::geography, '2024-08-28 12:00:00+09', 0, '200', 8.0, '1/125'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Tokyo Skytree', 1, 'View from the Skytree', 1, ST_SetSRID(ST_MakePoint(139.8107, 35.7101), 4326)::geography, '2024-08-28 17:30:00+09', 15, '800', 5.6, '1/30'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Meiji Shrine', 1, 'Beautiful shrine in Tokyo', 1, ST_SetSRID(ST_MakePoint(139.6993, 35.6764), 4326)::geography, '2024-08-28 09:30:00+09', 0, '100', 11.0, '1/500'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Odaiba Beach', 1, 'Relaxing view of Odaiba', 2, ST_SetSRID(ST_MakePoint(139.7765, 35.6272), 4326)::geography, '2024-08-28 15:00:00+09', 0, '200', 2.8, '1/125'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Rainbow Bridge at Night', 1, 'Bridge illuminated at night', 1, ST_SetSRID(ST_MakePoint(139.7621, 35.6267), 4326)::geography, '2024-08-28 19:00:00+09', 0, '1600', 2.0, '1/30'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Tokyo Station', 1, 'View of Tokyo Station', 2, ST_SetSRID(ST_MakePoint(139.7671, 35.6812), 4326)::geography, '2024-08-28 10:00:00+09', 20, '400', 4.5, '1/125'),
(uuid_generate_v4(), 1, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Roppongi Hills', 1, 'View from Roppongi Hills', 1, ST_SetSRID(ST_MakePoint(139.7300, 35.6600), 4326)::geography, '2024-08-28 16:30:00+09', 45, '100', 5.0, '1/500'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Shinjuku Gyoen', 1, 'Beautiful park in Shinjuku', 2, ST_SetSRID(ST_MakePoint(139.7100, 35.6852), 4326)::geography, '2024-08-28 12:00:00+09', 0, '100', 8.0, '1/125'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Mount Takao', 2, 'Beautiful hiking spot', 1, ST_SetSRID(ST_MakePoint(139.2437, 35.6256), 4326)::geography, '2024-08-28 14:00:00+09', 0, '100', 5.6, '1/500'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Lake Kawaguchi', 2, 'View of Mount Fuji from Kawaguchi', 2, ST_SetSRID(ST_MakePoint(138.7669, 35.5083), 4326)::geography, '2024-08-28 08:30:00+09', 15, '200', 4.0, '1/500'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Nikko Toshogu Shrine', 2, 'Historic shrine in Nikko', 1, ST_SetSRID(ST_MakePoint(139.5984, 36.7598), 4326)::geography, '2024-08-28 11:00:00+09', 30, '400', 6.3, '1/60'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Yokohama Minato Mirai', 2, 'Night view of Yokohama', 1, ST_SetSRID(ST_MakePoint(139.6380, 35.4540), 4326)::geography, '2024-08-28 19:00:00+09', 0, '1600', 2.0, '1/60'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Hakone', 2, 'Hot springs and views of Mt. Fuji', 2, ST_SetSRID(ST_MakePoint(139.0234, 35.2329), 4326)::geography, '2024-08-28 13:00:00+09', 0, '200', 5.6, '1/125'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Narita Temple', 2, 'Historic temple in Narita', 2, ST_SetSRID(ST_MakePoint(140.3185, 35.7769), 4326)::geography, '2024-08-28 09:00:00+09', 0, '100', 8.0, '1/125'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Hitachi Seaside Park', 2, 'Beautiful flower gardens', 1, ST_SetSRID(ST_MakePoint(140.5969, 36.4032), 4326)::geography, '2024-08-28 15:30:00+09', 0, '100', 11.0, '1/250'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Chichibu', 1, 'Mountain scenery', 2, ST_SetSRID(ST_MakePoint(139.0856, 35.9917), 4326)::geography, '2024-08-28 12:00:00+09', 10, '200', 5.0, '1/500'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Oarai', 2, 'Coastal view in Ibaraki', 2, ST_SetSRID(ST_MakePoint(140.5834, 36.3131), 4326)::geography, '2024-08-28 08:00:00+09', 0, '200', 8.0, '1/250'),
(uuid_generate_v4(), 2, 'https://www.yuriko.or.jp/wp-content/themes/koikeofficial/images/header2024.png', 'Fuji-Q Highland', 1, 'Amusement park near Mt. Fuji', 1, ST_SetSRID(ST_MakePoint(138.7846, 35.4880), 4326)::geography, '2024-08-28 14:30:00+09', 45, '100', 6.3, '1/500');