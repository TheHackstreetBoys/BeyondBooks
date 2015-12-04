<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];
$ratings = $_POST['ratings'];
$comment = $_POST['comment'];
$query = "insert into review values ('$username','$comment','$isbn');";

$result = pg_query($query);
if($result)
{
  $query = "insert into rating values ('$username','$ratings','$isbn');";
  $result1 = pg_query($query);
  if($result1)
  {
    echo json_encode(array('done_query'=>'true'));
  }
  else {
    echo json_encode(array('done_query'=>'false'));
  }
}
else {
  echo json_encode(array('done_query'=>'false'));
}


?>
