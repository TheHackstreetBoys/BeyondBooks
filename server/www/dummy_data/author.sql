-- Authors of the book
--create table author(
--       isbn varchar(13),
--       author varchar,
--       primary key (isbn,author),
--       foreign key (isbn) references books(isbn)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------

-- Authors ('isbn_no','author_name')


INSERT INTO author VALUES ('9780321588760','George B. Thomas, Jr');
INSERT INTO author VALUES ('9780073383095','Kenneth H.Rosen');
INSERT INTO author VALUES ('9781118431115','Cay Horstmann');
INSERT INTO author VALUES ('9780136123569','Paul Deitel,Harvey Deitel');
INSERT INTO author VALUES ('9780470521571','Richard C Dorf');
INSERT INTO author VALUES ('9780470458365','Erwin Kreyszig');

