<?php

$dbconn=null;

function connect_db()
{
	global $dbconn;
	$dbconn=pg_connect("host=localhost dbname=TITVBB user=postgres password=random") or die("couldnot connect!!!");
}

?>