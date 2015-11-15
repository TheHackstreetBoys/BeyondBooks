<?php
include_once "db_conn.php";
session_start();
//$username = $_SESSION['current'];
$uid=$_SESSION["user_id"];
$result=pg_query("SELECT* from user_profile where user_id='$uid'");
$ans=mysql_fetch_row($result);
$user = $ans['f_name'];
$id = $_GET['id'];
$content = $_POST['content'];
$time = time();
  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);

 $query = pg_query("INSERT INTO reply (post_id, rollno, name, content, date) VALUES ('$id', '$uid', '$user' , '$content', '$time')" );
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
