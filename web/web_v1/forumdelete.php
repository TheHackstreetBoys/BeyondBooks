<?php
include_once "db_conn.php";

//session_start();
  //  $username   = $_SESSION['current'];
    //$mycourse = $_SESSION['mycourse'];

$qid = $_GET['qid'];

pg_query("DELETE FROM qtags WHERE qid = '$qid'" );
pg_query("DELETE FROM forum_replies WHERE qid= '$qid'" );
pg_query("DELETE FROM question_forum WHERE qid= '$qid' " );



header("Location: forumWelcome.php");

?>
