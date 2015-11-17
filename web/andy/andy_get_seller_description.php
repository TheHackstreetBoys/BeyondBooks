<?php
require('db_conn.php');
$seller_id=$_POST['seller_id'];
$isbn=$_POST['isbn']='1001';

$query="SELECT * from (pbase JOIN single_sell on pbase.prodid=single_sell.prodid) as aiveen where isbn='$isbn' and sellerid='$seller_id'";
$result=pg_query($query);
$row=pg_fetch_array($result);


$final_arr = array();
$final_arr['review']=$row['description'];
echo json_encode($final_arr);

?>
