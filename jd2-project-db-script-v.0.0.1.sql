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
-- -----------------------------------------------------
-- Schema boraji
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `boraji` ;

-- -----------------------------------------------------
-- Schema boraji
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boraji` DEFAULT CHARACTER SET latin1 ;
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
-- Table `test-system-db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`users` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(60) NOT NULL,
  `lastName` VARCHAR(60) NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `salt` VARCHAR(60) NOT NULL,
  `isEnabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`iduser`))
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
  `questions_idquestion` INT NOT NULL,
  PRIMARY KEY (`idanswer`, `questions_idquestion`),
  INDEX `fk_answers_questions1_idx` (`questions_idquestion` ASC),
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`questions_idquestion`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_has_tags_tags1`
    FOREIGN KEY (`tags_idtag`)
    REFERENCES `test-system-db`.`tags` (`idtag`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test-system-db`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`authorities` (
  `idauthority` INT NOT NULL AUTO_INCREMENT,
  `authority` VARCHAR(45) NOT NULL,
  `users_iduser` INT NOT NULL,
  PRIMARY KEY (`idauthority`),
  INDEX `fk_authorities_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_authorities_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
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
  `duration` INT NOT NULL,
  `users_iduser` INT NOT NULL,
  `isPublic` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idtest`),
  INDEX `fk_tests_users1_idx` (`users_iduser` ASC),
  CONSTRAINT `fk_tests_users1`
    FOREIGN KEY (`users_iduser`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tests_has_questions_questions1`
    FOREIGN KEY (`questions_idquestions`)
    REFERENCES `test-system-db`.`questions` (`idquestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
  `tests_idtest` INT NOT NULL,
  `users_iduser_assignee` INT NOT NULL,
  `users_iduser_assigner` INT NOT NULL,
  `isSubmitted` TINYINT(1) NOT NULL,
  `deadline` DATETIME NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `details` VARCHAR(200) NULL,
  PRIMARY KEY (`idassigned_test`),
  INDEX `fk_users_has_tests1_tests1_idx` (`tests_idtest` ASC),
  INDEX `fk_users_has_tests1_users1_idx` (`users_iduser_assignee` ASC),
  INDEX `fk_users_has_assigned_tests_users1_idx` (`users_iduser_assigner` ASC),
  CONSTRAINT `fk_users_has_tests1_users1`
    FOREIGN KEY (`users_iduser_assignee`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_tests1_tests1`
    FOREIGN KEY (`tests_idtest`)
    REFERENCES `test-system-db`.`tests` (`idtest`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_has_assigned_tests_users1`
    FOREIGN KEY (`users_iduser_assigner`)
    REFERENCES `test-system-db`.`users` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Table `test-system-db`.`submittedQuestions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`submittedQuestions` (
  `idsubmittedQuestion` INT NOT NULL AUTO_INCREMENT,
  `idassigned_test` INT NOT NULL,
  `idquestion` INT NOT NULL,
  `feedback` VARCHAR(200) NULL,
  PRIMARY KEY (`idsubmittedQuestion`),
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
-- Table `test-system-db`.`submittedQuestions_has_answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-system-db`.`submittedQuestions_has_answers` (
  `idsubmittedAnswer` INT NOT NULL AUTO_INCREMENT,
  `answers_idanswer` INT NOT NULL,
  `submittedQuestions_idsubmittedQuestion` INT NOT NULL,
  `givenAnswer` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idsubmittedAnswer`),
  INDEX `fk_answeredQuestions_has_answers_answers1_idx` (`answers_idanswer` ASC),
  INDEX `fk_answeredQuestions_has_answers_submittedQuestions1_idx` (`submittedQuestions_idsubmittedQuestion` ASC),
  CONSTRAINT `fk_answeredQuestions_has_answers_answers1`
    FOREIGN KEY (`answers_idanswer`)
    REFERENCES `test-system-db`.`answers` (`idanswer`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answeredQuestions_has_answers_submittedQuestions1`
    FOREIGN KEY (`submittedQuestions_idsubmittedQuestion`)
    REFERENCES `test-system-db`.`submittedQuestions` (`idsubmittedQuestion`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `boraji` ;

-- -----------------------------------------------------
-- Table `boraji`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boraji`.`USERS` (
  `USERNAME` VARCHAR(50) NOT NULL,
  `PASSWORD` VARCHAR(100) NOT NULL,
  `ENABLED` TINYINT(1) NOT NULL,
  PRIMARY KEY (`USERNAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boraji`.`AUTHORITIES`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boraji`.`AUTHORITIES` (
  `USERNAME` VARCHAR(50) NOT NULL,
  `AUTHORITY` VARCHAR(50) NOT NULL,
  UNIQUE INDEX `ix_auth_username` (`USERNAME` ASC, `AUTHORITY` ASC),
  CONSTRAINT `fk_authorities_users`
    FOREIGN KEY (`USERNAME`)
    REFERENCES `boraji`.`USERS` (`USERNAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `test-system-db`.`categories`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`categories` (`idcategory`, `name`) VALUES (1, 'geography');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`subcategories`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`subcategories` (`idsubcategory`, `name`, `categories_idcategory`) VALUES (1, 'elementary', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`users` (`iduser`, `firstName`, `lastName`, `nickName`, `email`, `password`, `salt`, `isEnabled`) VALUES (1, 'John', 'Wayne', 'Cowboy', 'j.wayne@mail.com', '$2a$10$qxQLGPs/uokzZavKx8.NKuAcYhLv1vDyBW4qbYAtSA5zeMsoG8tTa', 'qwerty', 1);
INSERT INTO `test-system-db`.`users` (`iduser`, `firstName`, `lastName`, `nickName`, `email`, `password`, `salt`, `isEnabled`) VALUES (2, 'Yen', 'Johnson', 'Cowboy', 'y.johnson@mail.com', '$2a$10$nyXo/ByTq.43EYZqeAW.Luj4dtxZlBgCCU6qGIZrlyvDKYAck1Nbq', 'qwerty', 1);
INSERT INTO `test-system-db`.`users` (`iduser`, `firstName`, `lastName`, `nickName`, `email`, `password`, `salt`, `isEnabled`) VALUES (3, 'Joe', 'Deere', 'Cowboy', 'j.deere@mail.com', '$2a$10$nyXo/ByTq.43EYZqeAW.Luj4dtxZlBgCCU6qGIZrlyvDKYAck1Nbq', 'qwerty', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`questions`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`questions` (`idquestion`, `body`, `isOfMultipleChoice`, `subcategories_idsubcategory`, `users_iduser`) VALUES (1, 'What is the capital of Germany?', 0, 1, 1);
INSERT INTO `test-system-db`.`questions` (`idquestion`, `body`, `isOfMultipleChoice`, `subcategories_idsubcategory`, `users_iduser`) VALUES (2, 'What is the capital of Japan?', 0, 1, 2);
INSERT INTO `test-system-db`.`questions` (`idquestion`, `body`, `isOfMultipleChoice`, `subcategories_idsubcategory`, `users_iduser`) VALUES (3, 'What is the capital of Italy?', 0, 1, 3);
INSERT INTO `test-system-db`.`questions` (`idquestion`, `body`, `isOfMultipleChoice`, `subcategories_idsubcategory`, `users_iduser`) VALUES (4, 'Which of these are real countries?', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`answers`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (1, 1, 'Rogachev', 1);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (2, 1, 'Minsk', 1);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (3, 1, 'Bobruisk', 1);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (4, 0, 'Berlin', 1);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (5, 0, 'Tokio', 2);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (6, 1, 'Berlin', 2);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (7, 1, 'Kioto', 2);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (8, 1, 'Phnom-Penh', 2);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (9, 0, 'Roma', 3);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (10, 1, 'Borisov', 3);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (11, 1, 'Cherepovets', 3);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (12, 1, 'Samarkand', 3);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (13, 0, 'Belarus', 4);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (14, 0, 'Russia', 4);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (15, 0, 'Poland', 4);
INSERT INTO `test-system-db`.`answers` (`idanswer`, `isFalse`, `body`, `questions_idquestion`) VALUES (16, 1, 'Wakanda', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`tags`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`tags` (`idtag`, `name`) VALUES (1, 'basic geography');
INSERT INTO `test-system-db`.`tags` (`idtag`, `name`) VALUES (2, 'rivers');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`questions_has_tags`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`questions_has_tags` (`questions_idquestions`, `tags_idtag`) VALUES (1, 1);
INSERT INTO `test-system-db`.`questions_has_tags` (`questions_idquestions`, `tags_idtag`) VALUES (2, 1);
INSERT INTO `test-system-db`.`questions_has_tags` (`questions_idquestions`, `tags_idtag`) VALUES (3, 1);
INSERT INTO `test-system-db`.`questions_has_tags` (`questions_idquestions`, `tags_idtag`) VALUES (4, 1);
INSERT INTO `test-system-db`.`questions_has_tags` (`questions_idquestions`, `tags_idtag`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`authorities`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`, `users_iduser`) VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`, `users_iduser`) VALUES (2, 'ROLE_USER', 2);
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`, `users_iduser`) VALUES (3, 'ROLE_USER', 3);
INSERT INTO `test-system-db`.`authorities` (`idauthority`, `authority`, `users_iduser`) VALUES (4, 'ROLE_ADMIN', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`tests`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`tests` (`idtest`, `name`, `descritption`, `duration`, `users_iduser`, `isPublic`) VALUES (1, 'basic geography test', 'for app testing', 600, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`tests_has_questions`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`tests_has_questions` (`tests_idtest`, `questions_idquestions`) VALUES (1, 1);
INSERT INTO `test-system-db`.`tests_has_questions` (`tests_idtest`, `questions_idquestions`) VALUES (1, 2);
INSERT INTO `test-system-db`.`tests_has_questions` (`tests_idtest`, `questions_idquestions`) VALUES (1, 3);
INSERT INTO `test-system-db`.`tests_has_questions` (`tests_idtest`, `questions_idquestions`) VALUES (1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`users_has_tests`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`users_has_tests` (`users_iduser`, `tests_idtest`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`users_has_assigned_tests`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`users_has_assigned_tests` (`idassigned_test`, `tests_idtest`, `users_iduser_assignee`, `users_iduser_assigner`, `isSubmitted`, `deadline`, `name`, `details`) VALUES (1, 1, 2, 1, 0, '2019-04-26 09:34:00.000', 'someAssignmentName', 'someAssignmentDetails');
INSERT INTO `test-system-db`.`users_has_assigned_tests` (`idassigned_test`, `tests_idtest`, `users_iduser_assignee`, `users_iduser_assigner`, `isSubmitted`, `deadline`, `name`, `details`) VALUES (2, 1, 3, 1, 0, '2019-04-26 09:34:00.000', 'someAssignmentName', 'someAssignmentDetails');

COMMIT;


-- -----------------------------------------------------
-- Data for table `test-system-db`.`users_has_subscribers`
-- -----------------------------------------------------
START TRANSACTION;
USE `test-system-db`;
INSERT INTO `test-system-db`.`users_has_subscribers` (`users_iduser`, `users_idsubscriber`, `groupName`) VALUES (1, 2, 'students');
INSERT INTO `test-system-db`.`users_has_subscribers` (`users_iduser`, `users_idsubscriber`, `groupName`) VALUES (1, 3, 'students');

COMMIT;

