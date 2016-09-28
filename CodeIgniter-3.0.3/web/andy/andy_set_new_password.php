<?php
require 'db_conn.php';
$username=$_POST['user_id'];
$password=$_POST['password'];
$pwd=md5($password);

$query="UPDATE user_profile SET password='$pwd' where user_id='$username';";

$result=pg_query($query);
if($result)
  echo json_encode(array('result'=>'true'));
else
  echo json_encode(array('result'=>'false'));

?>
