<?php
include_once "db_conn.php";
session_start();
//$username = $_SESSION['current'];
$uid=$_SESSION["user_id"];
$qid = $_GET['qid'];
$content = $_POST['content'];

 $query = pg_query("INSERT INTO forum_replies(ts, qid , uid, reply) VALUES (CURRENT_TIMESTAMP, '$qid' , '$uid' , '$content')" );
 if($query)
{

  header("Location: forumview.php?qid= $qid ");
}

else
{
  echo "Error! Your comment could not be added";
  header("refresh:2;Location: forumview.php?qid= $qid ");
}





?>
