<?php
$bsf = $_POST['image'];
$username = $_POST['user_id'];
$uname = "$username"."_dp";
$outfile = fopen("../pictures/$uname.jpg",'w');
fwrite($outfile,base64_decode($bsf));
fclose($outfile);

?>
