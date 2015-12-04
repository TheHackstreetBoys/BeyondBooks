<?php

include ("db_conn.php");

$username = $_POST['id'];
$password = $_POST['password'];


$myfile = fopen("logfile.txt", "w");

fwrite($myfile,"$username\n$password");

$allow = "false";
$query = "select * from user_profile where user_id='$username';";
$result=pg_query($query);


while ($row = pg_fetch_array($result))
{
    if (strcmp($row['password'],(string)md5($password))==0)
    {
        $allow="true";
        break;
    }

}
fwrite($myfile,"\n".$allow);
echo json_encode(array('result' => "$allow"));
?>
