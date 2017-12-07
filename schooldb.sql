
create TABLE DEPARTMENT (
DEPT_ID  VARCHAR2(30)
                        CONSTRAINT dept_id_pk PRIMARY KEY,
DEPT_NAME VARCHAR2(50)
                        CONSTRAINT dept_name_unique UNIQUE
                        CONSTRAINT dept_name_not_null NOT NULL,
HEAD_ID VARCHAR2(2)
)
/
CREATE TABLE EXAM (
EXAM_CODE VARCHAR2(30)
                        CONSTRAINT exam_code_pk PRIMARY KEY,
EXAM_TYPE_ID VARCHAR2(30),
START_DATE DATE
)
/
CREATE TABLE EXAM_TYPE (
EXAM_TYPE_ID VARCHAR2(30)
                        CONSTRAINT exam_code_pk PRIMARY KEY,
EXAM_TYPE_NAME VARCHAR2(30)
                        CONSTRAINT exam_type_name_not_null NOT NULL
                        CONSTRAINT exam_type_name_unique UNIQUE,
START_DATE DATE
)
/
CREATE TABLE MARKS (
MARK_CODE VARCHAR2(30)
                        CONSTRAINT mark_code_pk PRIMARY KEY,
EXAM_CODE VARCHAR2(30) 
                        CONSTRAINT exam_code_fk FOREIGN KEY(EXAM_CODE) REFERENCES EXAM,
SCORE INTEGER(3,2) ,
STUDENT_ID VARCHAR2(30),
SUBJECT_ID VARCHAR2(30),
TEACHER_ID VARCHAR2(30)
)
/
CREATE TABLE SUBJECT (
SUBJECT_ID VARCHAR2(30)
                        CONSTRAINT subject_id_pk PRIMARY_KEY,
subject_name VARCHAR2(60)
                         CONSTRAINT subject_name_unique UNIQUE,
subject_description VARCHAR2(100)


)
/
CREATE TABLE CLASS (
CLASS_ID VARCHAR2(30) CONSTRAINT class_id_pk PRIMARY KEY,
CLASS_NAME VARCHAR(30) CONSTRAINT class_name_not_null NOT NULL
                       CONSTRAINT class_name_unique UNIQUE,
CAPACITY INTEGER(2)
)
/

CREATE TABLE TERM (
TERM_CODE VARCHAR2(30) CONSTRAINT term_id_pk PRIMARY KEY,
TERM_NAME VARCHAR2(30) CONSTRAINT term_name_not_null NOT NULL
                       CONSTRAINT term_name_unique UNIQUE,
TERM_FEE INTEGER(5,2),
TERM_DURATION INTEGER(2),
TERM_START_DATE DATE
)
/
CREATE TABLE FEE 
(
FEE_ID VARCHAR2(30) CONSTRAINT fee_id_pk PRIMARY_KEY,
TERM_CODE VARCHAR(30) CONSTRAINT term_code_fk FOREIGN KEY(TERM_CODE) REFERENCES TERM,
DATE_OF_PAYMENT DATE CONSTRAINT date_of_payment_not_null NOT NULL,
AMOUNT INTEGER(5,2) ,
STUDENT_ID VARCHAR(30),
MODE_ID VARCHAR(30)
)
/
CREATE TABLE MODE
(
MODE_ID VARCHAR2(30) CONSTRAINT mode_id_pk PRIMARY KEY,
MODE_NAME VARCHAR(30) CONSTRAINT mode_name_not_null NOT NULL
CONSTRAINT mode_name_unique UNIQUE,

)
/
CREATE TABLE STUDENT 
( stud_id VARCHAR2(30) CONSTRAINT stud_id_pk PRIMARY KEY,
  surname VARCHAR2(60) CONSTRAINT stud_surname_not_null NOT NULL,
  first_name VARCHAR2(60) CONSTRAINT stud_first_name_not_null NOT NULL,
  last_name VARCHAR2(60) CONSTRAINT stud_last_name_not_null NOT NULL,
  student_class VARCHAR2(20) CONSTRAINT stud_class_not_null NOT NULL,
  stud_year INTEGER CONSTRAINT year_not_null NOT NULL,
  dob DATE,
  date_of_admission DATE,
  status VARCHAR2(20),
  parent_id VARCHAR2(30),
  mobile_no VARCHAR2(30),
  gender VARCHAR(20) CONSTRAINT stud_gender_not_null NOT NULL,
  box VARCHAR2(30),
  postal_code VARCHAR2(10),
  town VARCHAR2(60)
)
/
CREATE TABLE staff
(ts_no VARCHAR2(30) CONSTRAINT ts_no_unique UNIQUE,
ID_NO VARCHAR2(30) CONSTRAINT staff_id_no_pk PRIMARY KEY,
surname VARCHAR2(60) CONSTRAINT teacher_surname_not_null NOT NULL,
first_name VARCHAR2(60) CONSTRAINT teacher_first_name_not_null NOT NULL,
last_name VARCHAR2(60) CONSTRAINT teacher_last_name_not_null NOT NULL,
dob DATE,
date_of_admission DATE,
email VARCHAR2(70),
box VARCHAR2(30),
postal_code VARCHAR2(10),
town VARCHAR2(60),
subject_code VARCHAR2(20)(DEFINED AS AN ARRAY TYPE),
gender VARCHAR(20) CONSTRAINT stud_gender_not_null NOT NULL
)
/
CREATE TABLE parent
(ID_NO VARCHAR2(30) CONSTRAINT parent_id_no_pk PRIMARY KEY,
surname VARCHAR2(60) CONSTRAINT parent_surname_not_null NOT NULL,
first_name VARCHAR2(60) CONSTRAINT parent_first_name_not_null NOT NULL,
last_name VARCHAR2(60) CONSTRAINT parent_last_name_not_null NOT NULL,
dob DATE,
email VARCHAR2(70),
box VARCHAR2(30),
postal_code VARCHAR2(10),
town VARCHAR2(60),
mobile_no VARCHAR2(30),
gender VARCHAR(20) CONSTRAINT stud_gender_not_null NOT NULL,
)
/