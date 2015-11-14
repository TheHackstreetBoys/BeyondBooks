<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$username = "201351010";
$query = "select * from notify where whom='$username';";
$result = pg_query($query);
$res_arr = array();

while ($row = pg_fetch_array($result))
{
    array_push($res_arr,$row['des']);
}
echo json_encode(array('notifications'=>$res_arr));
?>
