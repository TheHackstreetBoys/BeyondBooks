<?php
require 'db_conn.php';
$username = $_POST['user_id'];
$isbn = $_POST['isbn'];
require 'bookdetails.php';
$result = getDetails($isbn);
$times = date('d-m-Y H:i:s');

$query = "insert into books (isbn, title, publisher,description, by_user, ts) values ($isbn, $result['title'], $result['publisher'], $result['description'],$username,$times);";
$res1 = pg_insert($dbconn, "books", array("isbn"=>$isbn, 'title'=>$result['title'], 'publisher'=> $result['publisher'],
                            'description'=>$result['description'], 'by_user'=>$username, 'ts'=>$times));
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
