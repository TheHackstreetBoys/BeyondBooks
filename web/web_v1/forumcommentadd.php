<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

echo "Ji";
session_start();
//$username = $_SESSION['current'];
$user = "Kushal";
$id = $_GET['id'];
$name = "KJ";
$content = $_POST['content'];
$time = time();
  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);

 $query = pg_query("INSERT INTO reply (post_id, rollno, name, content, date) VALUES ('$id', '$user', '$name' , '$content', '$time')" );
 if($query)
{	
  $query1 = pg_query("UPDATE posts SET num_comments = num_comments+1 WHERE id= '$id'");
 if($query1)
{	
  header("Location: forumview.php?id= $id ");

}

}

else
{
  echo "Error".pg_last_error();
}





?>
