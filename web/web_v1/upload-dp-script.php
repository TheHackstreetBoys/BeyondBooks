
<?php
include_once 'db_conn.php';
session_start();

$userid=$_SESSION["user_id"];
$id=$userid;

$valid_formats = array("jpg", "png", "gif");

$temp=explode(".",$_FILES["images"]["name"]);
$newfilename = $id.'_dp'.'.'.end($temp);


$uploadOk = 1;
$size = $_FILES['images']['size'];

if(in_array(end($temp),$valid_formats))
{
	if($size<(1024*1024))
	{
		$uploadOk=1;
	}
	else
		$uploadOk=0;
}
else
{
	$uploadOk=0;
}



if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
}


else
{
    if (move_uploaded_file($_FILES["images"]["tmp_name"],"pictures/".$newfilename))
    {
    	echo "<img src='pictures/".$newfilename."' height=200px width=200px style='margin:0 25%;'> ";
    }
    else {
        echo 0;
    }
}
?>
