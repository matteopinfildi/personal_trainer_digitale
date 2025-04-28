-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema personal_trainer_digitale
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema personal_trainer_digitale
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `personal_trainer_digitale` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `personal_trainer_digitale` ;

-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`personal_trainer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`personal_trainer` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`personal_trainer` (
  `cf_personal` VARCHAR(16) NOT NULL,
  `nome` VARCHAR(20) NOT NULL,
  `cognome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`cf_personal`),
  UNIQUE INDEX `cf_personal_UNIQUE` (`cf_personal` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`atleta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`atleta` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`atleta` (
  `cf_atleta` VARCHAR(16) NOT NULL,
  `nome` VARCHAR(20) NOT NULL,
  `cognome` VARCHAR(20) NOT NULL,
  `data_nascita` DATE NOT NULL,
  `cf_personal` VARCHAR(16) NULL DEFAULT NULL,
  PRIMARY KEY (`cf_atleta`),
  UNIQUE INDEX `cf_atleta_UNIQUE` (`cf_atleta` ASC) VISIBLE,
  INDEX `cf_personal_idx` (`cf_personal` ASC) VISIBLE,
  CONSTRAINT `cf_personal`
    FOREIGN KEY (`cf_personal`)
    REFERENCES `personal_trainer_digitale`.`personal_trainer` (`cf_personal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`esercizi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`esercizi` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`esercizi` (
  `codice_es` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NOT NULL,
  `descrizione` TEXT NOT NULL,
  `num_serie` INT UNSIGNED NOT NULL,
  `ripetizioni` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`codice_es`),
  UNIQUE INDEX `codice_es_UNIQUE` (`codice_es` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`scheda_allenamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`scheda_allenamento` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`scheda_allenamento` (
  `id_scheda` INT NOT NULL AUTO_INCREMENT,
  `cf_atleta` VARCHAR(16) NOT NULL,
  `descrizione` TEXT NOT NULL,
  `stato` TINYINT(1) NOT NULL,
  `data_archiviazione` DATE NULL DEFAULT NULL,
  `cf_personal` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id_scheda`, `cf_atleta`),
  UNIQUE INDEX `id_scheda_attiva_UNIQUE` (`id_scheda` ASC) VISIBLE,
  INDEX `cf_personal_idx` (`cf_personal` ASC) VISIBLE,
  INDEX `cf_atleta_idx` (`cf_atleta` ASC) VISIBLE,
  INDEX `c_f_atleta_idx` (`cf_atleta` ASC) VISIBLE,
  CONSTRAINT `c_f_atleta`
    FOREIGN KEY (`cf_atleta`)
    REFERENCES `personal_trainer_digitale`.`atleta` (`cf_atleta`),
  CONSTRAINT `cf_personal_trainer`
    FOREIGN KEY (`cf_personal`)
    REFERENCES `personal_trainer_digitale`.`personal_trainer` (`cf_personal`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`contenuto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`contenuto` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`contenuto` (
  `codice_es` INT NOT NULL,
  `id_scheda` INT NOT NULL,
  PRIMARY KEY (`codice_es`, `id_scheda`),
  INDEX `id_scheda_attiva_idx` (`id_scheda` ASC) VISIBLE,
  CONSTRAINT `codice_eser`
    FOREIGN KEY (`codice_es`)
    REFERENCES `personal_trainer_digitale`.`esercizi` (`codice_es`),
  CONSTRAINT `id_scheda`
    FOREIGN KEY (`id_scheda`)
    REFERENCES `personal_trainer_digitale`.`scheda_allenamento` (`id_scheda`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`sessione_allenamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`sessione_allenamento` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`sessione_allenamento` (
  `cf_atleta` VARCHAR(16) NOT NULL,
  `data_allenamento` DATE NOT NULL,
  `durata` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`cf_atleta`, `data_allenamento`),
  CONSTRAINT `codf_atleta`
    FOREIGN KEY (`cf_atleta`)
    REFERENCES `personal_trainer_digitale`.`atleta` (`cf_atleta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`interagisce`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`interagisce` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`interagisce` (
  `cf_atleta` VARCHAR(16) NOT NULL,
  `data_allenamento` DATE NOT NULL,
  `codice_es` INT NOT NULL,
  `saltato` TINYINT(1) NOT NULL,
  `contrassegnato` TINYINT(1) NOT NULL,
  INDEX `codice_esercizio_idx` (`codice_es` ASC) VISIBLE,
  INDEX `cfisc_atleta_idx` (`cf_atleta` ASC, `data_allenamento` ASC) VISIBLE,
  CONSTRAINT `cfisc_atleta`
    FOREIGN KEY (`cf_atleta` , `data_allenamento`)
    REFERENCES `personal_trainer_digitale`.`sessione_allenamento` (`cf_atleta` , `data_allenamento`),
  CONSTRAINT `codice_esercizio`
    FOREIGN KEY (`codice_es`)
    REFERENCES `personal_trainer_digitale`.`esercizi` (`codice_es`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`macchinario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`macchinario` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`macchinario` (
  `codice_es` INT NOT NULL,
  `nome` VARCHAR(20) NOT NULL,
  `descrizione` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`codice_es`, `nome`),
  INDEX `macchinario_idx` (`nome` ASC) VISIBLE,
  CONSTRAINT `codice_es`
    FOREIGN KEY (`codice_es`)
    REFERENCES `personal_trainer_digitale`.`esercizi` (`codice_es`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `personal_trainer_digitale`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`user` ;

CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`user` (
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `ruolo` ENUM('personal', 'atleta', 'gestore') NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `personal_trainer_digitale` ;

-- -----------------------------------------------------
-- Placeholder table for view `personal_trainer_digitale`.`atleti_personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`atleti_personal` (`cf_atleta` INT, `nome_atleta` INT, `cognome_atleta` INT, `cf_personal` INT, `nome_personal_trainer` INT, `cognome_personal_trainer` INT);

-- -----------------------------------------------------
-- Placeholder table for view `personal_trainer_digitale`.`esercizi_in_schede`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`esercizi_in_schede` (`id_scheda` INT, `cf_atleta` INT, `codice_es` INT, `nome_esercizio` INT, `num_serie` INT, `ripetizioni` INT);

-- -----------------------------------------------------
-- Placeholder table for view `personal_trainer_digitale`.`statistiche_allenamenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`statistiche_allenamenti` (`cf_atleta` INT, `nome` INT, `totale_sessioni` INT, `sessioni_completate` INT);

-- -----------------------------------------------------
-- Placeholder table for view `personal_trainer_digitale`.`utilizzo_macchinari`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`utilizzo_macchinari` (`nome_macchinario` INT, `codice_es` INT, `nome_esercizio` INT, `id_scheda` INT, `cf_atleta` INT, `nome_atleta` INT, `cognome_atleta` INT);

-- -----------------------------------------------------
-- Placeholder table for view `personal_trainer_digitale`.`vista_schede_completa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `personal_trainer_digitale`.`vista_schede_completa` (`id_scheda` INT, `cf_atleta` INT, `descrizione_scheda` INT, `stato_scheda` INT, `codice_es` INT, `nome_esercizio` INT, `descrizione_esercizio` INT, `num_serie` INT, `ripetizioni` INT, `nome_macchinario` INT, `descrizione_macchinario` INT);

-- -----------------------------------------------------
-- procedure aggiorna_esercizi
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`aggiorna_esercizi`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiorna_esercizi`(in var_nome VARCHAR(20), in var_descrizione TEXT, in var_num_serie INT UNSIGNED, in var_ripetizioni INT UNSIGNED)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    START TRANSACTION;

    INSERT INTO esercizi (
        nome, descrizione, num_serie, ripetizioni
    ) VALUES (
        var_nome, var_descrizione, var_num_serie, var_ripetizioni
    );

	COMMIT;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure aggiorna_macchinari
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`aggiorna_macchinari`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `aggiorna_macchinari`(in var_codice_es INT, in var_nome VARCHAR(20), in var_descrizione TEXT)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    START TRANSACTION;

    INSERT INTO `personal_trainer_digitale`.`macchinario` (
        `codice_es`,
        `nome`,
        `descrizione`
    ) VALUES (
        var_codice_es,
        var_nome,
        var_descrizione
    );

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure archivia_scheda_attiva
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`archivia_scheda_attiva`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `archivia_scheda_attiva`(IN var_id_scheda INT)
BEGIN
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;
    RESIGNAL;
  END;

  SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
  START TRANSACTION;

  UPDATE scheda_allenamento
  SET stato = 0, -- 0 = archiviata
      data_archiviazione = CURDATE()
  WHERE id_scheda = var_id_scheda;

  COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure assegna_personal
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`assegna_personal`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `assegna_personal`(in var_cf_atleta VARCHAR(16), in var_cf_personal VARCHAR(16))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    IF NOT EXISTS (SELECT 1 FROM personal_trainer_digitale.personal_trainer WHERE cf_personal = var_cf_personal) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Personal Trainer non trovato';
    END IF;

     IF NOT EXISTS (SELECT 1 FROM personal_trainer_digitale.atleta WHERE cf_atleta = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

    START TRANSACTION;

	UPDATE `personal_trainer_digitale`.`atleta`
    SET `cf_personal` = var_cf_personal
    WHERE `cf_atleta` = var_cf_atleta;

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure associa_esercizio_scheda
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`associa_esercizio_scheda`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `associa_esercizio_scheda`(in var_codice_es INT, in var_id_scheda INT)
BEGIN
	DECLARE esiste_esercizio INT;
    DECLARE esiste_scheda INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    START TRANSACTION;

    SELECT COUNT(*) INTO esiste_esercizio
    FROM `personal_trainer_digitale`.`esercizi`
    WHERE `codice_es` = var_codice_es;

    IF esiste_esercizio = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Esercizio non trovato';
    END IF;

    SELECT COUNT(*) INTO esiste_scheda
    FROM `personal_trainer_digitale`.`scheda_allenamento`
    WHERE `id_scheda` = var_id_scheda AND `stato` = 1;

    IF esiste_scheda = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Scheda di allenamento non trovata';
    END IF;

    INSERT INTO `personal_trainer_digitale`.`contenuto`
    (`codice_es`, `id_scheda`)
    VALUES
    (var_codice_es, var_id_scheda);

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure creazione_scheda_attiva
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`creazione_scheda_attiva`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `creazione_scheda_attiva`(in var_cf_personal VARCHAR(16), in var_cf_atleta VARCHAR(16), in var_descrizione TEXT)
BEGIN
	DECLARE id_vecchia_scheda INT;
    DECLARE descrizione_vecchia TEXT;

	DECLARE EXIT HANDLER FOR sqlexception
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

	SET TRANSACTION ISOLATION LEVEL repeatable read;

    IF NOT EXISTS (SELECT 1 FROM personal_trainer_digitale.personal_trainer WHERE cf_personal = var_cf_personal) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Personal Trainer non trovato';
    END IF;

    SELECT id_scheda, descrizione
    INTO id_vecchia_scheda, descrizione_vecchia
    FROM personal_trainer_digitale.scheda_allenamento
    WHERE cf_atleta = var_cf_atleta AND stato = 1
    LIMIT 1;

    IF id_vecchia_scheda IS NOT NULL THEN
        UPDATE personal_trainer_digitale.scheda_allenamento
        SET stato = 0, data_archiviazione = CURDATE()
        WHERE id_scheda = id_vecchia_scheda;
    END IF;

    INSERT INTO personal_trainer_digitale.scheda_allenamento (
        descrizione, stato, data_archiviazione, cf_atleta, cf_personal
    )
    VALUES (
        var_descrizione, 1, NULL, var_cf_atleta, var_cf_personal
    );

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure elimina_esercizio
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`elimina_esercizio`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `elimina_esercizio`(in var_codice_es INT)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    START TRANSACTION;

    DELETE FROM `personal_trainer_digitale`.`contenuto`
    WHERE `codice_es` = var_codice_es;

	DELETE FROM `personal_trainer_digitale`.`interagisce`
    WHERE `codice_es` = var_codice_es;

    DELETE FROM `personal_trainer_digitale`.`macchinario`
    WHERE `codice_es` = var_codice_es;

	DELETE FROM `personal_trainer_digitale`.`esercizi`
    WHERE `codice_es` = var_codice_es;

    COMMIT;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure elimina_macchinario
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`elimina_macchinario`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `elimina_macchinario`(in var_nome VARCHAR(20))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    START TRANSACTION;

    DELETE FROM `personal_trainer_digitale`.`macchinario`
    WHERE `nome` = var_nome;

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure esercizio_completato
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`esercizio_completato`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `esercizio_completato`(in var_cf_atleta VARCHAR(16), in var_codice_es INT, in var_data_allenamento DATE)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    START TRANSACTION;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`sessione_allenamento` WHERE `cf_atleta` = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

	IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`esercizi` WHERE `codice_es` = var_codice_es) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Esercizio non trovato';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`sessione_allenamento` WHERE `data_allenamento` = var_data_allenamento) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Allenamento non trovato in quella data';
    END IF;

	IF EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`interagisce`
               WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento` = var_data_allenamento AND `codice_es` = var_codice_es AND `contrassegnato` = 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'L\'esercizio è già stato completato';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`interagisce`
                   WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento` = var_data_allenamento AND `codice_es` = var_codice_es) THEN
        INSERT INTO `personal_trainer_digitale`.`interagisce` (cf_atleta, data_allenamento, codice_es, saltato, contrassegnato)
        VALUES (var_cf_atleta, var_data_allenamento, var_codice_es, 0, 1);
    ELSE
		UPDATE `personal_trainer_digitale`.`interagisce`
		SET `contrassegnato` = 1
		WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento` = var_data_allenamento AND `codice_es` = var_codice_es;
    END IF;

	COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure esercizio_saltato
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`esercizio_saltato`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `esercizio_saltato`(in var_cf_atleta VARCHAR(16), in var_codice_es INT,  in var_data_allenamento DATE)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

    START TRANSACTION;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`sessione_allenamento` WHERE `cf_atleta` = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`esercizi` WHERE `codice_es` = var_codice_es) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Esercizio non trovato';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`sessione_allenamento` WHERE `data_allenamento` = var_data_allenamento) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Sessione non trovata per quella data';
    END IF;

    IF EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`interagisce`
               WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento`= var_data_allenamento AND `codice_es` = var_codice_es AND `saltato` = 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'L\'esercizio è già stato saltato';
    END IF;

    IF EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`interagisce`
               WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento`= var_data_allenamento AND `codice_es` = var_codice_es AND `contrassegnato` = 1) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'L\'esercizio è già stato completato';
    END IF;

    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`interagisce`
                   WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento`= var_data_allenamento AND `codice_es` = var_codice_es) THEN
        INSERT INTO `personal_trainer_digitale`.`interagisce` (cf_atleta, data_allenamento, codice_es, saltato, contrassegnato)
        VALUES (var_cf_atleta, var_data_allenamento, var_codice_es, 1, 0);
    ELSE
		UPDATE `personal_trainer_digitale`.`interagisce`
		SET `saltato` = 1
		WHERE `cf_atleta` = var_cf_atleta AND `data_allenamento` AND `codice_es` = var_codice_es;
    END IF;

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure genera_report
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`genera_report`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `genera_report`(
    IN var_data_inizio DATE,
    IN var_data_fine DATE,
    IN var_cf_personal VARCHAR(16)
)
BEGIN
    SELECT
        a.cf_atleta,
        CONCAT(a.nome, ' ', a.cognome) AS nome_atleta,
        sa.data_allenamento,
        sa.durata,
        s.id_scheda,
        s.descrizione AS descrizione_scheda,
        COUNT(i.codice_es) AS totale_esercizi,
        SUM(CASE WHEN i.saltato = 0 THEN 1 ELSE 0 END) AS esercizi_completati,
        SUM(CASE WHEN i.saltato = 1 THEN 1 ELSE 0 END) AS esercizi_saltati,
        ROUND(
            IFNULL(SUM(CASE WHEN i.saltato = 0 THEN 1 ELSE 0 END) * 100.0 / NULLIF(COUNT(i.codice_es), 0), 0),
            2
        ) AS percentuale_completamento
    FROM atleta a
    JOIN sessione_allenamento sa
        ON a.cf_atleta = sa.cf_atleta
    JOIN interagisce i
        ON i.cf_atleta = sa.cf_atleta AND i.data_allenamento = sa.data_allenamento
    JOIN contenuto c
        ON c.codice_es = i.codice_es
    JOIN scheda_allenamento s
        ON s.id_scheda = c.id_scheda AND s.cf_atleta = a.cf_atleta
        AND (s.data_archiviazione IS NULL OR sa.data_allenamento <= s.data_archiviazione)
    WHERE sa.data_allenamento BETWEEN var_data_inizio AND var_data_fine
      AND a.cf_personal = var_cf_personal
    GROUP BY
        a.cf_atleta, a.nome, a.cognome,
        sa.data_allenamento, sa.durata,
        s.id_scheda, s.descrizione
    ORDER BY a.cf_atleta, sa.data_allenamento;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisci_atleta
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`inserisci_atleta`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_atleta`(in var_cf_atleta VARCHAR(16), in var_nome VARCHAR(20), in var_cognome VARCHAR(20), in var_data_nascita DATE)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;

    IF EXISTS (
        SELECT 1
        FROM `personal_trainer_digitale`.`atleta`
        WHERE `cf_atleta` = var_cf_atleta
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Atleta già presente nel sistema';
    END IF;

    INSERT INTO `personal_trainer_digitale`.`atleta` (
        `cf_atleta`, `nome`, `cognome`, `data_nascita`, `cf_personal`
    )
    VALUES (
        var_cf_atleta, var_nome, var_cognome, var_data_nascita, NULL
    );

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserisci_personal_trainer
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`inserisci_personal_trainer`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserisci_personal_trainer`(in var_cf_personal VARCHAR(16), in var_nome VARCHAR(20), in var_cognome VARCHAR(20))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;
    START TRANSACTION;

    IF EXISTS (
        SELECT 1
        FROM `personal_trainer_digitale`.`personal_trainer`
        WHERE `cf_personal` = var_cf_personal
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Personal trainer già presente nel sistema';
    END IF;

    INSERT INTO `personal_trainer_digitale`.`personal_trainer` (
        `cf_personal`, `nome`, `cognome`
    )
    VALUES (
        var_cf_personal, var_nome, var_cognome
    );

    COMMIT;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`login`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(in var_username VARCHAR(30), in var_password VARCHAR(20), out ruolo_out INT)
BEGIN
	DECLARE var_ruolo ENUM('personal', 'atleta', 'gestore');

    SELECT `ruolo`
    into var_ruolo
    FROM `user`
    WHERE `username` = var_username and `password` = var_password;

    IF var_ruolo = 'personal' THEN
		SET ruolo_out = 1;
	ELSEIF var_ruolo = 'atleta' THEN
		SET ruolo_out = 2;
	ELSEIF var_ruolo = 'gestore' THEN
		SET ruolo_out = 3;
	END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure registrazione_allenamento
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`registrazione_allenamento`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrazione_allenamento`(in var_cf_atleta VARCHAR(16), in var_data_allenamento DATE, in var_durata INT UNSIGNED)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

    SET TRANSACTION ISOLATION LEVEL REPEATABLE READ;

    START TRANSACTION;
    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`atleta` WHERE `cf_atleta` = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

     INSERT INTO `personal_trainer_digitale`.`sessione_allenamento`
        (`cf_atleta`, `data_allenamento`, `durata`)
    VALUES
        (var_cf_atleta, var_data_allenamento, var_durata);

	COMMIT;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure stampa_esercizio
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`stampa_esercizio`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `stampa_esercizio`(in var_codice_es INT)
BEGIN
	DECLARE esercizio_nome VARCHAR(20);
    DECLARE num_serie INT;

    SELECT e.nome, e.num_serie
    INTO esercizio_nome, num_serie
    FROM personal_trainer_digitale.esercizi e
    WHERE e.codice_es = var_codice_es;

    IF esercizio_nome IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Esercizio non trovato!';
    ELSE
		SELECT esercizio_nome AS nome_esercizio, num_serie;
    END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_scheda_archiviata
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`visualizza_scheda_archiviata`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_scheda_archiviata`(in var_cf_atleta VARCHAR(16))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
    SET TRANSACTION READ ONLY;

    IF NOT EXISTS (SELECT 1 FROM personal_trainer_digitale.atleta WHERE cf_atleta = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM personal_trainer_digitale.scheda_allenamento
        WHERE cf_atleta = var_cf_atleta AND stato = 0
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Nessuna scheda archiviata per questo atleta';
    END IF;

    START TRANSACTION;

    SELECT
        id_scheda,
        descrizione,
        data_archiviazione,
        cf_atleta
    FROM personal_trainer_digitale.scheda_allenamento
    WHERE cf_atleta = var_cf_atleta AND stato = 0;

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure visualizza_scheda_attiva
-- -----------------------------------------------------

USE `personal_trainer_digitale`;
DROP procedure IF EXISTS `personal_trainer_digitale`.`visualizza_scheda_attiva`;

DELIMITER $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `visualizza_scheda_attiva`(in var_cf_atleta VARCHAR(16))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        RESIGNAL;
	END;

	SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
	SET TRANSACTION READ ONLY;

    START TRANSACTION;
    IF NOT EXISTS (SELECT 1 FROM `personal_trainer_digitale`.`atleta` WHERE `cf_atleta` = var_cf_atleta) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Atleta non trovato';
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM `personal_trainer_digitale`.`scheda_allenamento`
        WHERE `cf_atleta` = var_cf_atleta AND stato = 1
        LIMIT 1
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Nessuna scheda attiva trovata per l\'atleta';
    END IF;

    SELECT
        `id_scheda`,
        `descrizione`,
        `cf_personal`
    FROM `personal_trainer_digitale`.`scheda_allenamento`
    WHERE `cf_atleta` = var_cf_atleta AND stato = 1
    LIMIT 1;

    COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `personal_trainer_digitale`.`atleti_personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`atleti_personal`;
DROP VIEW IF EXISTS `personal_trainer_digitale`.`atleti_personal` ;
USE `personal_trainer_digitale`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personal_trainer_digitale`.`atleti_personal` AS select `a`.`cf_atleta` AS `cf_atleta`,`a`.`nome` AS `nome_atleta`,`a`.`cognome` AS `cognome_atleta`,`pt`.`cf_personal` AS `cf_personal`,`pt`.`nome` AS `nome_personal_trainer`,`pt`.`cognome` AS `cognome_personal_trainer` from (`personal_trainer_digitale`.`atleta` `a` join `personal_trainer_digitale`.`personal_trainer` `pt` on((`a`.`cf_personal` = `pt`.`cf_personal`)));

-- -----------------------------------------------------
-- View `personal_trainer_digitale`.`esercizi_in_schede`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`esercizi_in_schede`;
DROP VIEW IF EXISTS `personal_trainer_digitale`.`esercizi_in_schede` ;
USE `personal_trainer_digitale`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personal_trainer_digitale`.`esercizi_in_schede` AS select `sa`.`id_scheda` AS `id_scheda`,`sa`.`cf_atleta` AS `cf_atleta`,`e`.`codice_es` AS `codice_es`,`e`.`nome` AS `nome_esercizio`,`e`.`num_serie` AS `num_serie`,`e`.`ripetizioni` AS `ripetizioni` from ((`personal_trainer_digitale`.`scheda_allenamento` `sa` join `personal_trainer_digitale`.`contenuto` `c` on((`sa`.`id_scheda` = `c`.`id_scheda`))) join `personal_trainer_digitale`.`esercizi` `e` on((`c`.`codice_es` = `e`.`codice_es`)));

-- -----------------------------------------------------
-- View `personal_trainer_digitale`.`statistiche_allenamenti`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`statistiche_allenamenti`;
DROP VIEW IF EXISTS `personal_trainer_digitale`.`statistiche_allenamenti` ;
USE `personal_trainer_digitale`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personal_trainer_digitale`.`statistiche_allenamenti` AS select `a`.`cf_atleta` AS `cf_atleta`,`a`.`nome` AS `nome`,count(distinct `s`.`data_allenamento`) AS `totale_sessioni`,count(distinct (case when (`i`.`saltato` = 0) then `s`.`data_allenamento` end)) AS `sessioni_completate` from ((`personal_trainer_digitale`.`atleta` `a` left join `personal_trainer_digitale`.`sessione_allenamento` `s` on((`a`.`cf_atleta` = `s`.`cf_atleta`))) left join `personal_trainer_digitale`.`interagisce` `i` on(((`s`.`cf_atleta` = `i`.`cf_atleta`) and (`s`.`data_allenamento` = `i`.`data_allenamento`)))) group by `a`.`cf_atleta`,`a`.`nome`;

-- -----------------------------------------------------
-- View `personal_trainer_digitale`.`utilizzo_macchinari`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`utilizzo_macchinari`;
DROP VIEW IF EXISTS `personal_trainer_digitale`.`utilizzo_macchinari` ;
USE `personal_trainer_digitale`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personal_trainer_digitale`.`utilizzo_macchinari` AS select `m`.`nome` AS `nome_macchinario`,`e`.`codice_es` AS `codice_es`,`e`.`nome` AS `nome_esercizio`,`sa`.`id_scheda` AS `id_scheda`,`sa`.`cf_atleta` AS `cf_atleta`,`a`.`nome` AS `nome_atleta`,`a`.`cognome` AS `cognome_atleta` from ((((`personal_trainer_digitale`.`macchinario` `m` join `personal_trainer_digitale`.`esercizi` `e` on((`m`.`codice_es` = `e`.`codice_es`))) join `personal_trainer_digitale`.`contenuto` `c` on((`e`.`codice_es` = `c`.`codice_es`))) join `personal_trainer_digitale`.`scheda_allenamento` `sa` on((`c`.`id_scheda` = `sa`.`id_scheda`))) join `personal_trainer_digitale`.`atleta` `a` on((`sa`.`cf_atleta` = `a`.`cf_atleta`)));

-- -----------------------------------------------------
-- View `personal_trainer_digitale`.`vista_schede_completa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_trainer_digitale`.`vista_schede_completa`;
DROP VIEW IF EXISTS `personal_trainer_digitale`.`vista_schede_completa` ;
USE `personal_trainer_digitale`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `personal_trainer_digitale`.`vista_schede_completa` AS select `s`.`id_scheda` AS `id_scheda`,`s`.`cf_atleta` AS `cf_atleta`,`s`.`descrizione` AS `descrizione_scheda`,(case when (`s`.`stato` = 1) then 'Attiva' else 'Archiviata' end) AS `stato_scheda`,`e`.`codice_es` AS `codice_es`,`e`.`nome` AS `nome_esercizio`,`e`.`descrizione` AS `descrizione_esercizio`,`e`.`num_serie` AS `num_serie`,`e`.`ripetizioni` AS `ripetizioni`,`m`.`nome` AS `nome_macchinario`,`m`.`descrizione` AS `descrizione_macchinario` from (((`personal_trainer_digitale`.`scheda_allenamento` `s` join `personal_trainer_digitale`.`contenuto` `c` on((`s`.`id_scheda` = `c`.`id_scheda`))) join `personal_trainer_digitale`.`esercizi` `e` on((`c`.`codice_es` = `e`.`codice_es`))) left join `personal_trainer_digitale`.`macchinario` `m` on((`e`.`codice_es` = `m`.`codice_es`)));
USE `personal_trainer_digitale`;

DELIMITER $$

USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`check_lunghezza_cf_atleta` $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `check_lunghezza_cf_atleta` BEFORE INSERT ON `atleta` FOR EACH ROW BEGIN
	IF CHAR_LENGTH(NEW.cf_atleta) != 16 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Il codice fiscale dell\'atleta deve essere lungo esattamente 16 caratteri.';
  END IF;
END$$

USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`check_lunghezza_cf_personal` $$
USE `personal_trainer_digitale`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `personal_trainer_digitale`.`check_lunghezza_cf_personal`
BEFORE INSERT ON `personal_trainer_digitale`.`personal_trainer`
FOR EACH ROW
BEGIN
	IF CHAR_LENGTH(NEW.cf_personal) != 16 THEN
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Il codice fiscale del personal trainer deve essere lungo esattamente 16 caratteri.';
	END IF;
END$$


USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`verifica_durata_sessione` $$
USE `personal_trainer_digitale`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `personal_trainer_digitale`.`verifica_durata_sessione`
BEFORE INSERT ON `personal_trainer_digitale`.`sessione_allenamento`
FOR EACH ROW
BEGIN
	 IF NEW.durata < 30 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La durata minima di una sessione è di 30 minuti';
    END IF;
END$$

USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`max_5_esercizi_per_scheda` $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `max_5_esercizi_per_scheda` BEFORE INSERT ON `contenuto` FOR EACH ROW BEGIN
	DECLARE numero_esercizi INT;

    SELECT COUNT(*) INTO numero_esercizi
    FROM contenuto
    WHERE id_scheda = NEW.id_scheda;

    IF numero_esercizi >= 5 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Non è possibile aggiungere più di 5 esercizi a una scheda';
    END IF;
END$$

USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`max_3_esercizi_saltati` $$
USE `personal_trainer_digitale`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `personal_trainer_digitale`.`max_3_esercizi_saltati`
BEFORE INSERT ON `personal_trainer_digitale`.`interagisce`
FOR EACH ROW
BEGIN
    DECLARE esercizi_saltati INT;

    SELECT COUNT(*) INTO esercizi_saltati
    FROM interagisce
    WHERE cf_atleta = NEW.cf_atleta
      AND data_allenamento = NEW.data_allenamento
      AND saltato = 1;

    IF NEW.saltato = 1 THEN
        SET esercizi_saltati = esercizi_saltati + 1;
    END IF;

    IF esercizi_saltati > 3 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Non è possibile saltare più di 3 esercizi per sessione';
    END IF;
END$$


USE `personal_trainer_digitale`$$
DROP TRIGGER IF EXISTS `personal_trainer_digitale`.`check_singola_interazione` $$
USE `personal_trainer_digitale`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `check_singola_interazione` BEFORE INSERT ON `interagisce` FOR EACH ROW BEGIN
    -- Controlla che l'esercizio non sia contemporaneamente contrassegnato e saltato
    IF NEW.contrassegnato = 1 AND NEW.saltato = 1 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Un esercizio non può essere completato e saltato insieme';
    END IF;

    -- Controlla che un esercizio già contrassegnato non venga poi segnato come saltato
    IF NEW.saltato = 1 THEN
        IF EXISTS (
            SELECT 1
            FROM interagisce
            WHERE cf_atleta = NEW.cf_atleta
              AND data_allenamento = NEW.data_allenamento
              AND codice_es = NEW.codice_es
              AND contrassegnato = 1
        ) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Non è possibile segnare come saltato un esercizio già completato';
        END IF;
    END IF;
END$$

DELIMITER ;

SET SQL_MODE = '';
DROP USER IF EXISTS loginUser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'loginUser' IDENTIFIED BY 'login';

GRANT EXECUTE ON procedure `personal_trainer_digitale`.`login` TO 'loginUser';
SET SQL_MODE = '';


DROP USER IF EXISTS atletaUser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'atletaUser' IDENTIFIED BY 'atleta';

GRANT EXECUTE ON procedure `personal_trainer_digitale`.`esercizio_completato` TO 'atletaUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`esercizio_saltato` TO 'atletaUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`visualizza_scheda_attiva` TO 'atletaUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`visualizza_scheda_archiviata` TO 'atletaUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`registrazione_allenamento` TO 'atletaUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`stampa_esercizio` TO 'atletaUser';
GRANT SELECT ON personal_trainer_digitale.scheda_allenamento TO 'atletaUser';
SET SQL_MODE = '';

DROP USER IF EXISTS personalUser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'personalUser' IDENTIFIED BY 'personal';

GRANT EXECUTE ON procedure `personal_trainer_digitale`.`assegna_personal` TO 'personalUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`creazione_scheda_attiva` TO 'personalUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`associa_esercizio_scheda` TO 'personalUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`archivia_scheda_attiva` TO 'personalUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`genera_report` TO 'personalUser';
GRANT UPDATE ON personal_trainer_digitale.atleta TO 'personalUser';
GRANT SELECT ON personal_trainer_digitale.atleta TO 'personalUser';
GRANT SELECT ON personal_trainer_digitale.esercizi TO 'personalUser';
GRANT SELECT ON personal_trainer_digitale.scheda_allenamento TO 'personalUser';
SET SQL_MODE = '';

DROP USER IF EXISTS gestoreUser;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gestoreUser' IDENTIFIED BY 'gestore';

GRANT EXECUTE ON procedure `personal_trainer_digitale`.`aggiorna_esercizi` TO 'gestoreUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`aggiorna_macchinari` TO 'gestoreUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`elimina_esercizio` TO 'gestoreUser';
GRANT EXECUTE ON procedure `personal_trainer_digitale`.`elimina_macchinario` TO 'gestoreUser';
GRANT SELECT ON personal_trainer_digitale.esercizi TO 'gestoreUser';
SET SQL_MODE = '';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `personal_trainer_digitale`.`personal_trainer`
-- -----------------------------------------------------
START TRANSACTION;
USE `personal_trainer_digitale`;
INSERT INTO `personal_trainer_digitale`.`personal_trainer` (`cf_personal`, `nome`, `cognome`) VALUES ('aaaabbbbccccdddd', 'Mario', 'Rossi');

COMMIT;

-- -----------------------------------------------------
-- Data for table `personal_trainer_digitale`.`atleta`
-- -----------------------------------------------------
START TRANSACTION;
USE `personal_trainer_digitale`;
INSERT INTO `personal_trainer_digitale`.`atleta` (`cf_atleta`, `nome`, `cognome`, `data_nascita`, `cf_personal`) VALUES ('1234567890123456', 'Matteo', 'Pinfildi', '2003-02-15', 'aaaabbbbccccdddd');

COMMIT;

-- -----------------------------------------------------
-- Data for table `personal_trainer_digitale`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `personal_trainer_digitale`;
INSERT INTO `personal_trainer_digitale`.`user` (`username`, `password`, `ruolo`) VALUES ('atl', 'atl123', 'atleta');
INSERT INTO `personal_trainer_digitale`.`user` (`username`, `password`, `ruolo`) VALUES ('per', 'per123', 'personal');
INSERT INTO `personal_trainer_digitale`.`user` (`username`, `password`, `ruolo`) VALUES ('ges', 'ges123', 'gestore');

COMMIT;