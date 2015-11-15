<?php
require 'db_conn.php';
//$username = $_POST['user_id'];
//$isbn = $_POST['isbn'];
$username = '201351010';
$isbn = '9781449615529';
require 'bookdetails.php';
$result = getDetails($isbn);
$times = date('d-m-Y H:i:s');

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
$done1 = $res1?true:false ;

$done2 = false;
foreach ($result['authors'] as $value)
{

    $done2=false;
    $res2 = pg_insert($dbconn, "author", array('isbn'=>$isbn, 'author'=>$value));
    if(!$res2)
    {
        break;
    }
    $done2=true;
}

if(done1 && done2)
{
    echo json_encode(array('result'=>'true'));
}
else
{

    echo json_encode(array('result'=>'false'));
}
?>
