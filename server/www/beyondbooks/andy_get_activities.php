<?php

$username = $_POST['user_id'];

$query = "select * from books where by_user='$username'; limit 4";
$result = pg_query($query);


$ret_books = array();
while ($row = pg_fetch_array($result)) {
    append($ret_books, array('book_name'=>$row['title'], 'isbn'=>$row['isbn'] ));
}


$query = "select * from (books natural join review) as bullshit where uid='$username'; limit 4";
$result = pg_query($query);

$ret_rev = array();
while ($row = pg_fetch_array($result)) {
    append($ret_rev, array('book_name'=>$row['title'], 'isbn'=>$row['isbn'] ));
}

echo json_encode(array('uploads' => $ret_books, 'reviewed'=>$ret_rev ));
?>
