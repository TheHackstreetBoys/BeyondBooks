<?php

include_once 'db_conn.php';

session_start();

if(!empty($_POST['price']))
{
$isbn = $_POST['isbn'];
$age = $_POST['age'];

$price = $_POST['price'].'.0';

$description = pg_escape_string($_POST['description']);
$seller = $_SESSION["user_id"];

$query1 = pg_query("INSERT INTO pbase(ts, sellerid) VALUES (CURRENT_TIMESTAMP, '$seller'); select lastval();");
$prodid = pg_fetch_array($query1)[0];
echo pg_last_error();

$query2 = pg_query("INSERT INTO single_sell(prodid, isbn, age, description, price) VALUES ('$prodid', '$isbn', '$age','$description', '$price')");
echo pg_last_error();

header("Location: homepage.php"); /* Redirect browser */
}
else
{

  header("Location: homepage.php"); /* Redirect browser */
  # code...
}



?>
