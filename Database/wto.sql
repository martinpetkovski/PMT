-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema wto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wto` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `wto` ;

-- -----------------------------------------------------
-- Table `wto`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `password` MEDIUMTEXT NOT NULL,
  `points` INT NOT NULL,
  `followers` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`iduser`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `wto`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`image` (
  `idimage` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `title` VARCHAR(150) NOT NULL,
  `address` VARCHAR(14) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `points` INT NOT NULL DEFAULT 0,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idimage`),
  INDEX `fk_image_1_idx` (`iduser` ASC),
  UNIQUE INDEX `address_UNIQUE` (`address` ASC),
  CONSTRAINT `fk_image_1`
    FOREIGN KEY (`iduser`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `wto`.`image_vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`image_vote` (
  `idvote` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idimage` INT NOT NULL COMMENT 'this attribute is the id either of the image or the comment',
  `votetype` TINYINT(1) NOT NULL COMMENT 'true is upvote\nfalse is downvote',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idvote`),
  UNIQUE INDEX `index2` (`iduser` ASC, `idimage` ASC),
  CONSTRAINT `fk_vote_2`
    FOREIGN KEY (`idimage`)
    REFERENCES `wto`.`image` (`idimage`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_vote_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = 'Trigger explanation: \nafter create - if votetype is true increase points by one in image else decrease points by one\nafter update - if votetype is true increase points by 2 (one to neutral and one to upvote) else decrease by 2\nbefore delete - if votetype is true decrease points by 1 else increase by 1\nsame goes for comment_vote';


-- -----------------------------------------------------
-- Table `wto`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`comment` (
  `idcomment` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idimage` INT NOT NULL,
  `content` VARCHAR(140) NOT NULL,
  `points` INT NOT NULL DEFAULT 0,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcomment`),
  INDEX `fk_comment_1_idx` (`iduser` ASC),
  INDEX `fk_comment_2_idx` (`idimage` ASC),
  CONSTRAINT `fk_comment_1`
    FOREIGN KEY (`iduser`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_2`
    FOREIGN KEY (`idimage`)
    REFERENCES `wto`.`image` (`idimage`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `wto`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`tag` (
  `idtag` INT NOT NULL AUTO_INCREMENT,
  `idimage` INT NOT NULL,
  `content` VARCHAR(140) NOT NULL DEFAULT 'GENERAL',
  PRIMARY KEY (`idtag`),
  INDEX `fk_tag_1_idx` (`idimage` ASC),
  CONSTRAINT `fk_tag_1`
    FOREIGN KEY (`idimage`)
    REFERENCES `wto`.`image` (`idimage`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `wto`.`comment_vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`comment_vote` (
  `idvote` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `idcomment` INT NOT NULL COMMENT 'this attribute is the id either of the image or the comment',
  `votetype` TINYINT(1) NOT NULL COMMENT 'true is upvote\nfalse is downvote',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idvote`),
  INDEX `fk_comment_vote_comment1_idx` (`idcomment` ASC),
  UNIQUE INDEX `index3` (`iduser` ASC, `idcomment` ASC),
  CONSTRAINT `fk_comment_vote_comment1`
    FOREIGN KEY (`idcomment`)
    REFERENCES `wto`.`comment` (`idcomment`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_vote_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `wto`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`user_roles` (
  `idrole` INT(11) NOT NULL AUTO_INCREMENT,
  `iduser` INT NOT NULL,
  `ROLE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrole`),
  INDEX `uni_user_role` (`iduser` ASC, `ROLE` ASC),
  CONSTRAINT `fk_user_roles_user1`
    FOREIGN KEY (`iduser`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wto`.`follower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wto`.`follower` (
  `idfollower` INT NOT NULL AUTO_INCREMENT,
  `followerid` INT NOT NULL,
  `followeeid` INT NOT NULL,
  PRIMARY KEY (`idfollower`),
  INDEX `fk_follower_user1_idx` (`followerid` ASC),
  INDEX `fk_follower_user2_idx` (`followeeid` ASC),
  UNIQUE INDEX `index4` (`followerid` ASC, `followeeid` ASC),
  CONSTRAINT `fk_follower_user1`
    FOREIGN KEY (`followerid`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_follower_user2`
    FOREIGN KEY (`followeeid`)
    REFERENCES `wto`.`user` (`iduser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `wto`;

DELIMITER $$
USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`image_vote_AFTER_INSERT` AFTER INSERT ON `image_vote` FOR EACH ROW
BEGIN
	IF NEW.`votetype` = TRUE THEN
		UPDATE `image` SET `points` = `points` + 1 WHERE `image`.`idimage` = NEW.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` + 1 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = NEW.`idimage`);
	ELSE
		UPDATE `image` SET `points` = `points` - 1 WHERE `image`.`idimage` = NEW.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` - 1 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = NEW.`idimage`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`image_vote_AFTER_UPDATE` AFTER UPDATE ON `image_vote` FOR EACH ROW
BEGIN
	IF NEW.`votetype` = TRUE AND OLD.`votetype` = FALSE THEN 
		UPDATE `image` SET `points` = `points` + 2 WHERE `image`.`idimage` = NEW.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` + 2 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = NEW.`idimage`);
	ELSEIF NEW.`votetype` = FALSE AND OLD.`votetype` = TRUE THEN
		UPDATE `image` SET `points` = `points` - 2 WHERE `image`.`idimage` = NEW.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` - 2 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = NEW.`idimage`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`image_vote_BEFORE_DELETE` BEFORE DELETE ON `image_vote` FOR EACH ROW
BEGIN
	IF OLD.`votetype` = TRUE THEN
		UPDATE `image` SET `points` = `points` - 1 WHERE `image`.`idimage` = OLD.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` - 1 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = OLD.`idimage`);
	ELSE
		UPDATE `image` SET `points` = `points` + 1 WHERE `image`.`idimage` = OLD.`idimage`;
        UPDATE `user`, `image` SET `user`.`points` = `user`.`points` + 1 WHERE `user`.`iduser` = (SELECT `iduser` FROM `image` WHERE `idimage` = OLD.`idimage`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`comment_vote_AFTER_INSERT` AFTER INSERT ON `comment_vote` FOR EACH ROW
BEGIN
	IF NEW.`votetype` = TRUE THEN
		UPDATE `comment` SET `points` = `points` + 1 WHERE `comment`.`idcomment` = NEW.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` + 1  WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = NEW.`idcomment`);
	ELSE
		UPDATE `comment` SET `points` = `points` - 1 WHERE `comment`.`idcomment` = NEW.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` - 1 WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = NEW.`idcomment`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`comment_vote_AFTER_UPDATE` AFTER UPDATE ON `comment_vote` FOR EACH ROW
BEGIN
	IF NEW.`votetype` = TRUE AND OLD.`votetype` = FALSE THEN
		UPDATE `comment` SET `points` = `points` + 2 WHERE `comment`.`idcomment` = NEW.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` + 2  WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = NEW.`idcomment`);
	ELSEIF NEW.`votetype` = FALSE AND OLD.`votetype` = TRUE THEN
		UPDATE `comment` SET `points` = `points` - 2 WHERE `comment`.`idcomment` = NEW.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` - 2  WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = NEW.`idcomment`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`comment_vote_BEFORE_DELETE` BEFORE DELETE ON `comment_vote` FOR EACH ROW
BEGIN
	IF OLD.`votetype` = TRUE THEN
		UPDATE `comment` SET `points` = `points` - 1 WHERE `comment`.`idcomment` = OLD.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` - 1  WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = OLD.`idcomment`);
	ELSE
		UPDATE `comment` SET `points` = `points` + 1 WHERE `comment`.`idcomment` = OLD.`idcomment`;
        UPDATE `user`, `comment` SET `user`.`points` = `user`.`points` + 1  WHERE `user`.`iduser` = (SELECT `iduser` FROM `comment` WHERE `idcomment` = OLD.`idcomment`);
	END IF;
END
$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`follower_AFTER_INSERT` AFTER INSERT ON `follower` FOR EACH ROW
	UPDATE `user` SET `user`.`followers` = `user`.`followers` + 1 WHERE `user`.`iduser` = NEW.`followeeid`;$$

USE `wto`$$
CREATE DEFINER = CURRENT_USER TRIGGER `wto`.`follower_AFTER_DELETE` AFTER DELETE ON `follower` FOR EACH ROW
	UPDATE `user` SET `user`.`followers` = `user`.`followers` - 1 WHERE `user`.`iduser` = OLD.`followeeid`;$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
