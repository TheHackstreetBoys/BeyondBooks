<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

echo "Ji";
session_start();
//$username = $_SESSION['current'];

$uid = "201351022";
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
  echo "Error".pg_last_error();
}





?>
