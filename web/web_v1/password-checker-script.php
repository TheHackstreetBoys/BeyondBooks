<?php
include_once 'db_conn.php';
session_start();
$userid=$_SESSION['user_id'];
$pwd=$_GET['oldpwd'];
$password=md5($password);
$sql="SELECT * FROM user_profile WHERE user_id='$userid'";
$result=pg_query($sql);
$row=pg_fetch_array($result);
$origpwd=$row['password'];
if(strcmp($password,$origpwd)==0)
{
	echo "1";
}
else
{
	echo "0";
}
pg_close();
?>
