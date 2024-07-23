-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `aquaminas` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aquaminas` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `aquaminas` ;

-- -----------------------------------------------------
-- Table `Funcao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Funcao` (
  `idFuncao` INT NOT NULL AUTO_INCREMENT,
  `funcao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idFuncao`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Funcionario` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Funcionario` (
  `idFuncionario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  `Funcao_idFuncao` INT NOT NULL,
  PRIMARY KEY (`idFuncionario`),
  INDEX `fk_Funcionario_Funcao1_idx` (`Funcao_idFuncao` ASC) ,
  CONSTRAINT `fk_Funcionario_Funcao1`
    FOREIGN KEY (`Funcao_idFuncao`)
    REFERENCES `Funcao` (`idFuncao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Fornecedor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Fornecedor` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Fornecedor` (
  `idFornecedor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idFornecedor`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Peixe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Peixe` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Peixe` (
  `idPeixe` INT NOT NULL AUTO_INCREMENT,
  `valor_Unid` DECIMAL(20,2) NOT NULL,
  `especie` VARCHAR(100) NOT NULL,
  `nome_cientifico` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idPeixe`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cliente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`idcliente`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Venda` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `Funcionario_idFuncionario` INT NOT NULL,
  `venda_Total` DECIMAL(20,2) NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  PRIMARY KEY (`idVenda`),
  INDEX `fk_Venda_Funcionario_idx` (`Funcionario_idFuncionario` ASC) ,
  INDEX `fk_Venda_cliente1_idx` (`cliente_idcliente` ASC) ,
  CONSTRAINT `fk_Venda_Funcionario`
    FOREIGN KEY (`Funcionario_idFuncionario`)
    REFERENCES `Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `racao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `racao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `racao` (
  `idracao` INT NOT NULL AUTO_INCREMENT,
  `peso` DECIMAL(20,2) NOT NULL,
  `valor` DECIMAL(20,2) NOT NULL,
  `Fornecedor_idFornecedor` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idracao`),
  INDEX `fk_racao_Fornecedor1_idx` (`Fornecedor_idFornecedor` ASC) ,
  CONSTRAINT `fk_racao_Fornecedor1`
    FOREIGN KEY (`Fornecedor_idFornecedor`)
    REFERENCES `Fornecedor` (`idFornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Venda_Peixe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Venda_Peixe` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Venda_Peixe` (
  `Venda_idVenda` INT NOT NULL AUTO_INCREMENT,
  `Peixe_idPeixe` INT NOT NULL,
  `quantidade_Peixe` INT NOT NULL,
  PRIMARY KEY (`Venda_idVenda`, `Peixe_idPeixe`),
  INDEX `fk_Venda_has_Peixe_Peixe1_idx` (`Peixe_idPeixe` ASC) ,
  INDEX `fk_Venda_has_Peixe_Venda1_idx` (`Venda_idVenda` ASC) ,
  CONSTRAINT `fk_Venda_has_Peixe_Venda1`
    FOREIGN KEY (`Venda_idVenda`)
    REFERENCES `Venda` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_Peixe_Peixe1`
    FOREIGN KEY (`Peixe_idPeixe`)
    REFERENCES `Peixe` (`idPeixe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Venda_racao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Venda_racao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Venda_racao` (
  `Venda_idVenda` INT NOT NULL AUTO_INCREMENT,
  `racao_idracao` INT NOT NULL,
  `quantidade` INT NOT NULL,
  PRIMARY KEY (`Venda_idVenda`),
  INDEX `fk_Venda_has_racao_racao1_idx` (`racao_idracao` ASC) ,
  INDEX `fk_Venda_has_racao_Venda1_idx` (`Venda_idVenda` ASC) ,
  CONSTRAINT `fk_Venda_has_racao_Venda1`
    FOREIGN KEY (`Venda_idVenda`)
    REFERENCES `Venda` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Venda_has_racao_racao1`
    FOREIGN KEY (`racao_idracao`)
    REFERENCES `racao` (`idracao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endereco` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `endereco` (
  `idendereco` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `cliente_idcliente` INT NOT NULL,
  PRIMARY KEY (`idendereco`),
  INDEX `fk_endereco_cliente1_idx` (`cliente_idcliente` ASC) ,
  CONSTRAINT `fk_endereco_cliente1`
    FOREIGN KEY (`cliente_idcliente`)
    REFERENCES `cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

show tables;