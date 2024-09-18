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
(uuid_generate_v4(), 1, 'https://scontent-nrt1-2.cdninstagram.com/v/t51.2885-19/116311433_586070338750414_5084374547060680370_n.jpg?_nc_ht=scontent-nrt1-2.cdninstagram.com&_nc_cat=110&_nc_ohc=8kvmxzJmCgwQ7kNvgEEAeih&edm=AEhyXUkBAAAA&ccb=7-5&oh=00_AYD09lSlSfCpnkEZryAA9B9tT59iCMGYROZwbPE7ZfTZeQ&oe=66D61397&_nc_sid=8f1549', 'Sunset in Tokyo', 1, 'Beautiful sunset view from Tokyo Tower', 1, ST_SetSRID(ST_MakePoint(139.7456, 35.6586), 4326)::geography, '2024-08-28 18:30:00+09', 0, '100', 5.6, '1/250'),
(uuid_generate_v4(), 2, 'https://scontent-nrt1-2.cdninstagram.com/v/t51.29350-15/417187053_909491007381823_8890119924504756456_n.jpg?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xNDQweDE0NDAuc2RyLmYyOTM1MC5kZWZhdWx0X2ltYWdlIn0&_nc_ht=scontent-nrt1-2.cdninstagram.com&_nc_cat=107&_nc_ohc=lE7eJ-04EO0Q7kNvgHZJ0R8&edm=AEhyXUkBAAAA&ccb=7-5&ig_cache_key=MzI3NTcyNzIxMzQyNDk3Mjg5Ng%3D%3D.2-ccb7-5&oh=00_AYANg0ZF4HVmfrKhEtRoyMkf6wbXoGcvswr7u9L5YcUObQ&oe=66D5FCDA&_nc_sid=8f1549', 'Central Park in Spring', 2, 'Cherry blossoms in Central Park', 2, ST_SetSRID(ST_MakePoint(-73.9654, 40.7829), 4326)::geography, '2024-08-28 10:00:00-04', 45, '200', 4.0, '1/500');