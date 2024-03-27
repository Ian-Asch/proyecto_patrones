CREATE DATABASE laca;

USE laca;

CREATE TABLE `Users`(
    `UserID` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(255) NOT NULL,
    `IdentificationNumber` VARCHAR(100),
    `CompanyName` VARCHAR(255),
    `UserType` ENUM('Administrator', 'Carrier', 'Client', 'Approver', 'Viewer') NOT NULL,
    `Password` VARCHAR(255) NOT NULL
);
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
