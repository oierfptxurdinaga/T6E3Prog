USE FNFS;

-- 1. TALDEAK (6 equipos de Fútbol Sala)

INSERT INTO taldeak (Talde_Izena, Sorrera_Data, Lehendakari_Izena, Lehendakari_NAN, N_Bazkideak) VALUES
('Barca FS', '1989-01-01', 'Joan', '11111111A', 4500),
('ElPozo Murcia', '1989-08-24', 'Jose Ant.', '22222222B', 3200),
('Inter Movistar', '1977-10-15', 'Jose Mari', '33333333C', 4000),
('Palma Futsal', '1998-05-20', 'Miquel', '44444444D', 2800),
('Osasuna Magna', '1978-01-01', 'Tatono', '55555555E', 2500),
('Ribera Navarra', '2001-06-14', 'Alberto', '66666666F', 1500);

-- 2. Denboraldi Bakarra

INSERT INTO denboraldia (Id_Denbo, Denbo_urtea) VALUES (1, 2026);

-- 3. Crear las 8 jornadas restantes (Jornada 3 a la 10) dentro de la temporada 1

INSERT INTO jaurdunaldia (Id_Jaurdu, Zenbakia, Id_Denbo) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1);

-- 4. Insertar los 24 partidos restantes (Todos contra todos y doble vuelta)
INSERT INTO partiduak (Id_Par, Id_Jaurdu, Talde_Lok, Talde_Bis, Result_Lok, Result_Bis, ordutegia) VALUES

-- ================== PRIMERA VUELTA ================== --

-- Jornada 1
(1, 1, 'Barca FS', 'ElPozo Murcia', 4, 2, '2026-03-14 18:00:00'),
(2, 1, 'Inter Movistar', 'Palma Futsal', 3, 3, '2026-03-14 20:00:00'),
(3, 1, 'Osasuna Magna', 'Ribera Navarra', 2, 1, '2026-03-15 12:00:00'),

-- Jornada 2
(4, 2, 'Ribera Navarra', 'Barca FS', 1, 3, '2026-03-21 18:00:00'),
(5, 2, 'ElPozo Murcia', 'Inter Movistar', 2, 2, '2026-03-21 20:00:00'),
(6, 2, 'Palma Futsal', 'Osasuna Magna', 4, 1, '2026-03-22 12:00:00'),

-- JORNADA 3
(7, 3, 'Barca FS', 'Inter Movistar', NULL, NULL, '2026-03-28 18:00:00'),
(8, 3, 'Palma Futsal', 'Ribera Navarra', NULL, NULL, '2026-03-28 20:00:00'),
(9, 3, 'Osasuna Magna', 'ElPozo Murcia', NULL, NULL, '2026-03-29 12:00:00'),

-- JORNADA 4
(10, 4, 'Palma Futsal', 'Barca FS', NULL, NULL, '2026-04-04 18:00:00'),
(11, 4, 'Inter Movistar', 'Osasuna Magna', NULL, NULL, '2026-04-04 20:00:00'),
(12, 4, 'Ribera Navarra', 'ElPozo Murcia', NULL, NULL, '2026-04-05 12:00:00'),

-- JORNADA 5
(13, 5, 'Barca FS', 'Osasuna Magna', NULL, NULL, '2026-04-11 18:00:00'),
(14, 5, 'ElPozo Murcia', 'Palma Futsal', NULL, NULL, '2026-04-11 20:00:00'),
(15, 5, 'Ribera Navarra', 'Inter Movistar', NULL, NULL, '2026-04-12 12:00:00'),

-- ================== SEGUNDA VUELTA ================== --
-- (Mismos partidos, pero cambiando quién juega en casa)

-- JORNADA 6 (Inversa de la Jornada 1)
(16, 6, 'ElPozo Murcia', 'Barca FS', NULL, NULL, '2026-04-18 18:00:00'),
(17, 6, 'Palma Futsal', 'Inter Movistar', NULL, NULL, '2026-04-18 20:00:00'),
(18, 6, 'Ribera Navarra', 'Osasuna Magna', NULL, NULL, '2026-04-19 12:00:00'),

