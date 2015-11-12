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
		<li class="dropdown"><a href="#" data-toggle="dropdown"  class="dropdown-toggle"><img src="/var/www/html/BeyondBooks/web/images/user.png" class="img-circle" style="width: 50px"></a>

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


							<?php
$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");
$id = $_GET['id'];
$result = pg_query("SELECT * FROM posts WHERE id= '$id'");

if(!pg_num_rows($result)) {
	echo 'Post #'.$_GET['id'].' not found';
	exit;
}



	/*$result1 = mysql_safe_query('SELECT username FROM comments ');
	$row1 = mysql_fetch_assoc($result1);

	foreach ($row1 as $date) {
    echo $date['username'];
}*/


$row = pg_fetch_array($result);
	session_start();

$str = $_GET['id'] ;
$v  = strpos("$str"," ");
$c = substr("$str",$v+1);

    $username   = $_SESSION['current'];
    $mycourse = $_SESSION['mycourse'];

    $user = $row['tname'];
$result1 = pg_query("SELECT * FROM user_profile WHERE user_id = '$user'" );

if(!pg_num_rows($result1))
{
}
$row1 = pg_fetch_array($result1);

echo '<h2>'.$row['title'].'</h2><br/>';
echo 'By: <em>'.$row1['f_name'].' '.$row1['l_name'].'</em><br/>';
echo '<em>Posted '.date('F j<\s\up>S</\s\up>, Y', $row['date']).'</em><br/>';
echo nl2br($row['body']).'<br/>';
echo '<a href="forumedit.php?id='.$_GET['id'].'">Edit</a> | <a href="forumdelete.php?id='.$_GET['id'].'">Delete</a> ';
echo ' | <a href="forumWelcome.php">View All</a>';
$id = $_GET['id'];
echo '<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
$result = pg_query("SELECT * FROM reply WHERE post_id = '$id' ORDER BY date DESC LIMIT 4" );
echo '<ul id="comments">';
echo '<div class="row">
						<div class="col-md-12">
							<h3>
								<b> Comments : </b>
<hr style="height:1px; border:none; color:rgb(60,60,60); background-color:rgb(60,60,60);">
							</h3>
</div>
</div>';
while($row = pg_fetch_array($result)) {
	echo '<li id="post-'.$row['id'].'">';
	echo (empty($row['website'])?'<strong>'.$row['name'].'</strong>':'<a style="color: blue" href="#" target="_blank">'.$row['name'].'</a>');
	echo '<br/><small>'.date('j-M-Y g:ia', $row['date']).'</small><br/>';
	echo nl2br($row['content']);


		$result2 = pg_query('SELECT COUNT(plike) AS likes FROM commentss WHERE rollno = %s && id = %s', $row['rollno'], $row['id']);

    if(!pg_num_rows($result2))
    {
    }
    $row2 = pg_fetch_array($result2);

	echo '<br/> <a href = "please.php?id='.$row['id'].'"> <img src = "like.png" title = "LIKE" height = "20px">  </a>' . $row2['likes'];
	echo '</li><br/>';
		echo '</li><br/>';


}
echo '</ul>';

$str = $_GET['id'];

echo <<<HTML

<div class="row">
						<div class="col-md-12">

							<div class="row">
									<div class="col-md-12">
									<button type="button" class="btn btn-success active btn-block">
										Load More
									</button>
								</div>
							</div>
							<h3>
								<b>Add Your Comment :</b>
							</h3>
							<form class="form-horizontal" role="form" method="post" action="forumcommentadd.php?id={$_GET['id']}">
								<div class="form-group">

									<label for="inputEmail3" class="col-sm-2 control-label">

									</label>
									<div class="col-sm-10">

								<textarea class="form-control" name = "content" rows = "4" id="inputEmail3" type="text"> </textarea>

									</div>
								</div>


								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">

										<button type="submit" class="btn btn-default">
											Post Comment
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

</form>
HTML;

?>
						</div>
					</div>
<hr style="height:3px; border:none; color:rgb(60,60,60); background-color:rgb(60,60,60);">

				<div class="col-md-4">
					<h3>
						Recently Created Discussions <hr style="height:1px; border:none; color:rgb(60,60,60); background-color:rgb(60,90,180);">
					</h3>


					<?php

$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

			$result = pg_query("SELECT * FROM posts ORDER BY date DESC LIMIT 3");

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
