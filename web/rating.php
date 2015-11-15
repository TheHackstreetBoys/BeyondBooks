<?php
session_start();
include_once "db_conn.php";

if (isset($_POST['rate']) && !empty($_POST['rate'])) {
    $uid = $_SESSION["user_id"];
    $rate = real_escape_string($_POST['rate']);
// check if user has already rated
    $sql = "SELECT uid FROM rating WHERE uid = '$uid'";
    $result = pg_query($sql);
    $row = pg_fetch_array($result);

    if (pg_num_rows($row)> 0) {
        echo $row['uid'];
    } else {

        $sql = "INSERT INTO rating (uid, rating) VALUES ('$uid', '$rate'); ";
        if (pg_query($sql))
		{
            echo "0".pg_last_error();
        }
    }
}

?>
