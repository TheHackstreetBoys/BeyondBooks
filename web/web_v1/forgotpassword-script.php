<?php 
include_once 'db_conn.php';
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$mail=$_POST['email'];
	$mail=pg_real_escape_string($mail);
	$mail=strip_tags($mail);


	$query="SELECT * from user_profile where email = '$mail'";
	$result=pg_query($query);
	$num=pg_num_rows($result);
	$row=pg_fetch_array($result);
	if($num == 0)
	{
		echo 0;
	}
	else
	{
		$subject="Password Reset";
		$msg="Click on the following link to reset your password \n.. www..com/setpassword.php?email=".$mail;
		mail($mail,$subject,$msg);
		echo 1;
	}			
}
?>

}