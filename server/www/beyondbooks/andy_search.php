<?php
// Needed review list buy sell list and forum list
include 'db_conn.php';

$qs = $_POST['query'];
$query = "select * from (question_forum natural join user_profile) as lauda where lauda.title like '%qs%';";
$result = pg_query($query);

$for_ret=array();
while ($row = pg_fetch_row($result)) {
    append($for_ret,array("qid"=>$row['qid'], "author_id"=>$row['asker'], "author"=> $row['f_name'], "title"=>$row['title']));
}


$query = "select * from ((review natural join books) as lund natural join rating) as lauda where lauda.title like '%qs%';";
$result = pg_query($query);

$review_ret=array();
while ($row = pg_fetch_row($result)) {
    append($review_ret,array("image_link"=>$row['image_link'], "book_name"=>$row['title'],
    "ratings"=> $row['rating'], "isbn"=>$row['isbn']));
}
// needed book_name image_link isbn ratings
$query = "select * from ((pbase natural join single_sell) as lund natural join books) as lauda where lauda.title like '%qs%';";
$result = pg_query($query);

$bs_ret=array();
while ($row = pg_fetch_row($result)) {
    append($bs_ret,array("image_link"=>$row['image_link'], "book_name"=>$row['title'],
    "ratings"=> $row['rating'], "isbn"=>$row['isbn']));
}

echo json_encode(array('forum' => $for_ret, 'review' => $review_ret, 'buy_sell'=>$bs_ret )); 

?>
