<?php
require 'db_conn.php';
$username = $_POST['user_id']=201351010;
$comm_arr = array();
$start_arr = array();
// needed title author_id q_id
$query = "select * from question_forum where asker = '$username';";
$result = pg_query($query);

while ($row = pg_fetch_array($result))
{
    array_push($start_arr, array('title'=>$row['title'], 'q_id'=>$row['qid'], 'author_id'=>$row['asker'] ));
}

// needed text q_id q_title comment_id
$query = "select * from (forum_replies join question_forum on (forum_replies.qid = question_forum.qid)) as lund where uid='$username';";
$result = pg_query($query);

while ($row = pg_fetch_array($result))
{
    array_push($comm_arr, array('title'=>$row['title'], 'q_id'=>$row['qid'],
                            'author_id'=>$row['uid'], 'comment_id'=>$row['ts'] ));
}

echo json_encode(array('questions_started' => $start_arr, 'commented'=>$comm_arr));

?>
