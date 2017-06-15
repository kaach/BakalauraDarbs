SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `Points` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Points` ;

-- -----------------------------------------------------
-- Table `Points`.`Marsruts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Points`.`Marsruts` (
  `marsruts_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nosaukums` VARCHAR(40) CHARACTER SET 'utf8' COLLATE 'utf8_latvian_ci' NOT NULL,
  `sakuma_laiks` TIME NOT NULL,
  `beigu_laiks` TIME NOT NULL,
  PRIMARY KEY (`marsruts_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_latvian_ci;


-- -----------------------------------------------------
-- Table `Points`.`Punkts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Points`.`Punkts` (
  `punkts_id` INT(11) NOT NULL AUTO_INCREMENT,
  `reiss_id` INT(11) NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `laiks` TIME NOT NULL,
  PRIMARY KEY (`punkts_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_latvian_ci;


-- -----------------------------------------------------
-- Table `Points`.`Reiss`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Points`.`Reiss` (
  `reiss_id` INT(11) NOT NULL AUTO_INCREMENT,
  `marsruts_id` INT(11) NOT NULL,
  `soferis_id` INT(11) NOT NULL,
  `datums` DATE NOT NULL,
  PRIMARY KEY (`reiss_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_latvian_ci;


-- -----------------------------------------------------
-- Table `Points`.`Soferis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Points`.`Soferis` (
  `soferis_id` INT(5) NOT NULL AUTO_INCREMENT,
  `parole` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_latvian_ci' NOT NULL DEFAULT '',
  PRIMARY KEY (`soferis_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_latvian_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
