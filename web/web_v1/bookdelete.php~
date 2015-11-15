<?php

include_once 'db_conn.php';

$id = $_GET['id'];
$isbn = $_GET['isbn'];
$sellerid = $_GET['sellerid'];
 
pg_query("DELETE FROM pbase WHERE prodid= '$id' AND sellerid = '$sellerid' " );
pg_query("DELETE FROM single_sell WHERE prodid = '$id' AND isbn= '$isbn'" );
header("Location: mysells.php");

?>
