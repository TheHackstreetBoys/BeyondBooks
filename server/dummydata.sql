INSERT INTO user_profile VALUES ('Aditya','Prakash','201351010','5f4dcc3b5aa765d61d8327deb882cf99','201351010@iiitvadodara.ac.in','blah_blah',FALSE,'9810435438','2038-01-19 03:14:07');
INSERT INTO user_profile VALUES ('Pratik','Shaaaaaaaaah','20135','5f4dcc3b5aa765d61d8327deb882cf99','20135@pratikshah.ac.in','bluh_bluh',TRUE,'8128229077','2000-11-11 03:14:19');
INSERT INTO user_profile VALUES ('Choubey','Ji','201351006','lelelele','201351006@iiitvadodara.ac.in','5f4dcc3b5aa765d61d8327deb882cf99',FALSE,'8140234164','2118-11-15 05:16:11');



INSERT INTO question_forum VALUES (1,'2038-01-09 03:14:07','201351010','Software Engineering','Clauses of ISO 9000');
INSERT INTO question_forum VALUES (2,'2018-01-19 03:34:07','20135','Numerical Linear Algebra','All methods like Jacobi, Gauss');
INSERT INTO question_forum VALUES (3,'2028-01-29 03:14:07','20135','Discrete Mathemetics','Graph Algorithms');
INSERT INTO question_forum VALUES (4,'2008-01-19 03:24:07','201351006','Mess Committee','Your suggestion required on Mess Menu');


INSERT INTO qtags VALUES (1,'sen');
INSERT INTO qtags VALUES (2,'nla');
INSERT INTO qtags VALUES (3,'dm');
INSERT INTO qtags VALUES (4,'mess');


INSERT INTO pbase VALUES (100,'2001-10-11 04:14:19','201351010');
INSERT INTO pbase VALUES (101,'2002-09-11 05:14:19','201351010');
INSERT INTO pbase VALUES (102,'2003-08-11 06:14:19','20135');


INSERT INTO notify VALUES ('201351010','Want to buy this book',100);
INSERT INTO notify VALUES ('20135','Want to buy this book',101);
INSERT INTO notify VALUES ('201351006','Want to buy this book',101);


INSERT INTO forum_replies VALUES (1,'1995-08-11 06:14:19','201351010','I want to know clauses.');
INSERT INTO forum_replies VALUES (1,'1996-08-11 06:14:19','201351006','There are 8 priciples in quality management.');
INSERT INTO forum_replies VALUES (2,'1997-08-11 06:14:19','201351006','I know Jacobi');
INSERT INTO forum_replies VALUES (2,'1997-08-11 06:14:19','201351010','Gauss seidel is better than Jacobi');
INSERT INTO forum_replies VALUES (3,'1997-08-11 06:14:19','201351006','Prims algorithm');
INSERT INTO forum_replies VALUES (3,'1997-08-11 06:14:19','201351010','DFS and BFS are search algorithma');
INSERT INTO forum_replies VALUES (3,'1997-08-11 06:14:19','201351006','Rasgullah everday is possible');
INSERT INTO forum_replies VALUES (3,'1997-08-11 06:14:19','201351010','Ok Mess Menu');



INSERT INTO faculty_tags VALUES ('20135',1);
INSERT INTO faculty_tags VALUES ('20135',3);


INSERT INTO comments VALUES (100,'2014-08-11 06:14:19','201351010','This book is nice');
INSERT INTO comments VALUES (101,'2015-08-11 06:14:19','201351006','you can use this book');
INSERT INTO comments VALUES (102,'2016-08-11 06:14:19','20135','nice book for better understanding');
INSERT INTO comments VALUES (101,'2017-08-11 06:14:19','20135','good book want to buy');


INSERT INTO books VALUES ('1000','PHI learning','Book for Automata','2017-08-11 06:14:19','Theory of Computation','201351010');
INSERT INTO books VALUES ('1001','PHI learning','Nice book for electronics','2017-08-11 06:14:19','Basic Electronic Circuits','20135');
INSERT INTO books VALUES ('1002','PHI learning','Nice book for ADT','2017-08-11 06:14:19','Data Structures','20135');


INSERT INTO bid VALUES (100,'1999-08-09 06:14:19','201351010');
INSERT INTO bid VALUES (100,'1998-08-10 06:14:19','20135');
INSERT INTO bid VALUES (101,'1997-08-12 06:14:19','201351010');


INSERT INTO author VALUES ('1000','Klp Mishra');
INSERT INTO author VALUES ('1001','Marconi');
INSERT INTO author VALUES ('1002','Mark Allen');

INSERT INTO book_shelf VALUES ('201351010','1000');
INSERT INTO book_shelf VALUES ('201351010','1001');
INSERT INTO book_shelf VALUES ('20135','1000');

INSERT INTO single_sell VALUES (100,'1000',21,100000,'Nice book go through it.');
INSERT INTO single_sell VALUES (101,'1001',22,2000,'Nice book please read it');
INSERT INTO single_sell VALUES (102,'1002',23,100,'good book for hard concepts');


INSERT INTO review VALUES ('201351010','I liked book','1000');
INSERT INTO review VALUES ('20135','good book','1000');
INSERT INTO review VALUES ('201351010','excellent book for understanding','1001');
INSERT INTO review VALUES ('20135','book is ok','1002');
INSERT INTO review VALUES ('201351006','Some part of the book is very good','1002');


INSERT INTO rating VALUES ('201351010',5,'1000');
INSERT INTO rating VALUES ('20135',4,'1000');
INSERT INTO rating VALUES ('201351010',3,'1001');
INSERT INTO rating VALUES ('20135',2,'1002');
INSERT INTO rating VALUES ('201351006',2,'1002');

