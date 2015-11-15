<?php
include_once 'db_conn.php';
if($_SERVER['REQUEST_METHOD']=='POST')
{
	$mail=$_POST['email'];
	$mail=pg_real_escape_string($mail);
	$mail=strip_tags($mail);

	$seed = str_split('abcdefghijklmnopqrstuvwxyz'
	                 .'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
	                 .'0123456789'); // and any other characters
	shuffle($seed); // probably optional since array_is randomized; this may be redundant
	$rand = '';
	foreach (array_rand($seed, 7) as $k) $rand .= $seed[$k];
$password=md5($rand);

	$query="UPDATE user_profile SET passsword='$password' where email = '$mail'";
	$result=pg_query($query);
	$num=pg_num_rows($result);
	$row=pg_fetch_array($result);
	if($num == 0)
	{
		echo 0;
	}
	else
	{
		$subject="New Password Request";
		$msg="Following are the details:\r\n Email: ".$mail."\r\n New Password: ".$rand."\r\nYou are advised to change the password in the My Profile section after logging in";
		mail($mail,$subject,$msg);
		echo 1;
	}
}
?>

}
