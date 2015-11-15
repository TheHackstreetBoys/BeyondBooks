<?php

function getDetails($isbnl)
{
    /* needed description authors title and publisher*/
    $url = "https://www.googleapis.com/books/v1/volumes?q=isbn:$isbnl";
    $response = file_get_contents($url);

    $decoded = json_decode($response);

    $res = array('authors' => $decoded->{'items'}[0]->{'volumeInfo'}->{'authors'},
            "title" => $decoded->{'items'}[0]->{'volumeInfo'}->{'title'},

            "publisher" => $decoded->{'items'}[0]->{'volumeInfo'}->{'publisher'},
            "description" => $decoded->{'items'}[0]->{'volumeInfo'}->{'description'},
            "image-link"=> $decoded->{'items'}[0]->{'volumeInfo'}->{'imageLinks'}->{'thumbnail'});

    return $res;
}

?>
