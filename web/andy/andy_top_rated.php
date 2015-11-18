<?php
include ("db_conn.php");


$query = "select * from books natural join (select isbn, (sum(rating)/count(rating))as fuck from rating group by isbn ) as lund order by fuck desc limit 4;";
$result=pg_query($query);


$ret=array();
while ($row = pg_fetch_array($result)) {
    array_push($ret,array("isbn"=>$row['isbn'], "ratings"=>$row['fuck'], "book_name"=> $row['title'], "image_link"=>null));
}

echo json_encode(array('top_rated'=>$ret));
?>
