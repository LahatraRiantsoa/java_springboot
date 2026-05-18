-- Insertion des catégories
INSERT INTO categorie (id, name) VALUES (1, 'Électronique');
INSERT INTO categorie (id, name) VALUES (2, 'Informatique');
INSERT INTO categorie (id, name) VALUES (3, 'Accessoires');
INSERT INTO categorie (id, name) VALUES (4, 'Périphériques');

-- Insertion des produits
INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (1, 'Clavier Mécanique RGB', 'Clavier gaming avec switches rouges', 89.99, 15, 4, '/images/clavier.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (2, 'Souris Sans Fil', 'Souris ergonomique 16000 DPI', 45.00, 25, 4, '/images/souris.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (3, 'Écran 27 pouces 4K', 'Dalle IPS avec bordures fines', 349.99, 10, 2, '/images/ecran.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (4, 'Casque Audio Bluetooth', 'Réduction de bruit active', 120.50, 8, 3, '/images/casque.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (5, 'PC Portable Gamer', 'RTX 4060, 16Go RAM, SSD 1To', 1250.00, 5, 2, '/images/pc-portable.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (6, 'Enceinte Intelligente', 'Compatible Google Assistant et Alexa', 59.00, 20, 1, '/images/enceinte.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (7, 'Câble HDMI 2.1', 'Supporte la 8K à 60Hz', 15.99, 50, 3, '/images/cable-hdmi.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (8, 'Tablette Tactile 10"', 'Écran haute résolution, 128Go', 299.00, 12, 1, '/images/tablette.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (9, 'Disque Dur Externe 2To', 'USB 3.2 haute vitesse', 75.00, 30, 2, '/images/disque-dur.jpg', NOW());

INSERT INTO product (id, name, description, price, stock_quantity, categorie_id, lien_image, created_at)
VALUES (10, 'Tapis de Souris XXL', 'Surface optimisée pour le gaming', 19.99, 40, 4, '/images/tapis.jpg', NOW());