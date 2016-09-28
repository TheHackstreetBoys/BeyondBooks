<?php

include ('db_conn.php');
var_dump($dbconn);
if($dbconn==null)
{
    echo "tumhari maa ki chut";
}
else
{
    echo pg_dbname($dbconn);
}



?>
