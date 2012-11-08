ALTER TABLE Offer ADD COLUMN Hidden boolean DEFAULT false;

ALTER TABLE Offer ADD COLUMN CreationTime DATE;

UPDATE Offer SET CreationTime = SYSDATE();

ALTER TABLE Offer MODIFY CreationTime DATE NOT NULL;
