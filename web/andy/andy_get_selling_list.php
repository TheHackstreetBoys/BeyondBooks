<?php

require 'db_conn.php';
$username = $_POST['user_id'];

$query = "select isbn, title from
( books natural join ( select distinct isbn from
(single_sell natural join (select distinct prodid from pbase where sellerid = '$username') as lund) as pooku ) as pp ) as m ;";


$result = pg_query($query);
$fin_arr = array();
while ($row = pg_fetch_array($result))
{
    array_push($fin_arr,array('isbn'=>$row['isbn'], 'name'=>$row['title']));
}

echo json_encode(array("selling_list"=>$fin_arr));
?>
