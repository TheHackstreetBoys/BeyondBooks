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
Discussion Forum
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

<br/><br/><br/><br/>
 <div class="container-fluid">
	<div class="row">

		<div class="col-md-12"><h1>
<br/>
<b>Discussion Forums</b>
<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">
</h1>


			<div class="row">

				<div class="col-md-8">
					<div class="row">
						<div class="col-md-12">




<div class="row">
						<div class="col-md-12">

							<div class="row">
									<div class="col-md-12">

								</div>
							</div>
<?php
												$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");


//echo "<em>Design Project <b>Kushal Jangid</b></em>";


$str = $_GET['subject'] ;
$v  = strpos("$str","I");
$c = substr("$str",0,$v);
$username = substr("$str",$v-1);

session_start();


$result = pg_query("SELECT * FROM posts ORDER BY date DESC");


if(!pg_num_rows($result)) {
	echo '<p>No forums is Created Yet.</p>';
} else {
	while($row = pg_fetch_array($result)) {
		echo '<h2>'.$row['title'].'</h2><br/>';
		$body = substr($row['body'], 0, 20);
		echo nl2br($body).'...<br/>';
		echo '<a href="forumview.php?id='.$row['id'].'+'.$_SESSION['mycourse'].'">Read More</a> | ';
		echo '<a href="forumview.php?id='.$row['id'].'#comments">'.$row['num_comments'].' comments</a>';
		echo '<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';

	}
}

echo <<<HTML

<a style='color: #CC0000'href="addquestion.php">+ Add Your Question Here</a><br/>
<br/><br/>

HTML;

?>



						</div>
					</div>
				</div>

	</div>
					</div>
<hr style="height:3px; border:none; color:rgb(60,60,60); background-color:rgb(60,60,60);">




<div class="col-md-4">


					<h3>
						Top Rated Discussions <hr style="height:1px; border:none; color:rgb(60,60,60); background-color:rgb(60,90,180);">
					</h3>


					<?php

			$result = pg_query("SELECT * FROM posts ORDER BY num_comments DESC LIMIT 4");

			if(!pg_num_rows($result)) {
							echo '<p>No forums is Created Yet.</p>';
						     }
			else {

					while($row = pg_fetch_array($result))
				{
					echo '<h2>'.$row['title'].'</h2><br/>';
					$body = substr($row['body'], 0, 10);
					echo nl2br($body).'...<br/>';
					echo '<a href="forumview.php?id='.$row['id'].'">Read More</a> | ';
					echo '<a href="forumview.php?id='.$row['id'].'#comments">'.$row['num_comments'].' comments</a>';
					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
			     }
					}

					?>




				</div>
			</div>
		</div>
	</div>
</div>



<footer>
<div class="container">
<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">Beyond Books Everywhere</hr>
</br>
<p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
<p class="text-right">Copyright &copy; <img class="img-thumbnail" alt="Bootstrap Image Preview" src="images/hackstreetboys.png" height="42" width="42"> The Hackstreet Boys
</div>
</footer>
