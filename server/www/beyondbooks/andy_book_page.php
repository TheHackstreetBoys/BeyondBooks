<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];

$final_arr = array();
$query = "select * from books where isbn='$isbn';";
$result = pg_query($query);

$row = pg_fetch_array($result);
array_push($final_arr, 'about_book'=>$row['description'] );

$query = "select * from book_shelf where isbn='$isbn' and user_id='$username';";
$rnum = pg_num_rows($query);
if($rnum > 0)
{
    array_push($final_arr,'bookshelf'=>"true");
}
else
{
    array_push($final_arr,'bookshelf'=>"false");
}

$query = "select * from (rating join user_profile on (rating.uid = user_profile.user_id)) as lund where isbn='$isbn' and isFaculty='true';";
$result = pg_query($query);
$rnum = pg_num_rows($query);

if ($rnum>0)
{
    $row = pg_fetch_array($result);
    $fac_rat = $row['rating'];
}

$query = "select * from (rating join user_profile on (rating.uid = user_profile.user_id)) as lund where isbn='$isbn' and isFaculty='false';";
$result = pg_query($query);
$rnum = pg_num_rows($query);

if ($rnum>0)
{
    $row = pg_fetch_array($result);
    $stud_rat = $row['rating'];
}

array_push($final_arr,'student_ratings'=>$stud_rat,'faculty_ratings'=>$fac_rat,'public_ratings'=>(0.6*fac_rat + 0.4*student_ratings));

$query = "select * from (pbase natural join single_sell) as lund where isbn ='$isbn';";
$result = pg_query($query);

while ($row = pg_fetch_array($result))
{

}

?>
