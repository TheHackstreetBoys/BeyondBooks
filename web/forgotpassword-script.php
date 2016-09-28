<?php
include_once 'db_conn.php';


if($_SERVER['REQUEST_METHOD']=='POST')
{
	$mail = $_POST['email'];

	$seed = str_split('abcdefghijklmnopqrstuvwxyz'
	                 .'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
	                 .'0123456789'); // and any other characters
	shuffle($seed); // probably optional since array_is randomized; this may be redundant
	$rand = '';
	foreach (array_rand($seed, 7) as $k) $rand .= $seed[$k];
$password=md5($rand);
	echo $password ;
	$query="UPDATE user_profile SET password='$password' where email_id = '$mail'";
	$result=pg_query($query);

		$to      = $mail;
		$subject = "New Password Request";
		$message = "Following are the details:\r\n Email: ".$to."\r\n New Password: ".$rand."\r\nYou are advised to change the password in the My Profile section after logging in";


		$headers = "From: The hackstreetboys";

if (mail($to, $subject, $message, $headers))
{
echo "A mail has been sent to you.";
 header("refresh:2; url=index.php" );
}
else
{
   echo("<p>Email delivery failed</p>");
   header( "refresh:2; url=homepage.php" );
}



}




	?>
