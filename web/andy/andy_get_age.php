<?php
require('db_conn.php');
$seller_id=$_POST['seller_id']='201351010';
$isbn=$_POST['isbn']='1001';

$query="SELECT * from (pbase JOIN single_sell on pbase.prodid=single_sell.prodid) as aiveen where isbn='$isbn' and sellerid='$seller_id'";
$result=pg_query($query);
$row=pg_fetch_array($result);
$num=pg_num_rows($result);
$final_arr = array();
if($num==0)
$final_arr['age']=strval(0);
else {
  $final_arr['age']=$row['age'];
}



echo json_encode($final_arr);


?>
