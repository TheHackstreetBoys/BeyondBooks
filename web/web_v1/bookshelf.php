<!DOCTYPE HTML>
<?php
include_once 'db_conn.php';
session_start();
if(!isset($_SESSION["user_id"]))
{
	header('Location: index.php');
}
?>



<html class="bookshelf1">
<head>
<title>
Welcome to Beyond Books
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







<body style="height:auto;">
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

		<li><form action="" class="search-form">
                <div class="form-group has-feedback" id="search">

            		<input type="text" class="form-control" name="search" id="search1" placeholder="search">
              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
            	</div>
            </form></li>

	        <li><a href="homepage.php">Home</a></li>

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
				?>
		</a>

<ul class="dropdown-menu">
<li><a href="yourprofile.php">My profile</a></li>
</ul></li>

	      </ul>

	    </div><!-- /.navbar-collapse -->

	  </div><!-- /.container-fluid -->

	</nav>





	<div class="bookshelf">
		<?php
			$class1="book book-green";
			$class2="book book-umber";
			$class3="book book-blue";
			$class4="book book-springer";

			$query_bookshelf="SELECT * from book_shelf where user_id='$user_id'";
			$answer=pg_query($query_bookshelf);
			$rows=pg_num_rows($query_bookshelf);
			if($rows==0)
			{
				echo '<h3 style="color:blue;">Looks like you are not an avid reader! </h3>';
			}
			while($row=pg_fetch_array($answer))
			{
				$books=pg_query("SELECT * from books where isbn='$row[isbn]'");
				$bookname=pg_fetch_array($books);
				$var='$class'.rand(1,3);
				echo (rand(1,3));
				echo '<h3>'.$var.'</h3>';
				echo '<div class="'.$var.'">';
				echo '<h2><a href="book_main_page.php?isbn='.$row['isbn'].'&page=1">'.$bookname['title'].'</a></h2>';
				echo '</div>';
			}
		?>
	</div>












  <footer style="background-color:white;">
  <hr />
  <div class="container generalfooter">
  <hr>Beyond Books Everywhere</hr>
  </br>
  <p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
  <p class="text-right">Copyright &copy; Your Company 2014</p>
  </div>
  </footer>

</body>
</html>
