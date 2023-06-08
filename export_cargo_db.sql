/*ユーザーテーブル*/
CREATE TABLE users (
  id serial primary key,
  login_id varchar(255) unique not null,
  password varchar(255) not null,
  name varchar(255) not null,
  responsible_id int not null
);

/*担当テーブル*/
CREATE TABLE responsible (
  id serial primary key,
  name varchar(255) not null
);

/*航空会社テーブル*/
CREATE TABLE airport (
  id serial primary key,
  prefix int not null,
  letter_code varchar(255) not null,
  name varchar(255) not null,
  country varchar(255) not null,
  mon varchar(255),
  tue varchar(255),
  wed varchar(255),
  thu varchar(255),
  fri varchar(255),
  sat varchar(255),
  sun varchar(255)
);

/*向け地テーブル*/
CREATE TABLE destination (
  id serial primary key,
  name varchar(255) not null,
  responsible_id int not null
);

/*貨物テーブル*/
CREATE TABLE cargo (
  id serial primary key,
  name varchar(255) not null,
  destination_id int not null,
  close_date varchar(255),
  arrival_date varchar(255),
  lithium int not null,
  height int,
  width int,
  depth int,
  weight int,
  description varchar(255),
  reserve_status int not null,
  created_at timestamp,
  updated_at timestamp
);

/*航空会社データ追加*/
INSERT INTO airport (prefix, letter_code, name, country, mon, tue, wed, thu, fri, sat, sun)
VALUES
  (205, 'JL', 'Japan Airlines', 'Japan', '0/3', '0/0', '0/3', '0/0', '0/3', '0/0', '0/3'),
  (102, 'AA', 'American Airlines', 'United States', '1/6', '1/6', '1/6', '1/6', '1/6', '0/0', '0/0'),
  (106, 'AF', 'Air France', 'France', '1/9', '0/0', '1/9', '0/0', '1/9', '0/0', '1/9'),
  (301, 'EK', 'Emirates', 'United Arab Emirates', '0/0', '0/0', '1/6', '1/6', '1/6', '0/0', '0/0'),
  (309, 'SQ', 'Singapore Airlines', 'Singapore', '0/4', '0/4', '0/4', '0/4', '0/4', '1/9', '0/4');


/*貨物データ追加*/
INSERT INTO cargo (name,destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)
VALUES
  ('Semiconductor',1, '2023-06-10', '2023-06-15', 1, 50, 40, 30, 100, 'Semiconductor shipment', 1, NOW(), NOW()),
  ('Electronics',2, '2023-06-12', '2023-06-17', 1, 60, 50, 40, 150, 'Electronics goods', 2, NOW(), NOW()),
  ('Automobile Parts',4, '2023-06-14', '2023-06-19', 2, 70, 60, 50, 200, 'Automobile parts and accessories', 1, NOW(), NOW()),
  ('Textiles',7, '2023-06-16', '2023-06-21', 2, 80, 70, 60, 250, 'Textile products and fabrics', 2, NOW(), NOW()),
  ('Food Products',10, '2023-06-18', '2023-06-23', 2, 90, 80, 70, 300, 'Food and beverage items', 1, NOW(), NOW()),
  ('Medical Supplies',11, '2023-06-20', '2023-06-25', 1, 100, 90, 80, 350, 'Medical equipment and supplies', 2, NOW(), NOW()),
  ('Chemicals',3, '2023-06-22', '2023-06-27', 1, 110, 100, 90, 400, 'Chemical substances and compounds', 1, NOW(), NOW()),
  ('Apparel',4, '2023-06-24', '2023-06-29', 1, 120, 110, 100, 450, 'Clothing and fashion items', 2, NOW(), NOW()),
  ('Furniture',2, '2023-06-26', '2023-07-01', 1, 130, 120, 110, 500, 'Furniture and home decor', 1, NOW(), NOW()),
  ('Machinery', 8, '2023-06-28', '2023-07-03', 1, 140, 130, 120, 550, 'Industrial machinery and equipment', 2, NOW(), NOW());

