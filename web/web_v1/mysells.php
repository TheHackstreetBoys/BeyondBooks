<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>My Activities</title>

    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

  </head>
  <body>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="page-header">
				<h1>
					My Activities
				</h1>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="page-header">
						<h2>
							Books Uploaded
						</h2>
<?php

		$num_rec_per_page=2;

			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


$sellerid = "201351022";

if (isset($_GET["page1"])) { $page  = $_GET["page1"]; } else { $page=1; }; 
$start_from = ($page-1) * $num_rec_per_page; 


	$result = pg_query("SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE pbase.sellerid = '$sellerid' LIMIT $num_rec_per_page OFFSET $start_from");



			if(!pg_num_rows($result)) {
							echo '<p> No Seller is available.</p>';
						     }
			else {

					echo "<br/><b>Books added for Selling:</b><br/>";

					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					$i = 1;
					while($row = pg_fetch_array($result))
				{
					
					echo $i.'. <b>ISBN:&nbsp;'.$row['isbn'].'</b><br/>';
					echo 'Price:&nbsp;'.$row['price'].'<br/>';
					echo 'Age:&nbsp;'.$row['age'].'<br/>';
					$body = $row['description'];
					echo "Description:&nbsp;".nl2br($body).'<br/>';
					echo "<a href='bookinfoedit.php?isbn=".$row['isbn']."'>Click Here to Edit</a><br/>";
					echo "<a href='bookdelete.php?isbn=".$row['isbn']."&sellerid=".$sellerid."&id=".$row['prodid']."'>Delete the link</a><br/><br/>";
					$i = $i+1;
				

$sql = "SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE pbase.sellerid = '$sellerid'"; 
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);


			     }

					
echo "<a href='mysells.php?page1=1'>".'Prev-'."</a> "; // Goto 1st page  

for ($i=1; $i<=$total_pages; $i++) { 
            echo "<a href='mysells.php?&page1=".$i."'>".$i."</a> "; 
}; 
echo "<a href='mysells.php?page1=$total_pages'>".'-Next'."</a> "; // Goto last page

			
}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					?>

					</div>
				</div>
				<div class="col-md-6">
					<div class="page-header">
						<h2>
							Forum Created
						</h2>
<?php

		$num_rec_per_page=2;

			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


$user = "201351022";

if (isset($_GET["page2"])) { $page  = $_GET["page2"]; } else { $page=1; }; 
$start_from = ($page-1) * $num_rec_per_page; 


	$result = pg_query("SELECT * FROM posts WHERE tname = '$user' LIMIT $num_rec_per_page OFFSET $start_from");



			if(!pg_num_rows($result)) {
							echo '<p> No Seller is available.</p>';
						     }
			else {


					echo "<br/><b>Forums Created by you:</b><br/>";
					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					$i = 1;
					while($row = pg_fetch_array($result))
				{
					
					echo $i.'. <b>Title:&nbsp;'.$row['title'].'</b><br/>';
					echo 'Course:&nbsp;'.$row['course'].'<br/>';
					echo 'Age:&nbsp;'.$row['date'].'<br/>';
					$body = $row['body'];
					echo "Description:&nbsp;".nl2br($body).'<br/><br/>';

					$i = $i+1;
				

$sql = "SELECT * FROM posts WHERE tname = '$user'"; 
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);


			     }

					
echo "<a href='mysells.php?page2=1'>".'Prev-'."</a> "; // Goto 1st page  

for ($i=1; $i<=$total_pages; $i++) { 
            echo "<a href='mysells.php?&page2=".$i."'>".$i."</a> "; 
}; 
echo "<a href='mysells.php?page2=$total_pages'>".'-Next'."</a> "; // Goto last page

			
}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					?>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>

  
  </body>
</html>
