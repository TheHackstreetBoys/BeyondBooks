<?php
include_once "db_conn.php";
session_start();
//$username = $_SESSION['current'];

$uid= "201351006" ;
$qid = $_GET['qid'];
$content = $_POST['content'];

 $query = pg_query("INSERT INTO forum_replies(ts, qid , uid, reply) VALUES (CURRENT_TIMESTAMP, '$qid' , '$uid' , '$content')" );
 if($query)
{
  
  header("Location: forumview.php?qid= $qid ");
}

else
{
  echo "Error".pg_last_error();
}





?>
