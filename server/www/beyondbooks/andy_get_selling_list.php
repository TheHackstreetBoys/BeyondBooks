<?php
require('db_conn.php');
$user_id= '201351010';

$query="SELECT isbn from (pbase JOIN single_sell on pbase.prodid=single_sell.prodid) as aiveen WHERE sellerid='$user_id'";
$result=pg_query($query);
$row=pg_fetch_array($result);
$num=pg_num_rows($result);


while($row=pg_fetch_array($result))
{
$isbn = $row['isbn'];
$query1="SELECT * from books WHERE isbn ='$isbn'";
$result1=pg_query($query1);
$row1=pg_fetch_array($result1);

echo $row1['title'];
}



?>

