<!doctype html>
<?php
include_once 'db_conn.php';
session_start();
if(!isset($_SESSION["user_id"]))
{
	header('Location: index.php');
}
?>
<html>
<head>
<title>
Edit Book Info
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

	      <a class="navbar-brand" href="homepage.php">Beyond Books</a>

	    </div>



	    <div class="collapse navbar-collapse" id="navbar-collapse-main">

	      <ul class="nav navbar-nav navbar-right">

		<li>
<?php
$content ='
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
$(function(){
$(".search").keyup(function()
{
var searchid = $(this).val();
var dataString = \'search=\'+ searchid;
if(searchid!=\'\')
{
    $.ajax({
    type: "POST",
    url: "search.php",
    data: dataString,
    cache: false,
    success: function(html)
    {
    $("#result").html(html).show();
    }
    });
}return false;
});

jQuery("#result").live("click",function(e){
    var $clicked = $(e.target);
    var $name = $clicked.find(\'.name\').html();
    var decoded = $("<div/>").html($name).text();
    $(\'#searchid\').val(decoded);
});
jQuery(document).live("click", function(e) {
    var $clicked = $(e.target);
    if (! $clicked.hasClass("search")){
    jQuery("#result").fadeOut();
    }
});
$(\'#searchid\').click(function(){
    jQuery("#result").fadeIn();
});
});
</script>

<style type="text/css">
    #searchid
    {
        width:190px;
        padding:8px;
        font-size:12px;
	margin-top:-1cm;

     margin-left:1cm;
       }
    #result
    {
        position:absolute;
        width:190px;
        padding:10px;
        display:none;
     margin-left:1cm;

        margin-top:-1px;
        border-top: 0px;
        overflow:hidden;
        border:1px #CCC solid;
        background-color: white;
    }
    .show
    {
        padding:10px;
        border-bottom:0px #999 ;
        font-size:12px;

        height:10px;


    }
    .show:hover
    {

        cursor:pointer;
    }
</style>
<div class="content" style="margin-top: -3%;">
<input type="text" class="form-control search"  id="searchid" placeholder="Search for Books" />
	<span class="glyphicon glyphicon-search form-control-feedback" style="padding-top: 15%; color: #3596e0;"></span>
<div id="result"> </div>
</div>
';


$pre = 1;
require ("html.inc");
?></li>


	        <li><br/><a href="homepage.php">Home</a></li>

					<li><br/><a href="forumWelcome.php">Forum</a></li>
		<li><br/><a href="logout-script.php">Log Out <span class="glyphicon glyphicon-log-out"></span></a></li>
			<li class="dropdown"><a href="#" data-toggle="dropdown"  class="dropdown-toggle">
				<?php
				$filename=$_SESSION["user_id"].'_dp';
				$filename="pictures/".$filename."*";
				$result1=glob($filename);
				if (!empty($result1))
				echo '<img src="'.$result1[0].'"class="img-circle" style="width: 50px">';
				else
					echo '<img src="images/user.png"class="img-circle" style="width: 50px">';
					?>
			</a>
<ul class="dropdown-menu">
<li><a href="yourprofile.php">My Profile</a></li>
<li><a href="bookshelf.php">My Bookshelf</a></li>
<li><a href="my_sold_books.php">My Sold Books</a></li>
<li><a href="addbook.php">Add a Book</a></li>
<li><a href="notificationpage.php">My Notifications <div class="circle">
<?php

session_start();
$user_id = $_SESSION["user_id"];

$query1 = pg_query("SELECT COUNT(*) AS num FROM notify WHERE whom = '$user_id'");

$row = pg_fetch_array($query1);
echo $row['num'];

?></div></a></li>
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

$isbn = $_GET['isbn'];
$sellerid = $_SESSION["user_id"];


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

//$ts = time();

if(pg_query("UPDATE single_sell SET age = '$age', description = '$des', price = '$price' WHERE isbn ='$isbn' AND prodid = '$prodid' " ))
{

if(pg_query("UPDATE pbase SET ts = CURRENT_TIMESTAMP WHERE sellerid ='$sellerid' AND prodid = '$prodid' " ))
	{
    //  header("Location: my_sold_books.php");
          echo "<script>setTimeout(\"location.href = 'my_sold_books.php';\");</script>";	}
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
								<input class="form-control" value = "<?php echo $row['price']?>" id="inputPassword3" name = 'price' type="text" required="required">
							</div>
						</div>

						<div class="form-group">

							<label for="inputPassword3" class="col-sm-4 control-label">
								Enter the Age of Book.
							</label>
							<div class="col-sm-4">
								<input class="form-control" placeholder = "This is showing the age of the book in months" value = "<?php echo $row['age']?>" id="inputPassword3"  name = 'age' type="text" required="required">
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
<p class="text-left"><a href="BeyondBooks.apk"><button type="button" class="btn btn-primary">Click here to Download our android app</button></a></p>
<p class="text-right">Copyright &copy; BeyondBooks</p>
</div>
</footer>
