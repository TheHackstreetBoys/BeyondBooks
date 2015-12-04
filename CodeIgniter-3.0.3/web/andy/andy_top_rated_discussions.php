<?php
//needed title author author_id q_id
include ('db_conn.php');
$query = "select * from user_profile join (select * from question_forum natural join (select  qid from forum_replies group by qid order by count(*) desc) as foo limit 4) as pooku on (user_profile.user_id=pooku.asker);";
$result = pg_query($query);

$ret_array = array();

while ($row = pg_fetch_array($result))
{
    array_push($ret_array, array('q_id'=>$row['qid'], 'author_id' => $row['asker'],
                            'author'=>$row['f_name'], 'title' => $row['title']));
}
echo json_encode(array('top_rated'=>$ret_array));
?>
