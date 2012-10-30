DROP DATABASE IF EXISTS swap;
CREATE DATABASE swap;
Use swap;

CREATE TABLE changeLog (
  change_number BIGINT NOT NULL,
  complete_dt TIMESTAMP NOT NULL,
  applied_by VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL
);


ALTER TABLE changeLog ADD CONSTRAINT pkChangeLog PRIMARY KEY (change_number);
