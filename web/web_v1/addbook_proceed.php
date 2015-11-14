<!doctype html>
<html>
<head>
<title>
Add Your Book
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
<script src="js/jquery.min.js"></script>

<script src="js/scripts.js"></script>




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
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h3><br/>
				Add New Book <hr/>
			</h3>
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-center">
						<b>Add Your Book</b> <hr/>
					</h3>
					<form class="form-horizontal" role="form" method = "post" action = "addbook_insert.php">
						<div class="form-group">

							<label for="inputEmail3" class="col-sm-4 control-label">
								Enter the ISBN of the Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" id="inputEmail3" name = 'isbn' value = "<?php echo $_POST['isbn']?>" readonly type="email">
<center>
	<?php


/* needed description authors title and publisher*/
$isbn = $_POST['isbn'];
$url = "https://www.googleapis.com/books/v1/volumes?q=isbn:".$isbn;

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
$description = pg_escape_string($res['description']);
echo "<br/><br/><br/><br/>";
echo "<b>Authors:</b>".$author."<br/>";
echo "<b>Title:</b>".$title."<br/>";
echo "<b>Publisher:</b>".$publisher."<br/>";
echo "<b>Description:</b>". substr($description, 0, 200)."....<br/><br/><br/><br/>";



$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


$query1 = pg_query("INSERT INTO books(isbn, publisher, description, title) VALUES ('$isbn', '$publisher', '$description', '$title')");

 if($query1)
{
}
else
{
  echo "Error".pg_last_error();
}





$query = pg_query("INSERT INTO author(isbn, author) VALUES ('$isbn', '$author')");

 if($query)
{

}
else
{
  echo "Error".pg_last_error();
}

?>
</center>

							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Price of Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" id="inputPassword3" name = 'price' type="text">
							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Age of Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" id="inputPassword3"  name = 'age' type="text">
							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Description of Book.
							</label>
							<div class="col-sm-4">
								<textarea class="form-control" id="inputPassword3"  name = 'description' type="text"> </textarea>
							</div>
						</div>


					<center>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">

								<button type="submit" class="btn btn-default">
									+ Click Here to Add Book
								</button>
							</div>
					</center>

						</div>
					</form>
				</div>
			</div>
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
