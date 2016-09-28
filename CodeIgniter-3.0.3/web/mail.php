<?php
session_start();
include_once "db_conn.php";

$isbn =  $_POST['isbn'];
$sellerid = $_POST['sellerid'];
$user_id = $_POST['user_id'];
$prodid = $_GET['prodid'];

$contactno = $_POST['contactno'];
$message = pg_escape_string($_POST['message'])."Contact-Info:".$contactno;



$query1 = pg_query("INSERT INTO notify(whom, des, link) VALUES ('$sellerid', '$message', '$prodid')");
echo pg_last_error();

$query2 = pg_query("INSERT INTO bid(prodid, ts, bidder, description) VALUES ('$prodid', CURRENT_TIMESTAMP , '$user_id', '$message')");
echo pg_last_error();

header("Location: homepage.php"); /* Redirect browser */




//		$to      = $sellerid;
//		$subject = "Feedback Message";
//		$subject =  $subject." ".$user_id;
//		$message =  $_POST['message']." ".$isbn." ".$_POST['contactno'];



//		$headers = "From:".$user_id."@iiitvadodara.ac.in";

//if (mail($to, $subject, $message, $headers)) {
	//	echo "Thanks for submitting.";
//ini_set('display_errors',1);
 //header( "refresh:2;url=homepage.php" );
  //} else {
   //echo("<p>Email delivery failed</p>");
   //header( "refresh:2;url=homepage.php" );
  //}


?>
