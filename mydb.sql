-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `codigoCliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `endereco` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`codigoCliente`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigoCliente` ASC),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pedidos` (
  `codigoPedidos` INT(11) NOT NULL AUTO_INCREMENT,
  `valor` DECIMAL(10,2) UNSIGNED NOT NULL,
  `data` DATETIME NOT NULL,
  `codigoCliente` INT(11) NOT NULL,
  `descricao` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`codigoPedidos`),
  UNIQUE INDEX `idpedidos_UNIQUE` (`codigoPedidos` ASC),
  INDEX `codigoCliente_idx` (`codigoCliente` ASC),
  CONSTRAINT `codigoCliente`
    FOREIGN KEY (`codigoCliente`)
    REFERENCES `mydb`.`cliente` (`codigoCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