-- JORNADA 7 (Inversa de la Jornada 2)
(19, 7, 'Barca FS', 'Ribera Navarra', NULL, NULL, '2026-04-25 18:00:00'),
(20, 7, 'Inter Movistar', 'ElPozo Murcia', NULL, NULL, '2026-04-25 20:00:00'),
(21, 7, 'Osasuna Magna', 'Palma Futsal', NULL, NULL, '2026-04-26 12:00:00'),

-- JORNADA 8 (Inversa de la Jornada 3)
(22, 8, 'Inter Movistar', 'Barca FS', NULL, NULL, '2026-05-02 18:00:00'),
(23, 8, 'Ribera Navarra', 'Palma Futsal', NULL, NULL, '2026-05-02 20:00:00'),
(24, 8, 'ElPozo Murcia', 'Osasuna Magna', NULL, NULL, '2026-05-03 12:00:00'),

-- JORNADA 9 (Inversa de la Jornada 4)
(25, 9, 'Barca FS', 'Palma Futsal', NULL, NULL, '2026-05-09 18:00:00'),
(26, 9, 'Osasuna Magna', 'Inter Movistar', NULL, NULL, '2026-05-09 20:00:00'),
(27, 9, 'ElPozo Murcia', 'Ribera Navarra', NULL, NULL, '2026-05-10 12:00:00'),

-- JORNADA 10 (Inversa de la Jornada 5)
(28, 10, 'Osasuna Magna', 'Barca FS', NULL, NULL, '2026-05-16 18:00:00'),
(29, 10, 'Palma Futsal', 'ElPozo Murcia', NULL, NULL, '2026-05-16 20:00:00'),
(30, 10, 'Inter Movistar', 'Ribera Navarra', NULL, NULL, '2026-05-17 12:00:00');

-- 5. Insertar los jugadores (Ordenados por equipo)

INSERT IGNORE INTO jokalariak (NAN, Jok_Izena, Jok_Abizena, Jaio_Data, Merka_Prezioa, Talde_Izena, Posizioa) VALUES
-- BARÇA FS
('12345678A', 'Sergio', 'Lozano', '1988-11-09', 500000.00, 'Barca FS', 'Ala'),
('23456789B', 'Didac', 'Plana', '1990-05-22', 300000.00, 'Barca FS', 'Portero'),
('10000001A', 'Miquel', 'Feixas', '1997-09-04', 150000.00, 'Barca FS', 'Portero'),
('10000002B', 'Antonio', 'Perez', '2000-10-19', 250000.00, 'Barca FS', 'Cierre'),
('10000003C', 'Andre', 'Coelho', '1993-10-30', 300000.00, 'Barca FS', 'Cierre'),
('10000004D', 'Erick', 'Mendonca', '1995-07-21', 350000.00, 'Barca FS', 'Cierre'),
('10000005E', 'Adolfo', 'Fernandez', '1993-05-19', 400000.00, 'Barca FS', 'Ala'),
('10000006F', 'Dyego', 'Zuffo', '1989-08-05', 450000.00, 'Barca FS', 'Ala'),
('10000007G', 'Matheus', 'Rodrigues', '1996-10-03', 380000.00, 'Barca FS', 'Ala'),
('10000008H', 'Juanjo', 'Catela', '1995-04-14', 320000.00, 'Barca FS', 'Ala'),
('10000009I', 'Jean Pierre', 'Pito', '1991-11-06', 600000.00, 'Barca FS', 'Pívot'),
('10000010J', 'Carlos V.', 'Ferrao', '1990-10-29', 550000.00, 'Barca FS', 'Pívot'),

