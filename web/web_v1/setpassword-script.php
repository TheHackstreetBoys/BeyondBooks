<?php
include_once 'db_conn.php';

$mail=$_POST['email'];
$password=$_POST['password'];

$pwd=$_POST['password'];
	$pwd=md5($password);

$sql=pg_query("UPDATE user_profile SET password='$pwd' WHERE email='$mail'");
pg_close();
echo 1;
?>