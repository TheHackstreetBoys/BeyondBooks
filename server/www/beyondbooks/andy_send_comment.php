<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$text = $_POST['text'];
$qid = $_POST['q_id'];
$flog = fopen('logfile.txt','w');
$write = $username." ".$text." ".$qid;

fwrite($flog,$write);

fclose($flog);

$query = "insert into forum_replies (qid,ts,uid, reply) values ($qid, CURRENT_TIMESTAMP, '$username', '$text');";

$result = pg_query($query);
if ($result)
{
    echo json_encode(array('result'=>'true'));
}
else
{
    echo json_encode(array('result'=>'false'));
}
?>