-- ELPOZO MURCIA
('34567890C', 'Marcel', 'Marques', '1996-07-26', 450000.00, 'ElPozo Murcia', 'Ala'),
('45678901D', 'Juanjo', 'Angosto', '1985-08-19', 200000.00, 'ElPozo Murcia', 'Portero'),
('20000001A', 'Edu', 'Sousa', '1996-08-12', 180000.00, 'ElPozo Murcia', 'Portero'),
('20000002B', 'Marlon', 'Araujo', '1987-12-28', 250000.00, 'ElPozo Murcia', 'Cierre'),
('20000003C', 'Felipe', 'Valerio', '1993-07-08', 280000.00, 'ElPozo Murcia', 'Cierre'),
('20000004D', 'Ricardo', 'Mayor', '2000-02-21', 150000.00, 'ElPozo Murcia', 'Cierre'),
('20000005E', 'Fabricio', 'Gadeia', '1988-06-14', 420000.00, 'ElPozo Murcia', 'Ala'),
('20000006F', 'Artem', 'Niyazov', '1996-07-30', 350000.00, 'ElPozo Murcia', 'Ala'),
('20000007G', 'Taynan', 'Da Silva', '1993-02-12', 400000.00, 'ElPozo Murcia', 'Ala'),
('20000008H', 'David', 'Alvarez', '1997-11-03', 200000.00, 'ElPozo Murcia', 'Ala'),
('20000009I', 'Rafa', 'Santos', '1990-09-25', 380000.00, 'ElPozo Murcia', 'Pívot'),
('20000010J', 'Bruno', 'Taffy', '1990-03-30', 300000.00, 'ElPozo Murcia', 'Pívot'),

-- INTER MOVISTAR
('56789012E', 'Jesus', 'Herrero', '1986-11-04', 250000.00, 'Inter Movistar', 'Portero'),
('67890123F', 'Raul', 'Gomez', '1995-10-25', 400000.00, 'Inter Movistar', 'Pívot'),
('30000001A', 'Jesus', 'Garcia', '2000-12-05', 120000.00, 'Inter Movistar', 'Portero'),
('30000002B', 'Jose', 'Raya', '1997-05-08', 220000.00, 'Inter Movistar', 'Cierre'),
('30000003C', 'Tomaz', 'Braga', '1990-09-13', 250000.00, 'Inter Movistar', 'Cierre'),
('30000004D', 'Sepe', 'Gomez', '1991-01-22', 180000.00, 'Inter Movistar', 'Cierre'),
('30000005E', 'Cecilio', 'Morales', '1992-07-06', 320000.00, 'Inter Movistar', 'Ala'),
('30000006F', 'Rubi', 'Lemos', '1993-08-13', 250000.00, 'Inter Movistar', 'Ala'),
('30000007G', 'Tomas', 'Drahovsky', '1992-10-07', 450000.00, 'Inter Movistar', 'Ala'),
('30000008H', 'Jovan', 'Lazarevic', '1997-09-11', 280000.00, 'Inter Movistar', 'Ala'),
('30000009I', 'Kaito', 'Yamada', '2000-03-30', 150000.00, 'Inter Movistar', 'Ala'),
('30000010J', 'Rafael', 'Fits', '1992-05-23', 380000.00, 'Inter Movistar', 'Pívot'),

-- PALMA FUTSAL
('78901234G', 'Mario', 'Rivillos', '1989-12-13', 350000.00, 'Palma Futsal', 'Ala'),
('89012345H', 'Carlos', 'Barron', '1987-10-01', 150000.00, 'Palma Futsal', 'Portero'),
('40000001A', 'Luan', 'Muller', '1993-03-17', 280000.00, 'Palma Futsal', 'Portero'),
('40000002B', 'Rómulo', 'Alves', '1986-09-28', 180000.00, 'Palma Futsal', 'Cierre'),
('40000003C', 'Vilian', 'Lourenco', '1989-08-04', 200000.00, 'Palma Futsal', 'Cierre'),
('40000004D', 'Marcelo', 'Chaguinha', '1988-07-25', 300000.00, 'Palma Futsal', 'Ala'),
('40000005E', 'Moslem', 'Oladghobad', '1995-11-22', 400000.00, 'Palma Futsal', 'Ala'),
('40000006F', 'Cleber', 'Souza', '1997-01-06', 420000.00, 'Palma Futsal', 'Ala'),
('40000007G', 'Salar', 'Aghapour', '2000-03-07', 250000.00, 'Palma Futsal', 'Ala'),
('40000008H', 'Fabinho', 'Gomes', '2001-11-26', 150000.00, 'Palma Futsal', 'Ala'),
('40000009I', 'Hossein', 'Tayebi', '1988-09-29', 450000.00, 'Palma Futsal', 'Pívot'),
('40000010J', 'Bruno', 'Gomes', '1996-05-15', 350000.00, 'Palma Futsal', 'Pívot'),

