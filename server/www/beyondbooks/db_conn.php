<?php

$dbconn=null;
$useragent=$_SERVER['HTTP_USER_AGENT'];
function connect_db()
{
	global $dbconn;
	$dbconn=pg_connect("host=localhost dbname=TITVBB user=postgres password=random") or die("couldnot connect!!!");
}
function sql_query($str)
{
	global $dbconn,$useragent;
	echo $str;
	$result=pg_query($dbconn,$str);
	
	if (!$result) {
		echo "An error occurred.\n".pg_last_error();
		exit;
	}
	if($useragent!="maakichut")
	{
		while ($row = pg_fetch_row($result)) {
			var_dump($row);
			echo "<br />\n";
		}
	}
	else 
	{
		$jobj=array();
		while ($row = pg_fetch_row($result)) {
			array_push($jobj, $row);
		}
		echo json_encode($jobj);
	}
	
}
?>