--For the overall tags
----------------------------------------------------------------------------------------------------------------------------------
--create table qtags(
--       qid int,
--       htag varchar,
--       primary key (qid,htag),
--       foreign key (qid) references question_forum(qid)
--
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to database_name;
----------------------------------------------------------------------------------------------------------------------------------


-- qtags('qid','htag')

INSERT INTO qtags VALUES ('q1','#Automata theory');
INSERT INTO qtags VALUES ('q4','#Coding Competition');
INSERT INTO qtags VALUES ('q5','#Combo Offer');
INSERT INTO qtags VALUES ('q2','#Price down');
INSERT INTO qtags VALUES ('q7','#Book Bidding');
INSERT INTO qtags VALUES ('q6','#Top Rating');
