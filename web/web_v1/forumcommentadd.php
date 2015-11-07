<?php
include 'mysql.php';
session_start();
//$username = $_SESSION['current'];
$username = "Kushal";



  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);
    $_POST['name']    = "Kj";
    $_POST['email']   = "k@gmail.com";
    $_POST['website'] = "student";

mysql_safe_query('INSERT INTO comments (post_id, rollno, name, email,website,content,date) VALUES (%s,%s,%s,%s,%s,%s,%s)', 
	$_GET['id'], $username, $_POST['name'], $_POST['email'], $_POST['website'], $_POST['content'], time());
mysql_safe_query('UPDATE posts SET num_comments=num_comments+1 WHERE id=%s LIMIT 1', $_GET['id']);
redirect('forumview.php?id='.$_GET['id'].'#post-'.mysql_insert_id());
