<?php
include_once 'db_conn.php';
session_start();
if($_SERVER['REQUEST_METHOD']=='POST')
{

	$user_id=$_SESSION["user_id"];
	$fname=$_POST['fname'];
	$lname=$_POST['lname'];
	$pwd=$_POST['newpwd'];


	$result=pg_query("SELECT * FROM user_profile where user_id='$user_id'");
    $row=pg_fetch_array($result);


    if($fname=="")
    	$fname=$row['f_name'];
    if($lname=="")
    	$lname=$row['l_name'];


  if($pwd=="")
    {
    	$password=$row['password'];
    }
    else
    {
    	$password=md5($pwd);
    }


	$sql = "UPDATE user_profile SET f_name='$fname', l_name='$lname', password='$password' WHERE user_id='$user_id'";
	pg_query($sql);
}
pg_close();
header('Location: homepage.php');
?>
