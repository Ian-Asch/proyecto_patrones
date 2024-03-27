DROP DATABASE laca;

CREATE DATABASE laca;

USE laca;

###########################
# TRANSPORTER TABLE
###########################

CREATE TABLE transporters (
    id INT NOT NULL AUTO_INCREMENT primary key ,
    name varchar(200),
    company varchar(100)
);

###########################
# PACKAGE TABLE
###########################

CREATE TABLE package (
    id INT NOT NULL AUTO_INCREMENT primary key ,
    type varchar(50),
    weight double,
    name varchar(200),
    description varchar(500),
    price double,
    height double,
    width double
);

###########################
# COORDINATES TABLE
###########################

CREATE TABLE coordinates (
    id INT NOT NULL AUTO_INCREMENT primary key ,
    name varchar(200),
    description varchar(500),
    length double,
    altitude double
);

###########################
# ROUTES TABLE
###########################

CREATE TABLE routes (
    id INT NOT NULL AUTO_INCREMENT primary key ,
    startingPoint int NOT NULL,
    pointArrival int NOT NULL,
    name varchar(200),
    description varchar(500),
    CONSTRAINT `FK_startingPointCoordinate` FOREIGN KEY (startingPoint)
    REFERENCES coordinates(id),
     CONSTRAINT `FK_arrivalPointCoordinate` FOREIGN KEY (pointArrival)
    REFERENCES coordinates(id)
);

###########################
# INSERT PACKAGE
###########################

INSERT INTO  package (type, weight, name, description, price, height, width) VALUES ('ELECTRONIC', 40000, 'Alexa Amazon', 'Alexa is Amazon''s cloud-based voice service available on more than 100 million devices from Amazon and third-party device manufacturers. With Alexa, you can ...', 145, 70, 40);
INSERT INTO  package (type, weight, name, description, price, height, width) VALUES ('FOOD', 110000, 'Wagyu. Kobe beef', 'The American Wagyu Association (AWA) distinguishes Wagyu—now the most sought-after beef in the world—by the extensive marbling throughout the meat, which makes for a juicy, meltingly tender, and buttery experience. A bonus is that this luscious beef is also more heart-healthy than other types due to its higher ratio of mono-unsaturated fat to saturated fat. So you can feel good about spending $24 per ounce to treat yourself.', 30000, 2000, 2000);

###########################
# INSERT TRANSPORTER
###########################

INSERT INTO  transporters (name, company) VALUES ('Jose Ramirez', 'Accenture');
INSERT INTO  transporters (name, company) VALUES ('Alonso Mora', 'IBM');
INSERT INTO  transporters (name, company) VALUES ('Mario Perez', 'IBM');

###########################
# insert COORDINATES
###########################

INSERT INTO  coordinates  (name, description, length, altitude) VALUES ('Estadio Ricardo Saprissa San Jose Costa Rica', 'No hay description', 9.90244594077279 , -84.06539033058284);
INSERT INTO  coordinates   (name, description, length, altitude) VALUES ('Estadio Morera Soto Alajuela Costa Rica', 'No hay description', 9.802854 ,-84.0901548);
INSERT INTO  coordinates  (name, description, length, altitude) VALUES ('Escuela Santa Marta Cartago', 'No hay description', 9.902854 ,-81.06539033058284);
INSERT INTO  coordinates   (name, description, length, altitude) VALUES ('Sabana cementerio San jose Costa Rica', 'No hay description', 9.102854 ,-83.0901548);

###########################
# insert ROUTES
###########################

INSERT INTO  routes  (startingPoint, pointArrival, name, description) VALUES (1, 2, 'Routa Dominguez 67 el alto', 'No hay description');
INSERT INTO  routes  (startingPoint, pointArrival, name, description) VALUES (2, 3, 'Ruta 32 por paso canoas', 'No hay description');

###########################
# VIEW ROUTES
###########################

DELIMITER $$
CREATE FUNCTION CalculateDistance(lat1 DECIMAL(10, 6), lon1 DECIMAL(10, 6), lat2 DECIMAL(10, 6), lon2 DECIMAL(10, 6)) RETURNS VARCHAR(10)
BEGIN
    DECLARE radius DECIMAL(10, 2);
    DECLARE deltaLat DECIMAL(10, 6);
    DECLARE resultKM DECIMAL(10, 6);
    DECLARE deltaLon DECIMAL(10, 6);
    DECLARE a DECIMAL(10, 6);
    DECLARE c DECIMAL(10, 6);

    SET radius = 6371; -- Earth's radius in kilometers

    SET deltaLat = RADIANS(lat2 - lat1);
    SET deltaLon = RADIANS(lon2 - lon1);

    SET a = SIN(deltaLat / 2) * SIN(deltaLat / 2) + COS(RADIANS(lat1)) * COS(RADIANS(lat2)) * SIN(deltaLon / 2) * SIN(deltaLon / 2);
    SET c = 2 * ATAN2(SQRT(a), SQRT(1 - a));

    SET resultKM =  radius * c;

    IF resultKM >= 60 THEN
        RETURN 'LONG';
    ELSEIF resultKM >= 30  THEN
        RETURN 'MEDIUM';
    ELSE
        RETURN 'SMALL';
    END IF;
END$$
DELIMITER ;

CREATE OR REPLACE VIEW routes_vw AS
SELECT r.description, r.name, r.id, c1.altitude as lat1, c1.length as len1, c2.altitude as lat2, c2.length as len2,  CalculateDistance(c1.altitude, c1.length,c2.altitude, c2.length) as type
FROM routes r
INNER JOIN coordinates c1 ON c1.id = r.pointArrival
INNER JOIN coordinates c2 ON c2.id = r.startingPoint;


###########################
# UPDATE TRANSPORTER
###########################

create
    definer = admin@localhost procedure update_transporter(IN in_id int, IN in_name varchar(200), IN in_company varchar(100))
BEGIN
  DECLARE transporter_exists INT;

  START TRANSACTION;

  -- Verifica si el Transporter existe
  SELECT COUNT(*) INTO transporter_exists FROM transporters WHERE id = in_id;

  IF transporter_exists = 0 THEN
    ROLLBACK;
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Transporter not found by ID';
  ELSE
    -- Actualiza el Transporter
    UPDATE transporters
    SET name = in_name, company = in_company
    WHERE id = in_id;

    -- Devuelve el Transporter modificado
    SELECT id, name, company
    FROM transporters
    WHERE id = in_id;

    COMMIT;
  END IF;
END;
