-- Combo product table
--create table combo_sell(
--       prodid int,
--       isbn varchar(13),
--       age int,
--       quantity int,
--       primary key (prodid,isbn),
--       foreign key (prodid) references pbase(prodid)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------

--  combo_sell('prodid','isbn','age','quantity') 

INSERT INTO combo_sell VALUES ('23456','9780073383095','2','4');
INSERT INTO combo_sell VALUES ('34567','9780136123569','1','3');
INSERT INTO combo_sell VALUES ('45678','9780470458365','3','2');
INSERT INTO combo_sell VALUES ('56789','9781118431115','1','4');
INSERT INTO combo_sell VALUES ('67890','9780132936521','2','1');
INSERT INTO combo_sell VALUES ('12345','9781118063330','4','1');


