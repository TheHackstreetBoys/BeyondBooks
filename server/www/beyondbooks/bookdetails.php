<?php

echo "hello \n"."<br>";

$url = "https://www.googleapis.com/books/v1/volumes?q=isbn:9781451648546";

//$respose = http_get("http://www.google.com",array("timeout"=>1),$info);


// $response = http_get($url, array("timeout"=>1), $info);
$response = file_get_contents($url);
$decoded = (array)json_decode($response);
var_dump($decoded);

?>