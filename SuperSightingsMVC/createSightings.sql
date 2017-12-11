-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SuperSightings
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SuperSightings` ;

-- -----------------------------------------------------
-- Schema SuperSightings
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperSightings` DEFAULT CHARACTER SET utf8 ;
USE `SuperSightings` ;

-- -----------------------------------------------------
-- Table `SuperSightings`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Location` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Location` (
  `idLocation` INT NOT NULL auto_increment,
  `Address` VARCHAR(100) NULL,
  `Latitude` DECIMAL NULL,
  `Longitude` DECIMAL NULL,
  `Name` VARCHAR(45) NULL,
  PRIMARY KEY (`idLocation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`Sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Sighting` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Sighting` (
  `idSighting` INT NOT NULL auto_increment,
  `SightingDate` DATE NULL,
  `LocationID` INT NULL,
  PRIMARY KEY (`idSighting`),
  INDEX `fk_SightingLocation_idx` (`LocationID` ASC),
  CONSTRAINT `fk_SightingLocation`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperSightings`.`Location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`SuperPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`SuperPerson` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`SuperPerson` (
  `idSuperPerson` INT NOT NULL auto_increment,
  `Name` VARCHAR(45) NOT NULL,
  `Identity` VARCHAR(45) NULL,
  `SuperPower` VARCHAR(45) NULL,
  `Description` VARCHAR(500) NULL,
  PRIMARY KEY (`idSuperPerson`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Organization` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Organization` (
  `idOrganization` INT NOT NULL auto_increment,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(255) NULL,
  `LocationID` INT NULL,
  PRIMARY KEY (`idOrganization`),
  INDEX `fk_Org_Location_idx` (`LocationID` ASC),
  CONSTRAINT `fk_Org_Location`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperSightings`.`Location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`LocationOrganization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`LocationOrganization` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`LocationOrganization` (
  `LocationID` INT NULL,
  `OrganizaitonID` INT NULL,
  INDEX `fk_Location_Bridge_idx` (`LocationID` ASC),
  INDEX `fk_Organization_Bridge_idx` (`OrganizaitonID` ASC),
  CONSTRAINT `fk_Location_Org_Bridge`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperSightings`.`Location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Organization_Location_Bridge`
    FOREIGN KEY (`OrganizaitonID`)
    REFERENCES `SuperSightings`.`Organization` (`idOrganization`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`Super_Org`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Super_Org` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Super_Org` (
  `SuperID` INT NULL,
  `Org_ID` INT NULL,
  INDEX `fk_Org_Bridge_idx` (`SuperID` ASC),
  INDEX `fk_Org_Bridge_idx1` (`Org_ID` ASC),
  CONSTRAINT `fk_Person_Bridge`
    FOREIGN KEY (`SuperID`)
    REFERENCES `SuperSightings`.`SuperPerson` (`idSuperPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Org_Bridge`
    FOREIGN KEY (`Org_ID`)
    REFERENCES `SuperSightings`.`Organization` (`idOrganization`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`Sighting_Super`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Sighting_Super` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Sighting_Super` (
  `SuperID` INT NULL,
  `SightingID` INT NULL,
  INDEX `fk_Sighting_Bridge_idx` (`SightingID` ASC),
  INDEX `fk_Super_Bridge_idx` (`SuperID` ASC),
  CONSTRAINT `fk_Sighting_Super_Bridge`
    FOREIGN KEY (`SightingID`)
    REFERENCES `SuperSightings`.`Sighting` (`idSighting`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Super_Sighting_Bridge`
    FOREIGN KEY (`SuperID`)
    REFERENCES `SuperSightings`.`SuperPerson` (`idSuperPerson`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SuperSightings`.`Sighting_Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `SuperSightings`.`Sighting_Location` ;

CREATE TABLE IF NOT EXISTS `SuperSightings`.`Sighting_Location` (
  `SightingID` INT NULL,
  `LocationID` INT NULL,
  INDEX `fk_Location_Bridge_idx` (`LocationID` ASC),
  CONSTRAINT `fk_Sighting_Location_Bridge`
    FOREIGN KEY (`SightingID`)
    REFERENCES `SuperSightings`.`Sighting` (`idSighting`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Location_Sighting_Bridge`
    FOREIGN KEY (`LocationID`)
    REFERENCES `SuperSightings`.`Location` (`idLocation`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
