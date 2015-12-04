<?php
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];
$add = $_POST['add'];

if($add=='true')
{

    $query = "insert into book_shelf values ('$username','$isbn');";
    $result = $pg_query($query);
}
else
{
    $query = "delete from book_shelf where user_id='$username' and isbn='$isbn';";
    $result = $pg_query($query);
}

echo json_encode(array('done_query'=>'true'));
?>
