<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


//session_start();
  //  $username   = $_SESSION['current'];
    //$mycourse = $_SESSION['mycourse']; 

    $id = $_GET['id'];
pg_query("DELETE FROM posts WHERE id= '$id' " );
pg_query("DELETE FROM reply WHERE post_id= '$id'" );
header("Location: forumWelcome.php");

?>
