<?php

$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

$course = $_POST['course'];
$body   = $_POST['body'];
$title  = $_POST['title'];
$date   = time();
$tname  = "201351022";


    session_start();
   // $mycourse   = $_SESSION['mycourse']; 
    


 $query = pg_query("INSERT INTO posts (course, tname, title, body, date) VALUES ('$course', '$tname', '$title', '$body', '$date')");
 if($query)
{	
  echo 'Post is Successfully Created. Click here to <a href="forumWelcome.php">View</a>';
}
else
{
  echo "Error".pg_last_error();
}

?>

