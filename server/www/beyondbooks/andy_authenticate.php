<? php

include ("db_conn.php");

$username = $_POST['id'];
$password = $_POST['password']



$allow = false;
$query = "select * from user_profile where user_id=".$username.";";
$result=pg_query($query);
while ($row = pg_fetch_row($result)) {
    if (strcmp($row[3],md5($password))==0)
    {
        $allow=true;
    }

}
echo json_encode(array("result" => "$allow"));
?>
