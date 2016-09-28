<?php

$dbconn=pg_connect("host=localhost dbname=TITV user=postgres password=random");
$myfile = fopen('logfile.txt','w');
fwrite($myfile,pg_last_error()."$dbconn");
fclose($myfile);


?>
