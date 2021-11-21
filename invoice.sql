CREATE DATABASE invoice;
USE invoice;
SET NAMES cp1251;
CREATE TABLE invoice (
	inv_date	DATE NOT NULL,
	inv_number	INT UNSIGNED NOT NULL,
	inv_sum		BIGINT NOT NULL,
	inv_comment VARCHAR (256) NULL
	);
