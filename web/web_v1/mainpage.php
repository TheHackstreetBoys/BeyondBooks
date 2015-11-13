<!doctype html>
<html>
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





<br/><br/><br/>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<div class="page-header">
						<h1>
							<br/>New Uploads! 
						</h1>
			<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">

<?php
		
												$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

			$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn LIMIT 6");



			if(!pg_num_rows($result)) {
							echo '<p>No Book is available.</p>';
						     } 
			else {	
			
					while($row = pg_fetch_array($result))
				{
			echo "<div class='col-md-3'><br/>
<a href='book_main_page.php?isbn=".$row['isbn']."'>".$row['title']."</a>
<br/>
By:".$row['author']."<br/>
".$row['publisher']."</div>
";				


		
			     }
					}

					?>
					

					</div>
					
				</div>
			</div>
			<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">
			<div class="row">
				<div class="col-md-6">
					<div class="page-header">
						<h1>
							Popular/Top Rated Books!
						</h1>
					</div>
					<?php
		
												$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

			$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn LIMIT 3");



			if(!pg_num_rows($result)) {
							echo '<p>No Book is available.</p>';
						     } 
			else {	
			
					while($row = pg_fetch_array($result))
				{
			echo "<img src='http://www.librarything.com/devkey/KEY/medium/isbn/".$row['isbn']."'alt='Image is not available' > <br/> ";
					echo '<b>'.$row['title'].'<br/></b>';
					echo "<em>".$row['author']."</em><br/>";
					echo '<em>'.substr($row['description'], 0, 200).'</em><br/>';
					echo '<em>'.$row['publisher'].'</em><br/>';
					echo "<a href='book_main_page.php?isbn=".$row['isbn']."'>Click Here</a>";

			
					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';	
			     }
					}

					?>
					

				</div>
				<div class="col-md-6">
					<div class="page-header">
						<h1>
							Top Discussions on Forum!
						</h1>
					</div>
					<?php
		
												$dbconn=null;
global $dbconn;
$dbconn=pg_connect("host=localhost dbname=BeyondBooks user=postgres password=password") or die("could not connect!!!");

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
<hr />
<div class="container">
<hr>Beyond Books Everywhere</hr>
</br>
<p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
<p class="text-right">Copyright &copy; Your Company 2014</p>
</div>
</footer>



