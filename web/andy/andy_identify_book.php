<?php
require 'db_conn.php';
$isbn = $_POST['isbn']='1001';
$result = null;
if(pg_num_rows(pg_query("select * from books where isbn='$isbn'")) == 0)
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
else {
    $result = pg_query("select * from books where isbn='$isbn'");
    $result = pg_fetch_array($result);
}

echo json_encode(array('book_name'=>$result['title']));
?>
