

/* Create Sequences */

CREATE SEQUENCE BLVE.SEQ_SUBSCRIBEB_TBL INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1;
CREATE SEQUENCE BLVE.DEMO_CUST_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.DEMO_ORDER_ITEMS_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.DEMO_ORD_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.DEMO_PROD_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.DEMO_USERS_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1;
CREATE SEQUENCE BLVE.LOGGER_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.PARAMETER_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;
CREATE SEQUENCE BLVE.PARAMETER_TYPE_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 START WITH 1 ;



/* Create Tables */

CREATE TABLE PROMOTION
(
	PROMOTIONID VARCHAR2(50) NOT NULL,
	SHORTNUMBER VARCHAR2(50)
);


CREATE TABLE BLVE.SUBSCRIBEA_TBL
(
	SUBSCRIBEAID NUMBER NOT NULL,
	ALIAS VARCHAR2(20),
	ESTADO VARCHAR2(15),
	MSISDN VARCHAR2(9) NOT NULL,
	FECHACOBRO TIMESTAMP,
	FECHAULTIMAACTIVIDAD TIMESTAMP,
	PRIMARY KEY (SUBSCRIBEAID)
);


CREATE TABLE BLVE.SUBSCRIBEB_TBL
(
	SUBSCRIBEBID NUMBER NOT NULL,
	SUBSCRIBEAID NUMBER NOT NULL,
	ALIAS VARCHAR2(20),
	ESTADO VARCHAR2(15),
	MSISDN VARCHAR2(9),
	FECHACOBRO TIMESTAMP,
	FECHAULTIMAACTIVIDAD TIMESTAMP,
	FECHACREACION TIMESTAMP DEFAULT SYSDATE,
	PRIMARY KEY (SUBSCRIBEBID)
);


CREATE TABLE BLVE.APPLICATION_TBL
(
	APPLICATIONID VARCHAR2(10) NOT NULL,
	APPLICATIONNAME VARCHAR2(50),
	APPLICATIONHOSTIP CHAR(15),
	APPLICATIONSTATUS CHAR(10),
	APPLICATIONADDITIONALPARAM1 VARCHAR2(25),
	APPLICATIONADDITIONALPARAM2 VARCHAR2(25),
	APPLICATIONADDITIONALPARAM3 VARCHAR2(25),
	CONSTRAINT APPLICATION_PK PRIMARY KEY (APPLICATIONID)
);


CREATE TABLE BLVE.LOGGER_TBL
(
	LOGGERID NUMBER NOT NULL,
	TIMESTAMP TIMESTAMP,
	APPLICATIONID VARCHAR2(10),
	DESCRIPTION VARCHAR2(250),
	ACTION VARCHAR2(50),
	CONSTRAINT LOGGER_PK PRIMARY KEY (LOGGERID)
);


CREATE TABLE BLVE.PARAMETER_TBL
(
	PARAMETERID NUMBER NOT NULL,
	PARAMETERTYPEID NUMBER,
	APPLICATIONID VARCHAR2(10),
	PARAMETERNAME VARCHAR2(50) UNIQUE,
	PARAMETERDESCRIPTION VARCHAR2(250),
	PARAMETERSTATUS CHAR(10),
	PARAMETERVALUE VARCHAR2(250),
	CONSTRAINT PARAMETER_PK PRIMARY KEY (PARAMETERID)
);


CREATE TABLE BLVE.PARAMETER_TYPE_TBL
(
	PARAMETERTYPEID NUMBER NOT NULL,
	PARAMETERTYPE VARCHAR2(25) UNIQUE,
	PARAMETERTYPEDESCRIPTION VARCHAR2(250),
	CONSTRAINT PARAMETER_TYPE_PK PRIMARY KEY (PARAMETERTYPEID)
);



/* Create Foreign Keys */

ALTER TABLE SUBSCRIBEB_TBL
	ADD FOREIGN KEY (SUBSCRIBEAID)
	REFERENCES SUBSCRIBEA_TBL (SUBSCRIBEAID)
;


ALTER TABLE BLVE.PARAMETER_TBL
	ADD CONSTRAINT PARAMETER_FK2 FOREIGN KEY (APPLICATIONID)
	REFERENCES BLVE.APPLICATION_TBL (APPLICATIONID)
;


ALTER TABLE BLVE.PARAMETER_TBL
	ADD CONSTRAINT PARAMETER_FK1 FOREIGN KEY (PARAMETERTYPEID)
	REFERENCES BLVE.PARAMETER_TYPE_TBL (PARAMETERTYPEID)
;



