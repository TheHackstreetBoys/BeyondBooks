<?php

require ('db_conn.php');

$sellerid = $_POST['user_id'];
$isbn = $_POST['isbn'];

$result = pg_query("SELECT * FROM single_sell WHERE isbn = '$isbn' ;" );
$row = pg_fetch_array($result);


if(pg_query("DELETE FROM single_sell WHERE isbn= '$isbn' ;" ))
{
$done1 = 'true';
}
$prodid = $row['prodid'];
if(pg_query("DELETE FROM pbase WHERE prodid= '$prodid' AND sellerid = '$sellerid' ;" ))
{
$done2 = 'true';
}

if($done1 && $done2)
{
    echo json_encode(array('result'=>'true'));
}
else
{

    echo json_encode(array('result'=>'false'));
}

?>
