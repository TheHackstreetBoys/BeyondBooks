<?php
session_start();
include_once "db_conn.php";

$isbn =  $_POST['isbn'];
$sellerid = $_POST['sellerid']."@iiitvadodara.ac.in";
$user_id = $_POST['user_id'];





		$to      = $sellerid;
		$subject = "Feedback Message";
		$subject =  $subject." ".$user_id;
		$message =  $_POST['message']." ".$isbn." ".$_POST['contactno'];



		$headers = "From:".$user_id."@iiitvadodara.ac.in";

if (mail($to, $subject, $message, $headers)) {
		echo "Thanks for submitting.";
ini_set('display_errors',1);
 header( "refresh:2;url=homepage.php" );
  } else {
   echo("<p>Email delivery failed</p>");
   header( "refresh:2;url=homepage.php" );
  }


?>
