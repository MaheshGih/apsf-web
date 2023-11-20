
/**14-03-2019*/
ALTER TABLE apsf.state 
ADD COLUMN is_active CHAR(1) NOT NULL DEFAULT 'N' AFTER country_id;

UPDATE apsf.state SET is_active='Y' WHERE id='2';

INSERT INTO apsf.profession (name, is_active) VALUES ('PRIVATE', 'Y');
INSERT INTO apsf.profession (name, is_active) VALUES ('GOVERNMENT', 'Y');
INSERT INTO apsf.profession (name, is_active) VALUES ('STUDENT', 'Y');
INSERT INTO apsf.profession (name, is_active) VALUES ('PROFESSIONAL', 'Y');

INSERT INTO apsf.qualification (name, is_active) VALUES ('SSC', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('INTERMEDIATE', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('GRADUATE', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('MBBS', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('CA', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('POST GRADUATE', 'Y');
INSERT INTO apsf.qualification (name, is_active) VALUES ('PHD', 'Y');

INSERT INTO apsf.caste (name) VALUES ('OC');
INSERT INTO apsf.caste (name) VALUES ('BC');
INSERT INTO apsf.caste (name) VALUES ('SC');
INSERT INTO apsf.caste (name) VALUES ('ST');

INSERT INTO department(name,is_active) values('SOFTWARE','Y');
INSERT INTO department(name,is_active) values('MARKETING','Y');
INSERT INTO department(name,is_active) values('ADMINISTRATION','Y');
INSERT INTO department(name,is_active) values('NETWORKING','Y');
INSERT INTO department(name,is_active) values('ACCOUNTANT','Y');
INSERT INTO department(name,is_active) values('FINANCE STATISTICS','Y');
INSERT INTO department(name,is_active) values('AGRICULTURE','Y');
INSERT INTO department(name,is_active) values('ENGINEERING','Y');
INSERT INTO department(name,is_active) values('CHEMICAL ENGINEERING','Y');
INSERT INTO department(name,is_active) values('ELECTRICAL ENGINEERING','Y');
INSERT INTO department(name,is_active) values('MATERIAL ENGINEERING','Y');
INSERT INTO department(name,is_active) values('TRANSPORATION','Y');
INSERT INTO department(name,is_active) values('OVERSEAS','Y');
INSERT INTO department(name,is_active) values('NURSES','Y');
INSERT INTO department(name,is_active) values('BANKING','Y');
INSERT INTO department(name,is_active) values('MECHANICAL ENGINEER','Y');
INSERT INTO department(name,is_active) values('ALL DEPARTMENTS','Y');
INSERT INTO department(name,is_active) values('AGRICULTURE AND COOPERATION DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('ANIMAL HUSBANDRY, DAIRY DEVELOPMENT &FISHERIES DEPT','Y');
INSERT INTO department(name,is_active) values('CONSUMER AFFAIRS, FOOD &CIVIL SUPPLIES DEPT.','Y');
INSERT INTO department(name,is_active) values('HIGHER EDUCATION DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('SCHOOL EDUCATION DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('ENERGY DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('ENVIRONMENT, FORESTS, SCIENCE AND TECHNOLOGY DEPT.','Y');
INSERT INTO department(name,is_active) values('FINANCE (P.M.U) DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('FINANCE (WORKS &PROJECTS) DEPT.','Y');
INSERT INTO department(name,is_active) values('GENERAL ADMINISTRATION DEPT','Y');
INSERT INTO department(name,is_active) values('HEALTH, MEDICAL AND FAMILY WELFARE DEPT','Y');
INSERT INTO department(name,is_active) values('HOME DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('HOUSING DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('INFRASTRUCTURE AND INVESTMENT DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('INFORMATION TECHNOLOGY AND COMMUNICATIONS DEPT','Y');
INSERT INTO department(name,is_active) values('INDUSTRIES AND COMMERCE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('IRRIGATION AND COMMAND AREA DEVELOPMENT DEPT','Y');
INSERT INTO department(name,is_active) values('IRRIGATION AND C.A.D(PROJECT WING) DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('LABOUR, EMPLOYMENT, TRAINING AND FACTORIES DEPT','Y');
INSERT INTO department(name,is_active) values('LAW DEPARTMENTS','Y');
INSERT INTO department(name,is_active) values('LAW OFFICERS','Y');
INSERT INTO department(name,is_active) values('MUNICIPAL ADMINISTRATION AND URBAN DEVELOPMENT DEPT','Y');
INSERT INTO department(name,is_active) values('PLANNING DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('PUBLIC ENTERPRISES DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('PANCHAYAT RAJ AND RURAL DEVELOPMENT DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('REVENUE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('RAIN SHADOW AREAS DEVELOPMENT DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('TRANSPORT,ROADS AND BUILDINGS DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('UNIVERSITIES','Y');
INSERT INTO department(name,is_active) values('WELFARE DEPARTMENTS','Y');
INSERT INTO department(name,is_active) values('MINORITIES WELFARE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('SOCIAL WELFARE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('TRIBAL WELFARE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('DEPARTMENT FOR WOMEN,CHILDREN,DISABLED&SENIOR CITIZENS','Y');
INSERT INTO department(name,is_active) values('YOUTH ADVANCEMENT,TOURISM AND CULTURE DEPARTMENT','Y');
INSERT INTO department(name,is_active) values('BANKING','Y');
INSERT INTO department(name,is_active) values('NURSES','Y');
INSERT INTO department(name,is_active) values('10TH','Y');
INSERT INTO department(name,is_active) values('INTERMEDIATE','Y');
INSERT INTO department(name,is_active) values('UNDER GRADUATE','Y');
INSERT INTO department(name,is_active) values('POST GRADUATE','Y');
INSERT INTO department(name,is_active) values('PHD','Y');
INSERT INTO department(name,is_active) values('MEDICAL','Y');
INSERT INTO department(name,is_active) values('ACCOUNTANTS','Y');
INSERT INTO department(name,is_active) values('ACTUARIES','Y');
INSERT INTO department(name,is_active) values('ADVOCATES','Y');
INSERT INTO department(name,is_active) values('ARCHITECTS','Y');
INSERT INTO department(name,is_active) values('BUSINESS','Y');
INSERT INTO department(name,is_active) values('ECONOMISTS','Y');
INSERT INTO department(name,is_active) values('ENGINEERS','Y');
INSERT INTO department(name,is_active) values('LAWYERS','Y');
INSERT INTO department(name,is_active) values('PHYSICIANS','Y');
INSERT INTO department(name,is_active) values('SCIENTISTS','Y');
INSERT INTO department(name,is_active) values('SELF EMPLOYED','Y');
INSERT INTO department(name,is_active) values('SOCIAL WORKERS','Y');
INSERT INTO department(name,is_active) values('STATISTICIANS','Y');
INSERT INTO department(name,is_active) values('TEACHERS','Y');
INSERT INTO department(name,is_active) values('MEDICAL REPRESENTATIVES','Y');
INSERT INTO department(name,is_active) values('LIC AGENTS','Y');
INSERT INTO department(name,is_active) values('OTHERS','Y');




/**2019-03-23*/
alter table apsf.user add otp varchar(4);

/**2019-03-24  -- some db changes done but not updated**/
--------------------------

--------------------------
/**2019-03-26 **/

update apsf.district set code ='KUR' where id= 13;
update apsf.district set code ='ATP' where id= 12;
update apsf.district set code ='YSR' where id= 11;
update apsf.district set code ='CTR' where id= 10;
update apsf.district set code ='NEL' where id= 9;
update apsf.district set code ='PRK' where id= 8;
update apsf.district set code ='GNT' where id= 7;
update apsf.district set code ='KRI' where id= 6;
update apsf.district set code ='WGV' where id= 5;
update apsf.district set code ='EGV' where id= 4;
update apsf.district set code ='VSK' where id= 3;
update apsf.district set code ='VZM' where id= 2;
update apsf.district set code ='SRK' where id= 1;

INSERT INTO `apsf`.`qualification` (`name`, `is_active`) VALUES ('DIPLOMA', 'Y');
INSERT INTO `apsf`.`qualification` (`name`, `is_active`) VALUES ('ILLITERATE', 'Y');        
INSERT INTO `apsf`.`qualification` (`name`, `is_active`) VALUES ('OTHER', 'Y');

INSERT INTO `apsf`.`profession` (`name`, `is_active`) VALUES ('HOUSE WIFE', 'Y');
INSERT INTO `apsf`.`profession` (`name`, `is_active`) VALUES ('UN EMPLOYEE', 'Y');
INSERT INTO `apsf`.`profession` (`name`, `is_active`) VALUES ('OTHER', 'Y');

DELETE FROM `apsf`.`department` WHERE `id`='52';
DELETE FROM `apsf`.`department` WHERE `id`='53';
DELETE FROM `apsf`.`department` WHERE `id`='54';
DELETE FROM `apsf`.`department` WHERE `id`='55';
DELETE FROM `apsf`.`department` WHERE `id`='56';
DELETE FROM `apsf`.`department` WHERE `id`='57';
DELETE FROM `apsf`.`department` WHERE `id`='58';

INSERT INTO `apsf`.`department` (`name`) VALUES ('DWAKRA SHG');
