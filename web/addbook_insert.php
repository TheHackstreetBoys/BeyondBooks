<?php

include_once 'db_conn.php';


$isbn = $_POST['isbn'];
$age = $_POST['age'];
$ts = time();

$price = $_POST['price'];
$description = pg_escape_string($_POST['description']);
$seller = $_SESSION["user_id"];

$query1 = pg_query("INSERT INTO pbase(ts, price, sellerid) VALUES ('$ts', '$price', '$seller')");

if($query1)
{

}


$query2 = pg_query("INSERT INTO single_sell(isbn, age, description, price) VALUES ('$isbn', '$age','$description', '$price')");

if($query2)
{
header("Location: homepage.php"); /* Redirect browser */
}




?>
