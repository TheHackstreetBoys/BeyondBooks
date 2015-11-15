<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$query = "select * from user_profile where user_id='$username';";
$result = pg_query($query);
$row = pg_fetch_array($result);
echo json_encode(array('user_name'=>"$row['f_name'] $row['l_name']", 'image_link'=>$row['image_location']));

?>
