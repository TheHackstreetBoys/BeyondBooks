<?php
include_once 'db_conn.php';
session_start();
$isbn=$_POST['isbn'];
$user_id=$_POST['userid'];

$query="SELECT * from book_shelf where isbn='$isbn' and user_id='$user_id')";
$result=pg_query($query);
$num=pg_num_rows($result);
if($num!=0)
echo 1;
else
echo 0;
?>