-- OSASUNA MAGNA
('90123456I', 'Roberto', 'Martil', '1987-03-15', 180000.00, 'Osasuna Magna', 'Cierre'),
('01234567J', 'Asier', 'Llamas', '1990-11-02', 200000.00, 'Osasuna Magna', 'Portero'),
('50000001A', 'Alejandro', 'Palazon', '1998-02-14', 100000.00, 'Osasuna Magna', 'Portero'),
('50000002B', 'Jhonatan', 'Linhares', '1993-04-12', 160000.00, 'Osasuna Magna', 'Cierre'),
('50000003C', 'Igor', 'Korsun', '1995-09-08', 190000.00, 'Osasuna Magna', 'Cierre'),
('50000004D', 'Toni', 'Escribano', '2002-06-25', 90000.00, 'Osasuna Magna', 'Cierre'),
('50000005E', 'Dani', 'Zurdo', '2000-08-11', 140000.00, 'Osasuna Magna', 'Ala'),
('50000006F', 'Andres', 'Geraghty', '1998-01-22', 150000.00, 'Osasuna Magna', 'Ala'),
('50000007G', 'Juninho', 'Silva', '1997-03-16', 180000.00, 'Osasuna Magna', 'Ala'),
('50000008H', 'Carlos', 'Vallejo', '2001-09-05', 110000.00, 'Osasuna Magna', 'Ala'),
('50000009I', 'Leo', 'Cafe', '1999-12-01', 220000.00, 'Osasuna Magna', 'Pívot'),
('50000010J', 'Dani', 'Saldise', '1995-07-08', 300000.00, 'Osasuna Magna', 'Pívot'),

-- RIBERA NAVARRA
('11223344K', 'Terry', 'Prestes', '1995-02-14', 320000.00, 'Ribera Navarra', 'Pívot'),
('22334455L', 'Adrian', 'Pereira', '1998-04-10', 120000.00, 'Ribera Navarra', 'Portero'),
('60000001A', 'Raul', 'Jimenez', '2001-03-22', 80000.00, 'Ribera Navarra', 'Portero'),
('60000002B', 'David', 'Garcia', '1989-10-14', 150000.00, 'Ribera Navarra', 'Cierre'),
('60000003C', 'Dani', 'Fernandez', '2000-01-09', 110000.00, 'Ribera Navarra', 'Cierre'),
('60000004D', 'Pedro', 'Espin', '2002-05-30', 70000.00, 'Ribera Navarra', 'Cierre'),
('60000005E', 'Carlos', 'Bartolome', '1999-07-18', 140000.00, 'Ribera Navarra', 'Ala'),
('60000006F', 'Anas', 'El Ayyane', '1992-10-30', 200000.00, 'Ribera Navarra', 'Ala'),
('60000007G', 'Nacho', 'Gomez', '1998-12-12', 130000.00, 'Ribera Navarra', 'Ala'),
('60000008H', 'Lucas', 'Tripodi', '1994-06-18', 250000.00, 'Ribera Navarra', 'Ala'),
('60000009I', 'Uge', 'Hernandez', '1996-04-03', 180000.00, 'Ribera Navarra', 'Ala'),
('60000010J', 'Gabriel', 'Vasquez', '1997-08-25', 210000.00, 'Ribera Navarra', 'Pívot');
