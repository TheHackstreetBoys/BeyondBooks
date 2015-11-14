<?php
$require 'db_conn.php';
$username=$_POST['user_id'];
$isbn=$_POST['isbn'];
$age=$_POST['age'];
$price=$_POST['price'];
$description = $_POST['description'];

$query = "insert into pbase (ts,sellerid ) values (CURRENT_TIMESTAMP, '$username');select lastval();";
$result = pg_query($query);
$row = pg_fetch_array($result);
$num = $row['lastval'];

$result = pg_insert($dbconn, "single_sell", array('prodid'=>$num, 'isbn'=>$isbn, 'age'=>$age, 'price'=>$price, 'description'=>$description));
if($result)
{
    
    echo json_encode(array('result'=>'true'));
}
else
{
    echo json_encode(array('result'=>'false'));
}

?>
