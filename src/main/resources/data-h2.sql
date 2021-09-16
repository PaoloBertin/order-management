INSERT INTO role(id, name)
    VALUES (1, 'ROLE_USER'),
           (2, 'ROLE_ADMIN')
    ;

INSERT INTO addresses(id, city, street, house_number, zip_code, country, state, firstname, lastname)
    VALUES(  1, 'Battaglia Terme',   'Via A. Ecompany',  '8', '35041', 'Padova', 'Italy',     'User',    'User'),
          (  2, 'Battaglia Terme',   'Via U. Ecompany',  '7', '35041', 'Padova', 'Italy',    'Admin',   'Admin'),
          (  3, 'Battaglia Terme',   'Vicolo Pio X',    '11', '35041', 'Padova', 'Italy',    'Mario',   'Rossi'),
          (  4, 'Battaglia Terme',   'Via A. Volta',     '7', '35041', 'Padova', 'Italy', 'Giuseppe', 'Ferrari'),
          (  5, 'Battaglia Terme',   'Via A. Ecompany',  '8', '35041', 'Padova', 'Italy',     'User',    'User'),
          (  6, 'Battaglia Terme',   'Via U. Ecompany',  '7', '35041', 'Padova', 'Italy',    'Admin',   'Admin'),
          (  7, 'Battaglia Terme',   'Vicolo Pio X',    '11', '35041', 'Padova', 'Italy',    'Mario',   'Rossi'),
          (  8, 'Battaglia Terme',   'Via A. Volta',     '7', '35041', 'Padova', 'Italy', 'Giuseppe', 'Ferrari')
    ;

INSERT INTO contacts(id, email, cellular, landline_phone)
    VALUES (  1, 'user@email.com',    '3628323289', '0429776337'),
           (  2, 'admin@email.com',   '3628323299', '0429776339'),
           (  3, 'rossi@email.com',   '3458323286', '0497793348'),
           (  4, 'ferrari@email.com', '3628323287', '0429776335')
    ;

INSERT INTO customers (id, firstname, lastname, username, password, header_addres_id, delivery_addres_id)
    VALUES (1, 'User',     'User',    'user@email.com',    'user',    1, 5),
           (2, 'Admin',    'Admin',   'admin@email.com',   'admin',   2, 6),
           (3, 'Mario',    'Rossi',   'rossi@email.com',   'rossi',   3, 7),
           (4, 'Giuseppe', 'Ferrari', 'ferrari@email.com', 'ferrari', 4, 8)
    ;

INSERT INTO customer_role(customer_id, role_id)
    VALUES (1, 1),
           (2, 2),
           (3, 1),
           (4, 1)
    ;

INSERT INTO categories(id, name)
    VALUES(1, 'Specialità'),
          (2, 'Pizze'),
          (3, 'Birre'),
          (4, 'Bar'),
          (5, 'Bibite')
    ;

