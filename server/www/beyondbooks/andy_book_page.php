<?php
require('db_conn.php');
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];

$final_arr = array();
$query = "select * from books where isbn='$isbn';";
$result = pg_query($query);

$row = pg_fetch_array($result);
$final_arr['about_book'] = $row['description'] ;

$query = "select * from book_shelf where isbn='$isbn' and user_id='$username'";
$rnum = pg_num_rows(pg_query($query));
if($rnum > 0)
{
    $final_arr['bookshelf'] = "true";
}
else
{
    $final_arr['bookshelf'] = "false";
}

$query = "select * from (rating join user_profile on (rating.uid = user_profile.user_id)) as lund where isbn='$isbn' and isfaculty='t';";
$result = pg_query($query);
$rnum = pg_num_rows($result);

if ($rnum>0)
{
    $row = pg_fetch_array($result);
    $fac_rat = $row['rating'];
}
else
$fac_rat=0;

$query = "select * from (rating join user_profile on (rating.uid = user_profile.user_id)) as lund where isbn='$isbn' and isFaculty='false';";
$result = pg_query($query);
$rnum = pg_num_rows($result);

if ($rnum>0)
{
    $row = pg_fetch_array($result);
    $stud_rat = $row['rating'];
}
else {
  $stud_rat=0;
}

$final_arr['public_ratings']=(0.6*$fac_rat + 0.4*$stud_rat);
$final_arr['student_ratings']=strval($stud_rat);
$final_arr['faculty_ratings']=strval($fac_rat);
$final_arr['public_ratings']=strval($final_arr['public_ratings']);

$query = "select * from ((pbase natural join single_sell) as lund join user_profile on (lund.sellerid=user_profile.user_id))as chut where isbn ='$isbn';";
$result = pg_query($query);
$ret_array = array();
while ($row = pg_fetch_array($result))
{
    array_push($ret_array,array('seller_id'=>$row['sellerid'],'seller_name'=>$row['f_name'],'price'=>$row['price']));
}

$query = "select * from (review join user_profile on (review.uid = user_profile.user_id)) as lund where isbn='$isbn'";
$result = pg_query($query);
$ret_array1=array();
while ($row = pg_fetch_array($result))
{
    array_push($ret_array1,array('commentor_name'=>$row['f_name'].' '.$row['l_name'],'text'=>$row['review']));
}


$final_arr['comments']=$ret_array1;


$final_arr['sellers_list']=$ret_array;

echo json_encode($final_arr);
?>
