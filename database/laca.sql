CREATE DATABASE laca;

USE laca;

CREATE TABLE `Users`(
    `UserID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `IdentificationNumber` VARCHAR(100),
    `CompanyName` VARCHAR(255),
    `UserType` ENUM('Administrator', 'Carrier', 'Client', 'Approver', 'Viewer') NOT NULL,
    `Password` VARCHAR(255) NOT NULL
);DROP DATABASE laca;

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
CREATE TABLE `RouteTransportUnit`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `RouteID` INT UNSIGNED NOT NULL,
    `UnitID` INT UNSIGNED NOT NULL
);
CREATE TABLE `Packages`(
    `PackageID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` TEXT,
    `Weight` DECIMAL(10, 2) NOT NULL,
    `Price` DECIMAL(10, 2) NOT NULL,
    `SizeHeight` DECIMAL(10, 2) NOT NULL,
    `SizeWidth` DECIMAL(10, 2) NOT NULL,
    `ClientID` INT UNSIGNED,
    `RouteID` INT UNSIGNED,
    `Status` ENUM('Sent', 'In Transit', 'Delivered') NOT NULL
);
CREATE TABLE `TransportUnits`(
    `UnitID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Plate` VARCHAR(50) NOT NULL,
    `SizeHeight` DECIMAL(5, 2) NOT NULL,
    `SizeWidth` DECIMAL(5, 2) NOT NULL,
    `Type` ENUM('On Foot', 'Motorcycle', 'Truck', 'Pickup') NOT NULL,
    `MaxWeight` DECIMAL(10, 2) NOT NULL,
    `CarrierID` INT UNSIGNED
);
CREATE TABLE `Points`(
    `PointID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` TEXT,
    `Coordinates` POINT NOT NULL
);
CREATE TABLE `Routes`(
    `RouteID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `Description` TEXT,
    `StartPointID` INT UNSIGNED,
    `EndPointID` INT UNSIGNED,
    `ShippingCost` DECIMAL(10, 2) NOT NULL,
    `Approved` TINYINT(1) NOT NULL DEFAULT FALSE,
    `DurationType` ENUM('Short', 'Medium', 'Long') NOT NULL
);
ALTER TABLE
    `TransportUnits` ADD CONSTRAINT `transportunits_carrierid_foreign` FOREIGN KEY(`CarrierID`) REFERENCES `Users`(`UserID`);
ALTER TABLE
    `Routes` ADD CONSTRAINT `routes_startpointid_foreign` FOREIGN KEY(`StartPointID`) REFERENCES `Points`(`PointID`);
ALTER TABLE
    `Routes` ADD CONSTRAINT `routes_endpointid_foreign` FOREIGN KEY(`EndPointID`) REFERENCES `Points`(`PointID`);
ALTER TABLE
    `Packages` ADD CONSTRAINT `packages_clientid_foreign` FOREIGN KEY(`ClientID`) REFERENCES `Users`(`UserID`);
ALTER TABLE
    `Packages` ADD CONSTRAINT `packages_routeid_foreign` FOREIGN KEY(`RouteID`) REFERENCES `Routes`(`RouteID`);
ALTER TABLE
    `RouteTransportUnit` ADD CONSTRAINT `routetransportunit_unitid_foreign` FOREIGN KEY(`UnitID`) REFERENCES `TransportUnits`(`UnitID`);
ALTER TABLE
    `RouteTransportUnit` ADD CONSTRAINT `routetransportunit_routeid_foreign` FOREIGN KEY(`RouteID`) REFERENCES `Routes`(`RouteID`);

INSERT INTO Users (Name, IdentificationNumber, CompanyName, UserType, Password) VALUES
('Carlos Ruiz', '10203040', 'TransLogistica CR', 'Carrier', SHA2('password01', 256)),
('Maria Lopez', '50403020', 'ML Envíos', 'Client', SHA2('password02', 256)),
('Ana Pereira', '80607050', 'AP Transportes', 'Carrier', SHA2('password03', 256)),
('Luis Méndez', '30705090', NULL, 'Approver', SHA2('password04', 256)),
('Sofía Castro', '90807060', 'SC Paquetería', 'Client', SHA2('password05', 256)),
('Admin Principal', NULL, NULL, 'Administrator', SHA2('adminpassword', 256)),
('Visualizador General', NULL, NULL, 'Viewer', SHA2('viewpassword', 256));

INSERT INTO TransportUnits (Name, Plate, SizeHeight, SizeWidth, Type, MaxWeight, CarrierID) VALUES
('Camión 01', 'TX123AB', 3.0, 6.0, 'Truck', 15000, 1),
('Furgoneta 01', 'FV456CD', 2.0, 3.0, 'Pickup', 5000, 3),
('Motocicleta 01', 'MC789EF', 1.0, 1.5, 'Motorcycle', 150, 1),
('Camión 02', 'TX987ZY', 3.5, 7.0, 'Truck', 20000, 3);

INSERT INTO Points (Name, Description, Coordinates) VALUES
('Inicio Ciudad A', 'Ciudad A - Punto de inicio', ST_GeomFromText('POINT(10 20)')),
('Fin Ciudad B', 'Ciudad B - Punto de llegada', ST_GeomFromText('POINT(35 45)')),
('Inicio Ciudad C', 'Ciudad C - Punto de inicio', ST_GeomFromText('POINT(55 65)')),
('Fin Ciudad D', 'Ciudad D - Punto de llegada', ST_GeomFromText('POINT(75 85)'));

INSERT INTO Routes (Name, Description, StartPointID, EndPointID, ShippingCost, Approved, DurationType) VALUES
('Ruta A-B', 'De Ciudad A a Ciudad B', 1, 2, 250.00, 1, 'Long'),
('Ruta C-D', 'De Ciudad C a Ciudad D', 3, 4, 300.00, 1, 'Long');

INSERT INTO RouteTransportUnit (RouteID, UnitID) VALUES
(1, 1), -- Camión 01 en Ruta A-B
(1, 2), -- Furgoneta 01 en Ruta A-B
(2, 3), -- Motocicleta 01 en Ruta C-D
(2, 4); -- Camión 02 en Ruta C-D

INSERT INTO Packages (Name, Description, Weight, Price, SizeHeight, SizeWidth, ClientID, RouteID, Status) VALUES
('Paquete Electrónica', 'Dispositivos electrónicos', 10.0, 1000.00, 0.4, 0.3, 2, 1, 'Sent'),
('Paquete Libros', 'Libros y revistas', 5.0, 300.00, 0.5, 0.4, 5, 2, 'In Transit'),
('Paquete Ropa', 'Ropa variada', 8.0, 700.00, 0.6, 0.5, 2, 1, 'Delivered'),
('Paquete Documentos', 'Documentos importantes', 1.0, 50.00, 0.1, 0.2, 5, 2, 'Sent');
