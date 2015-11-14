<?php
		
session_start();					
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

	$rated		=	$_GET['rated']+'.0';
	$pollid		=	$_GET['pid'];
	$uid		=       "201351014";
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
