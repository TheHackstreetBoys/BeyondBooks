--- Author: krupo

-- For the User profile
create table user_profile (
       f_name varchar(300),
       l_name varchar(300),
       user_id varchar(30),
       password varchar(900),

       email_id varchar(50),
       image_location varchar(50),
       isFaculty boolean,
       phone_number varchar(10),
       last_loggedin timestamp,
       primary key (user_id)
);


-- The questions in the forum
create table question_forum(
       qid serial,
       ts timestamp,
       asker varchar,
       title text,
       content text,
       upvote int,
       downvote int,
       primary key(qid),
       foreign key (asker) references user_profile(user_id)
);

--For the overall tags
create table qtags(
       qid int,
       htag varchar,
       primary key (qid,htag),
       foreign key (qid) references question_forum(qid)

);

--For the faculty tags
create table faculty_tags(
       uid varchar,
       qid int,
       primary key (uid,qid),
       foreign key (uid) references user_profile(user_id),

       foreign key (qid) references question_forum(qid)
);

-- replies of the forum

create table forum_replies(
       qid int,
       ts timestamp,
       uid varchar,
       reply text,
       upvote int,
       downvote int,
       primary key (qid,ts,uid),

       foreign key (qid) references question_forum(qid),
       foreign key (uid) references user_profile(user_id)
);

-- The main books database
create table books(
       isbn varchar(13),
       publisher varchar,
       description text,

       ts timestamp,
       title varchar,
       image_link varchar,
       primary key (isbn)
);

-- Authors of the book
create table author(
       isbn varchar(13),
       author varchar,
       primary key (isbn,author),
       foreign key (isbn) references books(isbn)
);

-- Rating For the book
create table rating(
       uid varchar,
       rating decimal,
       isbn varchar(13),
       primary key(uid, isbn),
       foreign key (uid) references user_profile(user_id),
       foreign key (isbn) references books(isbn)
);

-- Review For the book
create table review(
       uid varchar,
       review text,
       isbn varchar(13),
       primary key(uid, isbn),
       foreign key (uid) references user_profile(user_id),
       foreign key (isbn) references books(isbn)
);


-- For the products base table
create table pbase(
       prodid serial,
       ts timestamp,
       description text,
       price decimal,
       sellerid varchar,
       primary key (prodid),
       foreign key (sellerid) references user_profile(user_id)

);

-- Bid table
create table bid(
       prodid int,
       ts timestamp,
       bidder varchar,
       primary key (prodid,bidder),
       foreign key (bidder) references user_profile(user_id),
       foreign key (prodid) references pbase(prodid)
);

-- Comments over the product
create table comments(
       prodid int,
       ts timestamp,
       commenter varchar,
       comment text,
       primary key (prodid,commenter),
       foreign key (commenter) references user_profile(user_id),
       foreign key (prodid) references pbase(prodid)
);

-- Single product table
create table single_sell(
       prodid int,
       isbn varchar(13),
       age int,
       price decimal,
       primary key (prodid,isbn),
       foreign key (prodid) references pbase(prodid)
);

-- Combo product table
create table combo_sell(
       prodid int,
       isbn varchar(13),
       age int,
       quantity int,
       price decimal,
       primary key (prodid,isbn),
       foreign key (prodid) references pbase(prodid)
);


-- For the notification
create table notify(
       whom varchar,
       des varchar,
       link varchar,
       primary key (whom,des),
       foreign key (whom) references user_profile(user_id)
);
