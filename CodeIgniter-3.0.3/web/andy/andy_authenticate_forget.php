<?php
$username = $_POST['user_id'];
require 'db_conn.php';
$fw = fopen('logfile.txt','w');
fwrite($fw,$username);
fclose($fw);
$mail="$username@iiitvadodara.ac.in";


$seed = str_split('abcdefghijklmnopqrstuvwxyz'
                    .'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
                    .'0123456789'); // and any other characters
shuffle($seed); // probably optional since array_is randomized; this may be redundant
$rand = '';
foreach (array_rand($seed, 7) as $k) $rand .= $seed[$k];

$password=md5($rand);

$query="UPDATE user_profile SET password='$password' where user_id='$username';";
$result=pg_query($query);
$num=pg_num_rows($result);
$row=pg_fetch_array($result);

    $to      = $mail;
    $subject = "Change password request for";
    $subject =  $subject." ".$user_id;
    $message =  "This an auto generated password for username=$username.\n Your password is $rand";
    $headers = "From:".$user_id."@iiitvadodara.ac.in";


    if (mail($to, $subject, $message, $headers)) {
        echo json_encode(array('result'=>'true'));
    }
    else {
        echo json_encode(array('result'=>'false'));
    }


?>
