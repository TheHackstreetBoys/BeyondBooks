<?php
include_once "db-conn.php";

//session_start();
  //  $username   = $_SESSION['current'];
    //$mycourse = $_SESSION['mycourse'];

$id = $_GET['id'];
pg_query("DELETE FROM posts WHERE id= '$id' " );
pg_query("DELETE FROM reply WHERE post_id= '$id'" );
header("Location: forumWelcome.php");

?>
