<?php
require ('db_conn.php');
$username = $_POST['user_id'];

$query = "select * from (book_shelf natural join books) as bullshit where user_id='$username' limit 4;" ;
$result = pg_query($query);
echo pg_last_error();

$ret_arr= array();
while ($row=pg_fetch_array($result)) {
    array_push($ret_arr,array('book_name'=>$row['title'],'isbn'=>$row['isbn']));
}

echo json_encode(array('books' => $ret_arr ));
?>
