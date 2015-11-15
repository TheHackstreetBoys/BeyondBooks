<?php
session_start();
include_once "db_conn.php";
$course = $_POST['course'];
$body   = $_POST['body'];
$title  = $_POST['title'];
$date   = time();
$tname  = $_SESSION["user_id"];




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
