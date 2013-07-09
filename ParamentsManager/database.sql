CREATE SEQUENCE "LOGGER_SEQ" START WITH 1;
CREATE SEQUENCE "PARAMETER_SEQ" START WITH 1;
CREATE SEQUENCE "PARAMETER_TYPE_SEQ" START WITH 1;

CREATE TABLE "APPLICATION_TBL" 
   (	
    "ApplicationID" VARCHAR2(10), 
	"ApplicationName" VARCHAR2(50), 
	"ApplicationHostIP" CHAR(15), 
	"ApplicationStatus" CHAR(10), 
	"ApplicationAdditionalParam1" VARCHAR2(25), 
	"ApplicationAdditionalParam2" VARCHAR2(25), 
	"ApplicationAdditionalParam3" VARCHAR2(25), 
	 CONSTRAINT "APPLICATION_PK" PRIMARY KEY ("ApplicationID") ENABLE
   )
/


CREATE TABLE "LOGGER_TBL" 
   (	
   	"LoggerID" NUMBER, 
	"ApplicationID" VARCHAR2(10), 
	"Action" VARCHAR2(50), 
	"Description" VARCHAR2(250), 
	"Timestamp" TIMESTAMP (6), 
	 CONSTRAINT "LOGGER_PK" PRIMARY KEY ("LoggerID") ENABLE, 
	 CONSTRAINT "LOGGER_FK1" FOREIGN KEY ("ApplicationID")
	 REFERENCES  "APPLICATION_TBL" ("ApplicationID") ENABLE
   )
/

CREATE TABLE "PARAMETER_TYPE_TBL" 
   (	
	"ParameterTypeID" NUMBER, 
	"ParameterType" VARCHAR2(25), 
	"ParameterTypeDescription" VARCHAR2(250), 
	 CONSTRAINT "PARAMETER_TYPE_PK" PRIMARY KEY ("ParameterTypeID") ENABLE, 
	 CONSTRAINT "PARAMETER_TYPE_UK1" UNIQUE ("ParameterType") ENABLE
   )
/

CREATE TABLE "PARAMETER_TBL" 
   (	
   	"ParameterID" NUMBER, 
	"ParameterTypeID" NUMBER, 
	"ApplicationID" VARCHAR2(10), 
	"ParameterName" VARCHAR2(50), 
	"ParameterDescription" VARCHAR2(250), 
	"ParameterStatus" CHAR(10), 
	"ParameterValue" VARCHAR2(250), 
	CONSTRAINT "PARAMETER_PK" 
		PRIMARY KEY ("ParameterID") ENABLE, 
	CONSTRAINT "PARAMETER_UK1" 
		UNIQUE ("ParameterName") ENABLE, 
	CONSTRAINT "PARAMETER_FK1" FOREIGN KEY ("ParameterTypeID")
		REFERENCES "PARAMETER_TYPE_TBL" ("ParameterTypeID") ENABLE, 
	CONSTRAINT "PARAMETER_FK2" FOREIGN KEY ("ApplicationID")
		REFERENCES "APPLICATION_TBL" ("ApplicationID") ENABLE
   )
/

CREATE OR REPLACE PROCEDURE LOGGER_SP(P_APPLICATION_ID IN VARCHAR2, P_ACTION IN VARCHAR2, P_DESCRIPTION IN VARCHAR2, P_TIMESTAMP IN TIMESTAMP,  P_CODE OUT NUMBER)
IS
  c1  SYS_REFCURSOR;
  nid VARCHAR2(10);
 

BEGIN
   	OPEN c1 FOR 
		SELECT "ApplicationID" AS fid FROM "APPLICATION_TBL" 
		WHERE "ApplicationID" = P_APPLICATION_ID AND rownum <= 1; 
   	FETCH c1 INTO nid;
   	CLOSE c1;
 
   	IF nid IS NULL THEN
      	P_CODE := 201;
    ELSE  	
		P_CODE := 200; 
		INSERT INTO "LOGGER_TBL" 
     		("LoggerID", "ApplicationID", "Action", "Description", "Timestamp")
   		VALUES
     		("LOGGER_SEQ".nextval, p_APPLICATION_ID, P_ACTION, P_DESCRIPTION, P_TIMESTAMP);
   	END IF;
   	
   	EXCEPTION
		WHEN OTHERS THEN
			P_CODE := 202;
END;
/

INSERT INTO "APPLICATION_TBL"
	("ApplicationID", "ApplicationName", "ApplicationHostIP", "ApplicationStatus")
VALUES
 	('APP-001', 'SMS-CHAT', '127.0.0.1', 'ACTIVE');

exec LOGGER_SP('APP-001', 'ActionTest', 'DescriptionTest', TO_TIMESTAMP ('10-Sep-02 14:10:10.123000', 'DD-Mon-RR HH24:MI:SS.FF'));

