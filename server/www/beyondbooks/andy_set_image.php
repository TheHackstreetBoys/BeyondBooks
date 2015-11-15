<?php
$bsf = $_POST['image'];
$username = $_POST['user-id'];
$outfile = fopen('../pictures/$username.jpg','w');
fwrite($outfile,base64_decode($bsf));
fclose($outfile);
?>
