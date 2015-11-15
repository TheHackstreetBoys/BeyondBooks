<?php
$author_id = $_POST['author_id'];
$title = $_POST['title'];
$author_name = $_POST['author_name'];
$question_id = $_POST['question_id'];
$details = $_POST['details'];
$times = date('d-m-Y H:i:s');

$query = "insert into question_forum (ts,asker, title, content, upvote, downvote ) values ($times,'$author_id','$title','$details',0,0);";
$result = pg_query($query);

echo json_encode(array('result' => "true" ));
?>