INSERT INTO cargo (name, destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)
VALUES
  ('Toys', 9, '2023-07-04', '2023-07-09', 2, 80, 60, 40, 100, 'Children''s toys and games', 1, NOW(), NOW()),
  ('Jewelry', 3, '2023-07-06', '2023-07-11', 2, 90, 70, 50, 110, 'Precious stones and accessories', 2, NOW(), NOW()),
  ('Sporting Goods', 7, '2023-07-08', '2023-07-13', 2, 100, 80, 60, 120, 'Sports equipment and gear', 1, NOW(), NOW()),
  ('Musical Instruments', 11, '2023-07-10', '2023-07-15', 2, 110, 90, 70, 130, 'Musical instruments and accessories', 2, NOW(), NOW()),
  ('Stationery', 2, '2023-07-12', '2023-07-17', 2, 120, 100, 80, 140, 'Office supplies and writing materials', 1, NOW(), NOW()),
  ('Home Appliances', 5, '2023-07-14', '2023-07-19', 1, 130, 110, 90, 150, 'Various home appliances', 2, NOW(), NOW()),
  ('Tools', 8, '2023-07-16', '2023-07-21', 2, 140, 120, 100, 160, 'Hand tools and power tools', 1, NOW(), NOW()),
  ('Garden Supplies', 6, '2023-07-18', '2023-07-23', 2, 150, 130, 110, 170, 'Gardening tools and equipment', 2, NOW(), NOW()),
  ('Pet Products', 10, '2023-07-20', '2023-07-25', 2, 160, 140, 120, 180, 'Supplies for pets and animals', 1, NOW(), NOW()),
  ('Artwork', 4, '2023-07-22', '2023-07-27', 2, 170, 150, 130, 190, 'Paintings and artistic creations', 2, NOW(), NOW());


/*担当データの追加*/
INSERT INTO responsible (name)
VALUES ('アジア担当'),
       ('ヨーロッパ担当'),
       ('南米北米担当');

/*向け地データの追加*/
INSERT INTO destination (name, responsible_id)
VALUES
  ('Tokyo (HND)', 1),
  ('Seoul (ICN)', 1),
  ('Beijing (PEK)', 1),
  ('Bangkok (BKK)', 1),
  ('Singapore (SIN)', 1),
  ('Mumbai (BOM)', 1),
  ('London (LHR)', 2),
  ('Paris (CDG)', 2),
  ('Amsterdam (AMS)', 2),
  ('Madrid (MAD)', 2),
  ('Cairo (CAI)', 2),
  ('New York (JFK)', 3),
  ('Los Angeles (LAX)', 3),
  ('Toronto (YYZ)', 3),
  ('Sao Paulo (GRU)', 3),
  ('Mexico City (MEX)', 3),
  ('Buenos Aires (EZE)', 3),
  ('Sydney (SYD)', 1),
  ('Auckland (AKL)', 1),
  ('Dubai (DXB)', 1);
  
  /*ユーザーデータの追加*/
  INSERT INTO users (login_id, password, name, responsible_id)
VALUES
  ('user1', 'password1', 'John Smith', 1),
  ('user2', 'password2', 'Emily Johnson', 2),
  ('user3', 'password3', 'Michael Davis', 3),
  ('user4', 'password4', 'Jessica Wilson', 1),
  ('user5', 'password5', 'David Taylor', 2);
  
  INSERT INTO cargo (name, destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)
VALUES
  ('Black Electronics', 13, '2023-07-22', '2023-07-21', 2, 180, 160, 140, 200, 'Electronic devices and gadgets', 1, NOW(), NOW()),
  ('Main Books', 12, '2023-07-26', '2023-07-31', 2, 190, 170, 150, 210, 'Books and reading materials', 2, NOW(), NOW()),
  ('Sevelal Clothing', 14, '2023-07-28', '2023-08-02', 2, 200, 180, 160, 220, 'Clothing and fashion items', 1, NOW(), NOW()),
  ('Vegetable Food and Beverages', 16, '2023-07-30', '2023-08-04', 1, 210, 190, 170, 230, 'Food products and drinks', 2, NOW(), NOW()),
  ('Beauty Products', 16, '2023-9-01', '2023-10-06', 2, 220, 200, 180, 240, 'Cosmetics and beauty items', 1, NOW(), NOW()),
  ('Big Furniture', 14, '2023-10-03', '2023-11-08', 2, 230, 210, 190, 250, 'Furniture and home decor', 2, NOW(), NOW()),
  ('Small Automotive Parts', 15, '2023-05-05', '2023-06-10', 2, 240, 220, 200, 260, 'Parts and accessories for vehicles', 1, NOW(), NOW()),
  ('For Child Healthcare Products', 12, '2023-10-07', '2023-11-12', 2, 250, 230, 210, 270, 'Medical supplies and health products', 2, NOW(), NOW()),
  ('Cheap Toiletries', 14, '2023-11-09', '2023-11-14', 2, 260, 240, 220, 280, 'Personal care and hygiene items', 1, NOW(), NOW()),
  ('Big Electrical Equipment', 13, '2023-11-11', '2023-11-16', 2, 270, 250, 230, 290, 'Electrical tools and equipment', 2, NOW(), NOW());


