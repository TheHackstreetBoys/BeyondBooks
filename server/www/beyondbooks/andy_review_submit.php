<?php
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];
$ratings = $_POST['ratings'];
$comment = $_POST['comment'];
$query = "insert into review values ('$username','$comment','$isbn');";
$result = pg_query($query);

$query = "insert into rating values ('$username','$ratings','$isbn');";
$result = pg_query($query);

echo json_encode(array('done_query'=>'true'));
?>
