<?php

if($dbconn != null)
{
    include ("db_conn.php");
}

$query = "select * from (books natural join ratings) as coll order by ratings desc limit 4;";
$result=pg_query($query);


$ret=array();
while ($row = pg_fetch_row($result)) {
    append($ret,array("isbn"=>$row['isbn'], "ratings"=>$row['ratings'], "book_name"=> $row['title'], "image_link"=>$row['image_link']));
}

echo json_encode($ret);
?>
