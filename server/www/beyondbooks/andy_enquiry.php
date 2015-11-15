<?php
$require 'db_conn.php';
$username = $_POST['buyer_id'];
$sellerid = $_POST['seller_id'];
$isbn = $_POST['isbn'];

$query = "select prodid from (pbase natural join single_sell) as lund where isbn='$isbn' and sellerid='$sellerid';";

$result = pg_query($query);
$row = pg_fetch_array($result);
$prodid = $row['prodid'];

$query = "insert into bid (prodid, ts, bidder) values ($prodid, CURRENT_TIMESTAMP, '$username');";
$result = pg_query($query);

$query = "insert into notify (whom, des, link) values ('$sellerid', '$username is interested in buying your book', $prodid);";

$result = pg_query($query);
if($result)
{
    echo json_encode(array('result'=>'true'));
}
else
{
    echo json_encode(array('result'=>'false'));
}

?>
