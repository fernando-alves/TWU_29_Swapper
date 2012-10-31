
-- START UNDO OF CHANGE SCRIPT #1: 01_initializeDatabase.sql

START TRANSACTION;



DELETE FROM changelog WHERE change_number = 1;

COMMIT;

-- END UNDO OF CHANGE SCRIPT #1: 01_initializeDatabase.sql

