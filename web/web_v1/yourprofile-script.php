<?php
include_once 'db_conn.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
	session_start();
	$mail=$_SESSION['inputEmail3'];
	$fname=$_POST['fname'];
	$lname=$_POST['lname'];
	$pwd=$_POST['newpwd'];
	

	$result=pg_query("SELECT * FROM user_profile where email_id='$mail'");
    $row=pg_fetch_array($result);

    if($fname=="")
    	$fname=$row['fname'];
    if($lname=="")
    	$lname=$row['lname'];
    if($fname=="")
    	$fname=$row['fname'];
    
    if($pwd=="")
    {
    	$password=$row['password'];
    }
    else
    {
    	$password=md5($password);
    }

	$sql = "UPDATE user_profile SET f_name='$fname', l_name='$lname', password='$password' WHERE email_id='$mail'";
	pg_query($sql);
}
pg_close();
header('Location: homepage.php');
?>