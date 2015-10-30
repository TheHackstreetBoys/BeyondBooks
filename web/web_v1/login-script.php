<?
include_once 'db_conn.php';
session_start();
if($_SERVER['REQUEST_METHOD']=='POST')
{
$username=$_POST['username'];
$username=pg_real_escape_string($username);
$username=strip_tags($username);

$password=$_POST['password'];
$pwd=md5($password);

$query="SELECT * from user_profile where user_id = '$username'";
	$result=pg_query($query);
	$num=pg_num_rows($result);
	$row=pg_fetch_array($result);
	if($num == 0)
	{
		//header('location: index.php');
		echo 3;
	}
	else
	{
		if ($pwd == $row['password']) 
		{
			if($row['isfaculty']=='true')
			echo 2;
			else
			echo 1;
		}
		else
		{
			echo 0;
		}
	}			
}

?>
