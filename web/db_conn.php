<?php

$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=10.100.88.14 dbname=TITV user=postgres password=password") or die("could not connect!!!");
?>
