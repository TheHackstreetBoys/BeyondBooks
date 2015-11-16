<?php
require 'db_conn.php';
$author_id = $_POST['author_id'];
$title = $_POST['title'];
$author_name = $_POST['author_name'];
$details = $_POST['details'];
$tags = $_POST['tags'];

$ftags = $_POST['faculty_tags'];
$logf = fopen('logfile.txt','w');
fwrite($logf,"$author_id $title $author_name $details $tags $ftags");
fclose($logf);
$query = "insert into question_forum (ts,asker, title, content ) values (CURRENT_TIMESTAMP,'$author_id','$title','$details'); select lastval()";

$result = pg_query($query);
$row = pg_fetch_array($result);

$row = $row[0];
foreach (explode(" ",$tags) as $value) {

    pg_insert($dbconn,"qtags", array('qid'=>$row, 'htag'=>$value));
}


echo json_encode(array('result' => "true" ));

?>
