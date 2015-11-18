<?php
//needed title author author_id q_id
require 'db_conn.php';
$query = "select * from (question_forum join user_profile on (question_forum.asker = user_profile.user_id)) as lund order by ts desc limit 4;";
$result = pg_query($query);

$final_arr = array();

while ($row = pg_fetch_array($result))
{
    array_push($final_arr,array('title' => $row['title'], 'author_id'=>$row['asker'], 'author'=>$row['f_name'], 'q_id'=>$row['qid']));
}

echo json_encode(array('recent' => $final_arr));
?>
