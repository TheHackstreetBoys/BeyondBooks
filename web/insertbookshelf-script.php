<?php
include_once 'db_conn.php';
session_start();
$isbn=$_POST['isbn'];
$user_id=$_POST['userid'];

$query="INSERT into book_shelf(user_id,isbn) VALUES ('$user_id','$isbn')";
$result=pg_query($query);
if($result)
echo 1;
else
echo 0;
?>
