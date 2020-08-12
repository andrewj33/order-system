/* *****************************************************************************
FILE: jdbc_dao_assignment.sql
NAME: JDBCDAOAssignment
DESC: The SQL script for the OrderSystem program
AUTH: Andrew Hanson
DATE: 2019-12-01
***************************************************************************** */

DROP DATABASE IF EXISTS JDBCDAOAssignment;
CREATE DATABASE JDBCDAOAssignment;
USE JDBCDAOAssignment;

/* *****************************************************************************
	Create statement for table Order_Record
***************************************************************************** */
DROP TABLE IF EXISTS Order_Record;
CREATE TABLE Order_Record(
	Order_Number VARCHAR(256) NOT NULL COMMENT 'The unique identifier number for the Order Record.'
	, Order_Date DATE NOT NULL COMMENT 'The order date of the Order Record.'
	, Vendor_Id INT NOT NULL COMMENT 'The vendor identifier of the Order Record.'
	, PRIMARY KEY(Order_Number)
) COMMENT 'Data about an Order Record.'
;

/* *****************************************************************************
	Build Stored Procedure sp_add_Order_Record
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_add_Order_Record$$
CREATE PROCEDURE sp_add_Order_Record(
	IN p_Order_Number VARCHAR(256)
	, IN p_Order_Date DATE
	, IN p_Vendor_Id INT
) COMMENT'Adds a record to the Order_Record table.'
BEGIN

	INSERT INTO Order_Record(
		Order_Number
		, Order_Date
		, Vendor_Id
	)
	VALUES (
		p_Order_Number
		, p_Order_Date
		, p_Vendor_Id
	);
    
END$$
DELIMITER ;

/* *****************************************************************************
	Build Stored Procedure sp_get_Order_Record_by_Order_Number
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_get_Order_Record_by_Order_Number$$
CREATE PROCEDURE sp_get_Order_Record_by_Order_Number(
		IN p_Order_Number VARCHAR(256)
) COMMENT'Gets a record from the Order_Record table.'
BEGIN
	
	SELECT 
		Order_Number
		, Order_Date
		, Vendor_Id
	FROM Order_Record
	WHERE Order_Number = p_Order_Number
	;

END$$
DELIMITER ;

/* *****************************************************************************
	Build Stored Procedure sp_get_all_Order_Record
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_get_all_Order_Record$$
CREATE PROCEDURE sp_get_all_Order_Record( 
) COMMENT'Gets all records from the Order_Record table.'
BEGIN
	
	SELECT 
		Order_Number
		, Order_Date
		, Vendor_Id
	FROM Order_Record
	;

END$$
DELIMITER ;

/* *****************************************************************************
	Build Stored Procedure sp_update_Order_Record
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_Order_Record$$
CREATE PROCEDURE sp_update_Order_Record(
	IN p_original_Order_Number VARCHAR(256)
	, IN p_original_Order_Date DATE
	, IN p_original_Vendor_Id INT
	, IN p_updated_Order_Date DATE
	, IN p_updated_Vendor_Id INT
) COMMENT'Updates a record in the Order_Record table.'
BEGIN

	DECLARE var_record_count INT DEFAULT 0;

	SELECT COUNT(*) INTO var_record_count
	FROM Order_Record
	WHERE 
		Order_Number = p_original_Order_Number
		AND Order_Date = p_original_Order_Date
		AND Vendor_Id = p_original_VendorId
	;
	
	IF var_record_count <> 1 THEN
		SIGNAL SQLSTATE '53000'
		SET MESSAGE_TEXT = 'Cannot update; no such record.';
	END IF;
	
    UPDATE Order_Record
		SET Order_Number = p_original_Order_Number
		, Order_Date = p_updated_Order_Date
		, Vendor_Id = p_updated_Vendor_Id
	WHERE 
		Order_Number = p_original_Order_Number
		AND Order_Date = p_original_Order_Date
		AND Vendor_Id = p_original_Vendor_Id
	;
	
	SELECT ROW_COUNT() AS 'Updated';
	
	
END$$
DELIMITER ;

/* *****************************************************************************
	Build Stored Procedure sp_delete_from_Order_Record
***************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_delete_from_Order_Record$$
CREATE PROCEDURE sp_delete_from_Order_Record(
	IN p_original_Order_Number VARCHAR(256)
	, IN p_original_Order_Date DATE
	, IN p_original_Vendor_Id INT
) COMMENT'Deletes a record in the Order_Record table.'
BEGIN
	
	DELETE FROM Order_Record
	WHERE 
		Order_Number = p_Order_Number
		AND Order_Date = p_Order_Date
		AND Vendor_Id = p_Vendor_Id
	;
	
	SELECT ROW_COUNT() AS 'Deleted';
	
	
END$$
DELIMITER ;

/* *****************************************************************************
	Create a user and grant permission to execute the stored procedures.
***************************************************************************** */
DROP USER IF EXISTS 'SQLDAOUser'@'%';
CREATE USER 'SQLDAOUser'@'%' IDENTIFIED BY 'week_password';

GRANT EXECUTE ON PROCEDURE JDBCDAOExample.sp_add_Order_Record TO 'SQLDAOUser'@'%';
GRANT EXECUTE ON PROCEDURE JDBCDAOExample.sp_get_Order_Record_by_Order_Number TO 'SQLDAOUser'@'%';
GRANT EXECUTE ON PROCEDURE JDBCDAOExample.sp_get_all_Order_Record TO 'SQLDAOUser'@'%';
GRANT EXECUTE ON PROCEDURE JDBCDAOExample.sp_update_Order_Record TO 'SQLDAOUser'@'%';
GRANT EXECUTE ON PROCEDURE JDBCDAOExample.sp_delete_from_Order_Record TO 'SQLDAOUser'@'%';