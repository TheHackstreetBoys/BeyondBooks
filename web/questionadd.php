<?php
session_start();

include_once "db_conn.php";
$title = $_POST['title'];
$htag   = $_POST['htag'];
$content  = $_POST['content'];
$asker  = '201351006';




$query = pg_query("INSERT INTO question_forum (ts, asker, title, content) VALUES (CURRENT_TIMESTAMP, '$asker', '$title', '$content'); select lastval();");
$qid = pg_fetch_array($query)[0];
if($query)
{
  echo 'Post is Successfully Created.';
  header('Refresh:1; url=forumWelcome.php');
}
else
{
  echo "Error adding your question ".pg_last_error();
}


$query = pg_query("INSERT INTO qtags (qid, htag) VALUES ('$qid', '$htag')");

?>
