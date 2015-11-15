<?php
include_once 'db_conn.php';
session_start();


	$rated		=	$_GET['rated']+'.0';
	$pollid		=	$_GET['pid'];
	$uid		=   $_SESSION["user_id"];
	$isbn		=	$_SESSION['isbn'];

$query1 = pg_query("INSERT INTO rating(uid, rating, isbn) VALUES('$uid','$rated','$isbn')");

if($query1)
{
echo "Ok";
}
else
{
echo "hi".pg_last_error();
}

?>
