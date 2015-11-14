<?php
require 'db_conn.php';
$isbn = $_POST['isbn'];

require 'bookdetails.php';
$resq = getDetails($isbn);


echo json_encode(array('book_name'=>$resq['title']));
?>
