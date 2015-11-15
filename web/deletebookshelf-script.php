<?php
include_once 'db_conn.php';
session_start();
$isbn=$_POST['isbn'];
$user_id=$_POST['userid'];

$query="DELETE FROM book_shelf WHERE user_id = '$user_id' AND isbn = '$isbn'" ;
$result=pg_query($query);
if($result)
echo 1;
else
echo 0;
?>
