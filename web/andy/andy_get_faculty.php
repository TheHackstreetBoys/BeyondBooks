<?php
require 'db_conn.php';
$query = "select f_name, l_name from user_profile where isFaculty=true;";
$result = pg_query($query);
$fin_arr = array();

while ($row = pg_fetch_array($result))
{
    $string = $row['f_name']." ". $row['l_name'];
    array_push($fin_arr, $string);
}
echo json_encode(array('faculties'=>$fin_arr));
?>
