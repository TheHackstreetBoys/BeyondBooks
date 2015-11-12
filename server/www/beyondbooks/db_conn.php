<?php

$dbconn=pg_connect("host=10.100.88.14 dbname=TITV user=postgres password=password");
$myfile = fopen('logfile.txt','w');
fwrite($myfile,pg_last_error()."$dbconn");
fclose($myfile);


?>
