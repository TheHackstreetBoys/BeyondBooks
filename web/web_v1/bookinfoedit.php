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
				Update Book Details<hr/>
			</h3>
			<div class="row">
				<div class="col-md-12">
					<h3 class="text-center">
						<b>Update Book Details</b> <hr/>
					</h3>

<?php


include_once 'db_conn.php';
$isbn = $_GET['isbn'];
$sellerid = "201351022";


$result = pg_query("SELECT * FROM  pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE single_sell.isbn = '$isbn' AND pbase.sellerid = '$sellerid'");

if(!pg_num_rows($result)) {
	echo 'No ';
}

$row = pg_fetch_array($result);

if(!empty($_POST)) 
{
$prodid = $row['prodid'];
$price = $_POST['price'];
$age = $_POST['age'];
$des = $_POST['description'];

$ts = time();

if(pg_query("UPDATE single_sell SET age = '$age', description = '$des', price = '$price' WHERE isbn ='$isbn' AND prodid = '$prodid' " ))
	{

if(pg_query("UPDATE pbase SET ts = '$ts', price = '$price' WHERE sellerid ='$sellerid' AND prodid = '$prodid' " ))
	{
      header("Location: mysells.php");
	
	}
}
	else
		echo pg_last_error();

	
	}
	








?>



					<form class="form-horizontal" role="form" method = "post" action = "">
						<div class="form-group">

							<label for="inputEmail3" class="col-sm-4 control-label">
								Enter the ISBN of the Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" id="inputEmail3" name = 'isbn' value = "<?php echo $_GET['isbn']?>" readonly type="email">
<center>


</center>

							</div>
						</div>

						<div class="form-group">

	

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Price of Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" value = "<?php echo $row['price']?>" id="inputPassword3" name = 'price' type="text">
							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Age of Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" placeholder = "This is showing the age of the book in months" value = "<?php echo $row['age']?>" id="inputPassword3"  name = 'age' type="text">
							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Description of Book.
							</label>
							<div class="col-sm-4">
								<textarea class="form-control" id="inputPassword3" value = "" name = 'description' type="text"><?php echo $row['description']?> </textarea>
							</div>
						</div>


					<center>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-6">

								<button type="submit" class="btn btn-default">
									Update book Details
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