-- Review For the book
--create table review(
--       uid varchar,
--       review text,
--       isbn varchar(13),
--       primary key(uid, isbn),
--       foreign key (uid) references user_profile(user_id),
--       foreign key (isbn) references books(isbn)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------


--          ('uid','review','isbn')

INSERT INTO review VALUES ('u1','good','9780073383095');
INSERT INTO review VALUES ('u2','better','9780136123569');
INSERT INTO review VALUES ('u5','best','9780470458365');
INSERT INTO review VALUES ('u7','best','9781118431115');
INSERT INTO review VALUES ('u4','good','9780132936521');
INSERT INTO review VALUES ('u9','better','9781118063330');
