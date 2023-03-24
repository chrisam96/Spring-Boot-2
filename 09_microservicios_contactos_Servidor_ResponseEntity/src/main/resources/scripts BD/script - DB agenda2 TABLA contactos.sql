/* Para MySQL, una DATABASE = SCHEMA
*/
CREATE DATABASE agenda2 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish2_ci;
-- CREATE SCHEMA `agenda` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci ;

-- Para usar/escoger la DB en la que se trabaja
 USE agenda2;

CREATE TABLE contactos (
	idContacto int(10) unsigned NOT NULL AUTO_INCREMENT,
    nombre varchar(45) NOT NULL,
    email varchar(45) NOT NULL,
    edad int(10) unsigned NOT NULL,
    PRIMARY KEY(idContacto)
);

-- Datos generados en mockaroo.com
insert into contactos (nombre, email, edad) values ('Luis Berzins', 'lberzins0@telegraph.co.uk', 23);
insert into contactos (nombre, email, edad) values ('Lizabeth Robart', 'lrobart1@gizmodo.com', 22);
insert into contactos (nombre, email, edad) values ('Baryram Arthurs', 'barthurs2@addtoany.com', 22);
insert into contactos (nombre, email, edad) values ('Anselma Antill', 'aantill3@tamu.edu', 20);
insert into contactos (nombre, email, edad) values ('Gusta Harry', 'gharry4@smugmug.com', 20);
insert into contactos (nombre, email, edad) values ('Ros Ivic', 'rivic5@cam.ac.uk', 20);
insert into contactos (nombre, email, edad) values ('Jimmie Paoloni', 'jpaoloni6@miibeian.gov.cn', 21);
insert into contactos (nombre, email, edad) values ('Celestyn Zorzutti', 'czorzutti7@livejournal.com', 23);
insert into contactos (nombre, email, edad) values ('Inessa Gurnett', 'igurnett8@godaddy.com', 18);
insert into contactos (nombre, email, edad) values ('Worth Hamlett', 'whamlett9@arstechnica.com', 29);

-- Datos proporcionados por el ejemplo -> 18 registros
INSERT INTO `contactos` VALUES 
(476,'Antonio Martín Sierra','aaaaaaa',333),
(483,'rest','spring@gmail',7777),
(487,'hiber','hiber',33),
(489,'post cliente jersey','jersey@gmail',66),
(493,'cliente easy','easy@gmail',99),
(501,'curso android neoris','curso@gmail',55),
(502,'profe','aaaa@google',22),
(503,'datasource','aaaq@ggg',22),
(504,'actualizacion','java20@gmail',200),
(505,'ful','fulg@gmail',800),
(506,'probando contactos','prueba@.gmail',100),
(508,'nuevo','altag@gmail',100),
(509,'nuevo','altauuuug@gmail',100),
(510,'profeprueba','emailmio@gmail',44),
(515,'eureka','eureka@gmail',44),
(516,'cloud','cloud@gmail',88),
(517,'sa','ds',22),
(518,'sa','ds',22);
