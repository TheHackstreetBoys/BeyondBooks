<?php
$require 'db_conn.php';
$username=$_POST['user_id'];
$isbn=$_POST['isbn'];
$age=$_POST['age'];
$price=$_POST['price'];
$description = $_POST['description'];

if(pg_num_rows(pg_query("select * from books where isbn=$isbn")) == 0)
{
    require 'bookdetails.php';
    $result = getDetails($isbn);

    $title = $result['title'];
    $description = $result['description'];
    $publisher = $result['publisher'];
    $query = "insert into books (isbn, title, publisher,description, by_user, ts) values ('$isbn', '$title',
                                    '$publisher', '$description', '$username', CURRENT_TIMESTAMP);";
    $imagelink= $result['image-link'];

    $content = file_get_contents($imagelink);
    $fp = fopen("../books_pics/$isbn.jpg", "w");
    fwrite($fp, $content);
    fclose($fp);
    $res1 = pg_query($query);

}
$query = "insert into pbase (ts,sellerid ) values (CURRENT_TIMESTAMP, '$username');select lastval();";
$result = pg_query($query);
$row = pg_fetch_array($result);
$num = $row['lastval'];

$result = pg_insert($dbconn, "single_sell", array('prodid'=>$num, 'isbn'=>$isbn, 'age'=>$age, 'price'=>$price, 'description'=>$description));
if($result)
{

    echo json_encode(array('result'=>'true'));
}
else
{
    echo json_encode(array('result'=>'false'));
}

?>
