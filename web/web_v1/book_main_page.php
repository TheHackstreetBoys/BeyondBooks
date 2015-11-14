<!doctype html>

<html>
<head>
<title>
Main Page of Book
</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Bree+Serif' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Philosopher' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/bootstrap.min.css"/>
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<script src="js/modernizr-2.6.2.min.js"></script>
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link href='css/rating.css' rel='stylesheet' type='text/css'/>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/rating.js"></script>



<script type="text/javascript">
$(document).ready(function(){
	$(".dropdown, .btn-group").hover(function(){
		var dropdownMenu = $(this).children(".dropdown-menu");
		if(dropdownMenu.is(":visible")){
			dropdownMenu.parent().toggleClass("open");
		}
	});
});
</script>
</head>







<body>
<!--                                                                                -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	  <div class="container-fluid">

	    <!-- Brand and toggle get grouped for better mobile display -->

	    <div class="navbar-header">

	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-main">
	        <span class="sr-only">Toggle navigation</span>

	        <span class="icon-bar"></span>

	        <span class="icon-bar"></span>

	        <span class="icon-bar"></span>
	      </button>

	      <a class="navbar-brand" href="#">Beyond Books</a>

	    </div>



	    <div class="collapse navbar-collapse" id="navbar-collapse-main">

	      <ul class="nav navbar-nav navbar-right">

		<li><form action="" class="search-form">
                <div class="form-group has-feedback" id="search">

            		<input type="text" class="form-control" name="search" id="search1" placeholder="search">
              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
            	</div>
            </form></li>

	        <li><a href="#home">Home</a></li>

	        <li><a href="#about">About</a></li>
		<li><a href="logout-script.php">Log Out <span class="glyphicon glyphicon-log-out"></span></a></li>
		<li class="dropdown"><a href="#" data-toggle="dropdown"  class="dropdown-toggle">
			<?php
			
			   $user_id=$_SESSION["user_id"];
				 $query="SELECT * FROM user_profile where user_id='$user_id'";
			   $result=pg_query($query);
			   $row=pg_fetch_array($result);
			$filename=$row['user_id'].'_dp';
			$filename="pictures/".$filename."*";
			$result1=glob($filename);
			if (!empty($result1))
			echo '<img src="'.$result1[0].'"class="img-circle" style="width: 50px">';
			else
				echo '<img src="images/user.png"class="img-circle" style="width: 50px">';
				?></a>

<ul class="dropdown-menu">
<li><a herf="#">My profile</a></li>
<li><a href="#">My uploads</a></li>
</ul></li>

	      </ul>

	    </div><!-- /.navbar-collapse -->

	  </div><!-- /.container-fluid -->

	</nav>

<br/><br/><br/><br/>
<hr/>
  <div class="container-fluid">
	<div class="row">
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-6"><br/>
	<?php
session_start();
			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

			$isbn = $_GET['isbn'];
			$_SESSION['isbn'] = $isbn;

	$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn WHERE books.isbn = '$isbn'");



			if(!pg_num_rows($result)) {
							echo '<p>No Book is available.</p>';
						     }
			else {

					while($row = pg_fetch_array($result))
				{
					echo '<b>'.$row['title'].'</b></br>';
					echo "<b> By :".$row['author']."</b>";

			     }
					}

echo "	</br></br>				<img src='http://www.librarything.com/devkey/KEY/medium/isbn/$isbn'  alt='Image is not available' >  ";
					?>




					<br/><br/>

<?php
			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");



	$result = pg_query("SELECT COUNT(uid) AS total FROM rating WHERE isbn = '$isbn'");
	$result1 = pg_query("SELECT sum(rating) AS totalrating FROM rating WHERE isbn = '$isbn'");
	$row = pg_fetch_array($result);
	$row1 = pg_fetch_array($result1);

$starNumber = $row1['totalrating']/$row['total'];
echo "Rating :";
    for($x=1;$x<=$starNumber;$x++) {
        echo '<img src="full.png" />';
    }
    if (strpos($starNumber,'.')) {
        echo '<img src="half.png" />';
        $x++;
    }
    while ($x<=5) {
        echo '<img src="zero.png" />';
        $x++;
    }


					?>
<br/><br/>
Your Rating:
<div id="rating_panel" data-pollid="1" data-rated="0">
					<img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /><div id="starloader"></div>
				</div>

				<br/><br/><button type="button" class="btn btn-success">
						 + ADD to wishlist
					</button>
				</div>
				<div class="col-md-6">
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<h3>
				Rating and Reviews
			</h3>


<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

session_start();
//$username = $_SESSION['current'];

$uid = "201351005";
$review = pg_escape_string($_POST['content']);
$isbn = $_GET['isbn'];

  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);

if (!empty($review))
{
$result = pg_query("SELECT * FROM review WHERE uid='$uid' AND isbn = '$isbn'");
if(pg_num_rows($result)!=0 ) 
{
 $query = pg_query("UPDATE review SET review = '$review' WHERE uid = '$uid' AND isbn = '$isbn'" );
}
else
{
 $query1 = pg_query("INSERT INTO review (uid, review, isbn) VALUES ('$uid', '$review', '$isbn') " );
}

}

?>

	<?php
		$num_rec_per_page=2;

			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

