<?php
session_start();

include_once "db_conn.php";
$title = pg_escape_string($_POST['title']);
$htag   = pg_escape_string($_POST['htag']);
$content  = pg_escape_string($_POST['content']);
$asker  = $_SESSION["user_id"];




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
  header('Refresh:1; url=forumWelcome.php');
}


$query = pg_query("INSERT INTO qtags (qid, htag) VALUES ('$qid', '$htag')");

?>
