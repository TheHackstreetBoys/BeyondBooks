----------------------------------------------------------------------------------------------------------------------------------
-- For the User profile
-- create table user_profile (  
--	f_name varchar(300),
--	l_name varchar(300),
--	user_id varchar(30),
--	password varchar(900),
--	email_id varchar(50),
--	image_location varchar(50),
--	isFaculty boolean,(y-yes/n-no)
--	phone_number varchar(10),
--	last_loggedin timestamp,
--	primary key (user_id)
--);
----------------------------------------------------------------------------------------------------------------------------------
set search_path to coursera;
----------------------------------------------------------------------------------------------------------------------------------

--faculty
INSERT INTO user_profile VALUES ('asim','banerjee','f1','password','asim_banerjee@daiict.ac.in','','y','079-30510554','2015-11-06 04:05:06');
INSERT INTO user_profile VALUES ('nandini','banerjee','f2','password','nandini_banerjee@daiict.ac.in','','y','','2015-11-06 04:05:06');


--students
INSERT INTO user_profile VALUES ('dilip','puri','s1','password','201351014@iiitvadodara.ac.in','','n','9408822223','2015-11-06 04:05:06');
INSERT INTO user_profile VALUES ('chirag','panpalia','s2','password','201351001@iiitvadodara.ac.in','','n','9408822223','2015-11-06 04:05:06');
INSERT INTO user_profile VALUES ('akhilesh','kumar','s3','password','201351009@iiitvadodara.ac.in','','n','9408822223','2015-11-06 04:05:06');
INSERT INTO user_profile VALUES ('sonu','patidar','s4','password','201351016@iiitvadodara.ac.in','','n','9408822223','2015-11-06 04:05:06');
