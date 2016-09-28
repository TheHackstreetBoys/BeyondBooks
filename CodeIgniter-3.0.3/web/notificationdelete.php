<?php
include_once "db_conn.php";

session_start();
  $whom   = $_SESSION['user_id'];
    //$mycourse = $_SESSION['mycourse'];

$bidder =  $_GET['bidder'];
$prodid = $_GET['prodid'];
$des   = $_GET['des'];

pg_query("DELETE FROM bid WHERE bidder = '$bidder' AND prodid = '$prodid'" );
pg_query("DELETE FROM notify WHERE link= '$prodid' AND whom = '$whom' AND des = '$des'" );


header("Location: homepage.php"); /* Redirect browser */
?>
