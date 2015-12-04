<?php
include_once "db_conn.php";
session_start();
//$username = $_SESSION['current'];

$uid = $_SESSION["user_id"];
$review = $_POST['content'];
$isbn = $_POST['isbn'];

  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);

 $query = pg_query("INSERT INTO review (uid, review, isbn) VALUES ('$uid', '$review', '$isbn')" );
 if($query)
{

  header("Location: book_main_page.php");

}

else
{
  echo "Error addign your review.";
  header("refresh:2;Location: book_main_page.php");
}





?>
