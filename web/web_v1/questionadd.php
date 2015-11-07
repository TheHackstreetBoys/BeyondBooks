<?php
if(!empty($_POST)) {
	include 'mysql.php';

    session_start();
    $username   = "Kushal";
   // $mycourse   = $_SESSION['mycourse']; 
    
 
    
	if(mysql_safe_query('INSERT INTO posts (course, tname, title,body,date) VALUES (%s,%s,%s,%s,%s)',$_POST['course'], $username, $_POST['title'], $_POST['body'], time()))
		echo 'Post is Successfully Created. Click here to <a href="http://localhost/myforum/forumview.php?id='.mysql_insert_id().'+'.$mycourse.'">View</a>';
	else
		echo mysql_error();
}
?>

