<?php
<<<<<<< HEAD
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

=======
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
>>>>>>> 83f6d6976d54a4ec1815be066a7711a3bf000010
