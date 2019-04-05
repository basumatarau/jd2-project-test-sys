-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema test-system-db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test-system-db` ;

-- -----------------------------------------------------
-- Schema test-system-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test-system-db` DEFAULT CHARACTER SET utf8 ;
USE `test-system-db` ;

-- -----------------------------------------------------
-- Table `test-system-db`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`categories` (
  `idcategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idcategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`subcategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`subcategories` (
  `idsubcategory` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `categories_idcategory` INT NOT NULL,
  PRIMARY KEY (`idsubcategory`),
  INDEX `fk_subcategories_categories1_idx` (`categories_idcategory` ASC),
  CONSTRAINT `fk_subcategories_categories1`
    FOREIGN KEY (`categories_idcategory`)
    REFERENCES `test-system-db`.`categories` (`idcategory`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`authorities` (
  `idauthority` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idauthority`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`users` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(60) NOT NULL,
  `lastName` VARCHAR(60) NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(80) NOT NULL,
  `salt` VARCHAR(60) NOT NULL,
  `isEnabled` TINYINT(1) NOT NULL,
  `authorities_idauthority` INT NOT NULL,
  PRIMARY KEY (`iduser`),
  INDEX `fk_users_authorities1_idx` (`authorities_idauthority` ASC),
  CONSTRAINT `fk_users_authorities1`
    FOREIGN KEY (`authorities_idauthority`)
    REFERENCES `test-system-db`.`authorities` (`idauthority`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`questions` (
  `idquestion` INT NOT NULL AUTO_INCREMENT,
  `body` VARCHAR(1000) NOT NULL,
  `isOfMultipleChoice` TINYINT(1) NOT NULL,
  `subcategories_idsubcategory` INT NOT NULL,
  `users_iduser` INT NOT NULL,
  PRIMARY KEY (`idquestion`),
  INDEX `fk_questions_subcategories1_idx` (`subcategories_idsubcategory` ASC),
  INDEX `fk_questions_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_questions_subcategories1`
    FOREIGN KEY (`subcategories_idsubcategory`)
    REFERENCES `test-system-db`.`subcategories` (`idsubcategory`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`answers` (
  `idanswer` INT NOT NULL AUTO_INCREMENT,
  `isFalse` TINYINT(1) NOT NULL,
  `body` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idanswer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`questions_has_answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`questions_has_answers` (
  `questions_idquestions` INT NOT NULL,
  `answers_idanswer` INT NOT NULL,
  PRIMARY KEY (`questions_idquestions`, `answers_idanswer`),
  INDEX `fk_questions_has_answers_answers1_idx` (`answers_idanswer` ASC),
  INDEX `fk_questions_has_answers_questions_idx` (`questions_idquestions` ASC),
  CONSTRAINT `fk_questions_has_answers_questions`
    FOREIGN KEY (`questions_idquestions`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_has_answers_answers1`
    FOREIGN KEY (`answers_idanswer`)
    REFERENCES `test-system-db`.`answers` (`idanswer`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`tags` (
  `idtag` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idtag`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`questions_has_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`questions_has_tags` (
  `questions_idquestions` INT NOT NULL,
  `tags_idtag` INT NOT NULL,
  PRIMARY KEY (`questions_idquestions`, `tags_idtag`),
  INDEX `fk_questions_has_tags_tags1_idx` (`tags_idtag` ASC),
  INDEX `fk_questions_has_tags_questions1_idx` (`questions_idquestions` ASC),
  CONSTRAINT `fk_questions_has_tags_questions1`
    FOREIGN KEY (`questions_idquestions`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_questions_has_tags_tags1`
    FOREIGN KEY (`tags_idtag`)
    REFERENCES `test-system-db`.`tags` (`idtag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`tests` (
  `idtest` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `descritption` VARCHAR(400) NOT NULL,
  `deadline` TIMESTAMP NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`idtest`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`tests_has_questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`tests_has_questions` (
  `tests_idtest` INT NOT NULL,
  `questions_idquestions` INT NOT NULL,
  PRIMARY KEY (`tests_idtest`, `questions_idquestions`),
  INDEX `fk_tests_has_questions_questions1_idx` (`questions_idquestions` ASC),
  INDEX `fk_tests_has_questions_tests1_idx` (`tests_idtest` ASC),
  CONSTRAINT `fk_tests_has_questions_tests1`
    FOREIGN KEY (`tests_idtest`)
    REFERENCES `test-system-db`.`tests` (`idtest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tests_has_questions_questions1`
    FOREIGN KEY (`questions_idquestions`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`users_has_tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`users_has_tests` (
  `users_iduser` INT NOT NULL,
  `tests_idtest` INT NOT NULL,
  PRIMARY KEY (`users_iduser`, `tests_idtest`),
  INDEX `fk_users_has_tests_tests1_idx` (`tests_idtest` ASC),
  INDEX `fk_users_has_tests_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_users_has_tests_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_tests_tests1`
    FOREIGN KEY (`tests_idtest`)
    REFERENCES `test-system-db`.`tests` (`idtest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`users_has_assigned_tests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`users_has_assigned_tests` (
  `idassigned_test` INT NOT NULL AUTO_INCREMENT,
  `users_iduser` INT NOT NULL,
  `tests_idtest` INT NOT NULL,
  PRIMARY KEY (`idassigned_test`),
  INDEX `fk_users_has_tests1_tests1_idx` (`tests_idtest` ASC),
  INDEX `fk_users_has_tests1_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_users_has_tests1_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_tests1_tests1`
    FOREIGN KEY (`tests_idtest`)
    REFERENCES `test-system-db`.`tests` (`idtest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`users_has_subscribers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`users_has_subscribers` (
  `users_iduser` INT NOT NULL,
  `users_idsubscriber` INT NOT NULL,
  `groupName` VARCHAR(80) NULL,
  PRIMARY KEY (`users_iduser`, `users_idsubscriber`),
  INDEX `fk_users_has_users_users2_idx` (`users_idsubscriber` ASC),
  INDEX `fk_users_has_users_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_users_has_users_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_users_users2`
    FOREIGN KEY (`users_idsubscriber`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`answeredQuestions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`answeredQuestions` (
  `idassigned_test` INT NOT NULL,
  `idquestion` INT NOT NULL,
  PRIMARY KEY (`idassigned_test`, `idquestion`),
  INDEX `fk_users_has_assigned_tests_has_questions_questions1_idx` (`idquestion` ASC),
  INDEX `fk_users_has_assigned_tests_has_questions_users_has_assigne_idx` (`idassigned_test` ASC),
  CONSTRAINT `fk_users_has_assigned_tests_has_questions_users_has_assigned_1`
    FOREIGN KEY (`idassigned_test`)
    REFERENCES `test-system-db`.`users_has_assigned_tests` (`idassigned_test`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_assigned_tests_has_questions_questions1`
    FOREIGN KEY (`idquestion`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`answeredQuestions_has_answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`answeredQuestions_has_answers` (
  `answeredQuestions_idassigned_test` INT NOT NULL,
  `answeredQuestions_idquestion` INT NOT NULL,
  `answers_idanswer` INT NOT NULL,
  `givenAnswer` TINYINT(1) NOT NULL,
  PRIMARY KEY (`answeredQuestions_idassigned_test`, `answeredQuestions_idquestion`, `answers_idanswer`),
  INDEX `fk_answeredQuestions_has_answers_answers1_idx` (`answers_idanswer` ASC),
  INDEX `fk_answeredQuestions_has_answers_answeredQuestions1_idx` (`answeredQuestions_idassigned_test` ASC, `answeredQuestions_idquestion` ASC),
  CONSTRAINT `fk_answeredQuestions_has_answers_answeredQuestions1`
    FOREIGN KEY (`answeredQuestions_idassigned_test` , `answeredQuestions_idquestion`)
    REFERENCES `test-system-db`.`answeredQuestions` (`idassigned_test` , `idquestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answeredQuestions_has_answers_answers1`
    FOREIGN KEY (`answers_idanswer`)
    REFERENCES `test-system-db`.`answers` (`idanswer`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `test-system-db`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`) VALUES (2, 'ROLE_USER');
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`) VALUES (3, 'ROLE_GUEST');

COMMIT;

