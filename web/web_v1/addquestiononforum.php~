<?
include_once 'db_conn.php';
session_start();
if($_SERVER['REQUEST_METHOD']=='POST')
{

$username =$_SESSION['username'];
$title    =$_POST['title'];
$title    =pg_real_escape_string($username);
$title    =strip_tags($title);

$ts = new DateTime();

$content =$_POST['content'];
$content =pg_real_escape_string($content);
$content =strip_tags($content );

$password=$_POST['password'];
$pwd=md5($password);

$query="INSERT INTO question_forum(ts, asker, title, content) VALUES ('$ts', '$username', '$title', '$content')";
	$result=pg_query($query);
}

?>
