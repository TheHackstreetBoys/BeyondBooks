<?php
include_once 'db_conn.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
$email=$_POST['email'];

$password=$_POST['password'];
$pwd=md5($password);

$query="SELECT * from user_profile where email_id = '$email'";
	$result=pg_query($query);
	$num=pg_num_rows($result);
	$row=pg_fetch_array($result);
	if($num == 0)
	{
		echo 3;
	}
	else
	{
		if ($pwd == $row['password'])
		{
			echo 1;
		}
		else
		{
			echo 0;
		}
	}
}

?>