if (isset($_GET["page"])) { $page  = $_GET["page"]; } else { $page=1; }; 
$start_from = ($page-1) * $num_rec_per_page; 

	$result = pg_query("SELECT * FROM review WHERE isbn = '$isbn' LIMIT $num_rec_per_page OFFSET $start_from");

			if(!pg_num_rows($result)) {
							echo '<p>No forums is Created Yet.</p>';
						     }
			else {

					echo "<br/><b>Reviews of the Users:</b><br/><br/>";

					while($row = pg_fetch_array($result))
				{
					echo '<b>'.$row['uid'].'</b><br/>';
					$body = $row['review'];

					echo "".nl2br($body).'<br><br/>';
				

$sql = "SELECT * FROM review WHERE isbn = '$isbn'"; 
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);




			     }
echo "<a href='book_main_page.php?isbn=$isbn&page=1'>".'Prev-'."</a> "; // Goto 1st page  

for ($i=1; $i<=$total_pages; $i++) { 
            echo "<a href='book_main_page.php?isbn=$isbn&page=".$i."'>".$i."</a> "; 
}; 
echo "<a href='book_main_page.php?isbn=$isbn&page=$total_pages'>".'-Next'."</a> "; // Goto last page

					}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
		echo <<<HTML

 <form class="form-horizontal" role="form" method="post" action="">
								<div class="form-group">

							
									<div class="col-sm-10">

				<textarea class="form-control" name = "content" rows = "1" id="inputEmail3" type="text"> Add Your Review</textarea>

									</div>
								</div>

<div class="form-group">

							
									<div class="col-sm-4">

				<input class="form-control" type ="hidden" value = "$isbn" name = "isbn" id="inputEmail3" type="text"/>

									</div>
								</div>



	<div class="form-group">

							
									<div class="col-sm-4">

				<input class="form-control" type ="submit" name = "Submit" value =" Add Review" id="inputEmail3" type="text"/>

									</div>
								</div>


								
							</form>
HTML;

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';



					?>



							
				


			<p>

			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<h3>
				About the Book
			</h3>
			<p>
				<?php
session_start();
				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

			$isbn = $_GET['isbn'];
			$_SESSION['isbn'] = $isbn;

	$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn WHERE books.isbn = '$isbn'");



			if(!pg_num_rows($result)) {
							echo '<p>No Book is available.</p>';
						     }
			else {

					while($row = pg_fetch_array($result))
				{
					
					
		

					echo '<b> Title :</b> '.$row['title'].'<br/>';
					echo "<b> Authors :</b><em>".$row['author']."</em><br/>";
				        echo '<b> Publication: </b><em>'.$row['publisher'].'</em><br/>';
					echo '<b> Description: </b><em>'.$row['description'].'</em><br/>';




					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
			     }
					}

					?>

			</p>
		</div>
		<div class="col-md-6">
			<h3>
				Any User Selling Book
			</h3>
			<?php

		$num_rec_per_page=1;

			$dbconn=null;
			global $dbconn;
			$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

if (isset($_GET["page1"])) { $page  = $_GET["page1"]; } else { $page=1; }; 
$start_from = ($page-1) * $num_rec_per_page; 

	$result = pg_query("SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE single_sell.isbn = '$isbn' AND pbase.prodid = single_sell.prodid LIMIT $num_rec_per_page OFFSET $start_from");

			if(!pg_num_rows($result)) {
							echo '<p> No Seller is available.</p>';
						     }
			else {

					echo "<br/><b>Available Seller:</b><br/>";

					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';

					while($row = pg_fetch_array($result))
				{
					echo '<b>Seller:&nbsp;'.$row['sellerid'].'</b><br/>';
					echo 'Price:&nbsp;'.$row['price'].'<br/>';
					echo 'Age:&nbsp;'.$row['age'].'<br/>';
					$body = $row['description'];
					echo "Description:&nbsp;".nl2br($body).'';
					$user_id = "201351022";
					echo " <form method = 'POST' action= 'mailproceed.php'>

  						<input type='hidden' name = 'isbn' value =".$row['isbn'].">
  						<input type='hidden' name = 'sellerid' value =".$row['sellerid'].">
  						<input type='hidden' name = 'user_id' value =".$user_id.">

  						<input type='submit' value = 'Show Interest'>
						</form><br/> ";

$sql = "SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE single_sell.isbn = '$isbn' AND pbase.prodid = single_sell.prodid"; 
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);


			     }

					
echo "<a href='book_main_page.php?isbn=$isbn&page1=1'>".'Prev-'."</a> "; // Goto 1st page  

for ($i=1; $i<=$total_pages; $i++) { 
            echo "<a href='book_main_page.php?isbn=$isbn&page1=".$i."'>".$i."</a> "; 
}; 
echo "<a href='book_main_page.php?isbn=$isbn&page1=$total_pages'>".'-Next'."</a> "; // Goto last page

			
}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					?>


		</div>
	</div>
</div>


<footer>
<hr />
<div class="container">
<hr>Beyond Books Everywhere</hr>
</br>
<p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
<p class="text-right">Copyright &copy; <img class="img-thumbnail" alt="Bootstrap Image Preview" src="images/hackstreetboys.png" height="42" width="42"> The Hackstreet Boys </p>
</div>
</footer>