INSERT INTO products(id, name, product_code, price, category_id, description)
    VALUES( 1, 'Florinda',        'P0001',  9.00, 1, 'Focaccia bianca all’olio Evo e origano di Sicilia. Fuori cottura: rosmarino, bresaola, rucolina selvatica e pomodorini freschi.'),
          ( 2, 'La Ravelllese',   'P0002', 10.00, 1, 'Conserva di pomodorino giallo Corbarino DOP, mozzarella di bufala di Battipaglia DOP, pancetta stufata croccante e origano di Sicilia. Fuori cottura: pepe nero, pecorino grattugiato fresco.'),
          ( 3, 'A Burrat',        'P0003', 11.00, 1, 'Conserva di pomodoro San Marzano DOP. Fuori cottura: burrata di Puglia 100g, Crudo di Sauris, pomodorini secchi, rucolina selvatica.'),
          ( 4, 'Marinara',        'P0004',  5.00, 2, 'Pomodoro, aglio, basilico fresco, olio Evo, origano di Sicilia'),
          ( 5, 'Margherita',      'P0005',  6.00, 2, 'Pomodoro, mozzarella, basilico fresco, olio Evo.'),
          ( 6, 'Cotto e Funghi',  'P0006',  7.50, 2, 'Pomodoro, mozzarella, funghi champignon, prosciutto cotto al naturale.'),
          ( 7, 'Diavola',         'P0007',  7.00, 2, 'Pomodoro, mozzarella, salamino piccante, basilico fresco.'),
          ( 8, 'Caprcciosa',      'P0008',  8.00, 2, 'Pomodoro, mozzarella, funghi champignon, carciofi, prosciutto cotto naturale.'),
          ( 9, 'Viennese',        'P0009',  7.00, 2, 'Pomodoro, mozzarella, wurstel.'),
          (10, 'Ortolana',        'P0010',  7.00, 2, 'Pomodoro, mozzarella, *melanzane, *zucchine, *peperoni.'),
          (11, 'Formaggi',        'P0011',  7.50, 2, 'Mozzarella, brie, gorgonzola. Fuori cottura: Grana Padano e Pecorino grattugiati freschi'),
          (12, 'Speck e Brie',    'P0012',  7.50, 2, 'Pomodoro, mozzarella, brie e speck'),
          (13, 'Jattura 33cl',    'P0013',  4.50, 3, 'Ramata carica - percentuale di malto wishky affumicato e caramello,'),
          (14, 'Paliat 33cl',     'P0014',  4.50, 3, 'Nera - 9 sfumature di malti, 18 settimane di maturazione, impatto corposo e maltato'),
          (15, 'Guerrilla 40cl',  'P0015',  6.00, 3, 'IPA - Secca con finale amaro e persistente.'),
          (16, 'Audace 75cl',     'P0016', 13.00, 3, 'Chiara forte, toni agrumati, sapore secco e deciso'),
          (17, 'Nebra 75cl',      'P0017', 13.00, 3, 'Ambrata con aromi floreali, toni dolci e leggeri'),
          (18, 'Caffè',           'P0018',  1.50, 4, ''),
          (19, 'Limoncello',      'P0019',  3.00, 4, ''),
          (20, 'Amaro Sambuca',   'P0020',  3.50, 4, ''),
          (21, 'Acqua Naturale',  'P0021',  2.00, 5, 'Acqua Panna – ½ litro'),
          (22, 'Acqua Frizzante', 'P0022',  2.00, 5, 'San Pellegrino – ½ litro'),
          (23, 'Coca Cola',       'P0023',  3.00, 5, '0,33cl in vetro'),
          (34, 'The alla pesca',  'P0024',  3.50, 5, '0,35cl in vetro')
    ;

INSERT INTO line_items(id, product_id, quantity)
    VALUES( 1,  4, 1),
          ( 2,  5, 2),
          ( 3,  6, 1),
          ( 4, 10, 1),
          ( 5, 13, 1),
          ( 6,  8, 2),
          ( 7, 12, 1),
          ( 8,  9, 1),
          ( 9, 11, 2),
          (10,  7, 1),
          (11,  1, 1),
          (12, 16, 1),
          (13, 15, 1),
          (14,  2, 1),
          (15,  6, 2),
          (16, 17, 1),
          (17,  5, 1),
          (18, 12, 1),
          (19, 20, 1),
          (20,  5, 2),
          (21, 20, 1),
          (22,  6, 1),
          (23, 14, 1)
    ;

INSERT INTO orders(id, order_date, total_amount, customer_id)
    VALUES( 1, '2018-10-10 14:07:00',  17.00, 3),
          ( 2, '2018-10-10 09:13:00',  19.00, 3),
          ( 3, '2018-10-10 12:05:00',  16.00, 3),
          ( 4, '2018-10-10 11:17:00',   7.50, 3),
          ( 5, '2018-10-12 09:24:00',   7.00, 1),
          ( 6, '2018-10-13 15:48:00',  22.00, 1),
          ( 7, '2018-10-13 19:37:00',  28.00, 1),
          ( 8, '2018-10-13 10:16:00',  30.50, 1),
          ( 9, '2018-10-13 08:21:00',  29.00, 2),
          (10, '2018-10-14 20:47:00',  15.50, 2);

INSERT INTO order_line_item(order_id, line_item_id)
    VALUES( 1,  1),
          ( 1,  2),
          ( 2,  3),
          ( 2,  4),
          ( 2,  5),
          ( 3,  6),
          ( 4,  7),
          ( 5,  8),
          ( 6,  9),
          ( 6, 10),
          ( 7, 11),
          ( 7, 12),
          ( 7, 13),
          ( 8, 14),
          ( 8, 15),
          ( 8, 16),
          ( 9, 17),
          ( 9, 18),
          ( 9, 19),
          ( 9, 20),
          (10, 21),
          (10, 22),
          (10, 23)
    ;
