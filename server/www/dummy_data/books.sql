-- The main books database
--create table books(
--       isbn varchar(13),
--       publisher varchar,
--       description text,
--       title varchar,
--       primary key (isbn)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------

-- books('isbn','publisher','description','title')

INSERT INTO books VALUES ('9780073383095','The McGraw-Hill Companies','Mathematics','Discrete Mathematics and Its Applications');
INSERT INTO books VALUES ('9780136123569','Deitel Publication','C Programming','C How to Program');
INSERT INTO books VALUES ('9780470458365','John Wiley & Sons, Inc.','Mathematics','Advanced Engineering Mathematics');
INSERT INTO books VALUES ('9781118431115','Wiley Publication','Java Programming','Big Jav Early Objects');
INSERT INTO books VALUES ('9780132936521','Pearson','Java Programming','Introduction to Java Programming');
INSERT INTO books VALUES ('9781118063330','Wiley Publication','Introduction to Operating System','Operating System Concepts');
