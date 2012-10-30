DROP DATABASE IF EXISTS Swap;
CREATE DATABASE Swap;
USE Swap;

CREATE TABLE ChangeLog (
  change_number BIGINT NOT NULL,
  complete_dt TIMESTAMP NOT NULL,
  applied_by VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL
);

ALTER TABLE ChangeLog ADD CONSTRAINT pkChangeLog PRIMARY KEY (change_number);