<?php

/* needed description authors title and publisher*/
$url = "https://www.googleapis.com/books/v1/volumes?q=isbn:9781451648546";

//$respose = http_get("http://www.google.com",array("timeout"=>1),$info);


// $response = http_get($url, array("timeout"=>1), $info);
$response = file_get_contents($url);
$decoded = json_decode($response);
//var_dump($decoded);

$res = array('author' => $decoded->{'items'}[0]->{'volumeInfo'}->{'authors'},
        "title" => $decoded->{'items'}[0]->{'volumeInfo'}->{'title'},

        "publisher" => $decoded->{'items'}[0]->{'volumeInfo'}->{'publisher'},
        "description" => $decoded->{'items'}[0]->{'volumeInfo'}->{'description'});

$author = $res['author'][0];
$title  = $res['title'];
$publisher = $res['publisher'];
$description = $res['description'];


?>
