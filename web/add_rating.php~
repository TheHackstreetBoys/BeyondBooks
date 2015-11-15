<?php
session_start();
include_once 'db_conn.php';


	$rating	= $_GET['rated'];
	$uid  = $_SESSION["user_id"];
	$isbn = $_GET['isbn'];



	$query	= "INSERT INTO rating(uid, rating, isbn) VALUES('$uid','$rating','$isbn') ";
	pg_query($query);

?>
