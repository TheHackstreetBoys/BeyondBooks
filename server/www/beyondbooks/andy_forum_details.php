<?php
// needed title author_name author_id id details
require 'db_conn.php';
$qid = $_POST['q_id'];
$qid = "1";
$query = "select * from (question_forum join user_profile on (question_forum.asker = user_profile.user_id)) as lund where qid='$qid';";
$result = pg_query($query);

$fin_arr = array();
$row = pg_fetch_array($result);
$fin_arr['title'] = $row['title'];
$fin_arr['author_id'] = $row['asker'];
$fin_arr['author_name'] = "$row[f_name] $row[l_name]";
$fin_arr['details'] = $row['content'];

$comm = array();
$query="select * from forum_replies where qid='$qid';";

$result = pg_query($query);
while ($row = pg_fetch_array($result))
{
    array_push($comm, array('text'=>$row['reply'], 'user_id'=>$row['uid'], 'comment_id'=>$row['ts']));
}
$fin_arr['comments']=$comm;

echo json_encode($fin_arr);
?>
