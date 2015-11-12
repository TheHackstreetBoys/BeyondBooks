
-- For the notification
--create table notify(
--       whom varchar,
--       des varchar,
--       link varchar,
--       primary key (whom,des),
--       foreign key (whom) references user_profile(user_id)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------

--   notify('whom','des')

INSERT INTO notify VALUES ('u1','Please contact me');
INSERT INTO notify VALUES ('u2','Please send me an email');
INSERT INTO notify VALUES ('u9','Book version updated');
INSERT INTO notify VALUES ('u4','Book is available');
INSERT INTO notify VALUES ('u3','Combo offer on K.Rosen');
INSERT INTO notify VALUES ('u7','Let us C on top rating');
