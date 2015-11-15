<?php

include ("db_conn.php");

$query = "select * from (books natural join rating) as coll order by ts desc limit 4;";
$result=pg_query($query);


$ret=array();
while ($row = pg_fetch_array($result)) {
    array_push($ret,array("isbn"=>$row['isbn'], "ratings"=>$row['rating'], "book_name"=> $row['title'], "image_link"=>$row['image_link']));
}

echo json_encode(array('newly_added'=>$ret));
?>
