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
Edit Your Question
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


<!-- <script src="http://js.nicedit.com/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
-->

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
		<li><a href="logout-script.php">Log Out <span class="glyphicon glyphicon-log-out"></span></li>
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



<br/><br/><br/><br/><br/>
    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
 <div id="header">
         <center> <h1> <a href="#">Edit Your Question Here!</br>  </a></h1> </center> <br/>
      </div>
<hr>

<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

if(!empty($_POST)) {
$course = $_POST['course'];
$title = $_POST['title'];
$body = $_POST['body'];
$date = time();
$id = $_GET['id'];

	if(pg_query("UPDATE posts SET course= '$course', title= '$title', body= '$body', date='$date' WHERE id='$id'" ))

       header("Location: forumview.php?id=$id");
	else
		echo pg_last_error();
}
$id1 = $_GET['id'];
$result = pg_query("SELECT * FROM posts WHERE id='$id1'" );

if(!pg_num_rows($result)) {
	echo 'Post #'.$_GET['id'].' not found';
	exit;
}

$row = pg_fetch_array($result);

echo <<<HTML

			<form class="form-horizontal" role="form" method="post" >
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-4 control-label">
						Enter the title :
					</label>
					<div class="col-sm-5">
						<input class="form-control" value="{$row['title']}" name = "course" id="inputEmail3" type="text">
					</div>
				</div>
				<div class="form-group">

					<label for="inputPassword3" class="col-sm-4 control-label">
						Enter the Content of the Question :
					</label>
					<div class="col-sm-5">
						<textarea class="form-control" name = "body" id="inputPassword3" rows = "4" type="text">{$row['body']} </textarea>
					</div>
				</div>

				<div class="form-group">

					<label for="inputPassword3" class="col-sm-4 control-label">
						Enter the Name of the Hash Tag :
					</label>
					<div class="col-sm-5">
						<input class="form-control" value="{$row['course']}" name= "title" id="inputPassword3" type="text">
					</div>
				</div>



				<center>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-9">
						 				<br/>						 				<br/>						 				<br/>
						<button type="submit" size = "6" class="btn btn-default">
							Update Discussion Here!
						</button>
					</div>
				</div>
				</center>
			</form>
HTML;
?>
		</div>
	</div>
</div>

<footer>

<div class="container">
<hr>Beyond Books Everywhere</hr>
</br>
<p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
<p class="text-right">Copyright &copy; <img class="img-thumbnail" alt="Bootstrap Image Preview" src="images/hackstreetboys.png" height="42" width="42"> The Hackstreet Boys </p>
</div>
</footer>



  </body>
</html>