SELECT c.close_date, c.name, d.name destination_name, c.lithium, c.reserve_status FROM cargo c INNER JOIN destination d ON c.destination_id = d.id WHERE d.responsible_id = 3 ORDER BY c.id DESC;

SELECT c.id, c.close_date, c.name, d.name AS destination_name, c.lithium, c.reserve_status FROM cargo c INNER JOIN destination d ON c.destination_id = d.id WHERE d.responsible_id = 3 ORDER BY c.close_date DESC;

INSERT INTO airport (prefix, letter_code, name, country, mon, tue, wed, thu, fri, sat, sun)
VALUES
  (309, 'SQ', 'Singapore Airlines', 'Singapore', '0/4', '0/4', '0/4', '0/4', '0/4', '1/9', '0/4');
  
  delete from cargo;
  

/*貨物データ追加*/
INSERT INTO cargo (name,destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)
VALUES
  ('Semiconductor',1, '2023-06-10', '2023-06-15', 1, 5, 4, 3, 100, 'Semiconductor shipment', 1, NOW(), NOW()),
  ('Electronics',2, '2023-06-12', '2023-06-17', 1, 6, 5, 4, 150, 'Electronics goods', 2, NOW(), NOW()),
  ('Automobile Parts',4, '2023-06-14', '2023-06-19', 2, 3, 6, 5, 200, 'Automobile parts and accessories', 1, NOW(), NOW()),
  ('Textiles',7, '2023-06-16', '2023-06-21', 2, 2, 1, 6, 250, 'Textile products and fabrics', 2, NOW(), NOW()),
  ('Food Products',10, '2023-06-18', '2023-06-23', 2, 3, 1, 4, 300, 'Food and beverage items', 1, NOW(), NOW()),
  ('Medical Supplies',11, '2023-06-20', '2023-06-25', 1, 1, 3, 2, 350, 'Medical equipment and supplies', 2, NOW(), NOW()),
  ('Chemicals',3, '2023-06-22', '2023-06-27', 1, 1, 1, 5, 400, 'Chemical substances and compounds', 1, NOW(), NOW()),
  ('Apparel',4, '2023-06-24', '2023-06-29', 1, 1, 1, 1, 450, 'Clothing and fashion items', 2, NOW(), NOW()),
  ('Furniture',2, '2023-06-26', '2023-07-01', 1, 1, 2, 1, 500, 'Furniture and home decor', 1, NOW(), NOW()),
  ('Machinery', 8, '2023-06-28', '2023-07-03', 1, 4, 3, 1, 550, 'Industrial machinery and equipment', 2, NOW(), NOW());

INSERT INTO cargo (name, destination_id, close_date, arrival_date, lithium, height, width, depth, weight, description, reserve_status, created_at, updated_at)
VALUES
  ('Toys', 9, '2023-07-04', '2023-07-09', 2, 4, 5, 4, 100, 'Children''s toys and games', 1, NOW(), NOW()),
  ('Jewelry', 3, '2023-07-06', '2023-07-11', 2, 1, 4, 5, 110, 'Precious stones and accessories', 2, NOW(), NOW()),
  ('Sporting Goods', 7, '2023-07-08', '2023-07-13', 2, 1, 2, 6, 120, 'Sports equipment and gear', 1, NOW(), NOW()),
  ('Musical Instruments', 11, '2023-07-10', '2023-07-15', 2, 1, 2, 1, 130, 'Musical instruments and accessories', 2, NOW(), NOW()),
  ('Stationery', 2, '2023-07-12', '2023-07-17', 2, 1, 1, 2, 140, 'Office supplies and writing materials', 1, NOW(), NOW()),
  ('Home Appliances', 5, '2023-07-14', '2023-07-19', 1, 1, 1, 3, 150, 'Various home appliances', 2, NOW(), NOW()),
  ('Tools', 8, '2023-07-16', '2023-07-21', 2, 1, 1, 1, 160, 'Hand tools and power tools', 1, NOW(), NOW()),
  ('Garden Supplies', 6, '2023-07-18', '2023-07-23', 2, 1, 3, 1, 170, 'Gardening tools and equipment', 2, NOW(), NOW()),
  ('Pet Products', 10, '2023-07-20', '2023-07-25', 2, 1, 1, 1, 180, 'Supplies for pets and animals', 1, NOW(), NOW()),
  ('Artwork', 4, '2023-07-22', '2023-07-27', 2, 5, 1, 1, 190, 'Paintings and artistic creations', 2, NOW(), NOW());
  
  delete from users;
