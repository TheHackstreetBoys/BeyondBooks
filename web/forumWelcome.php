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
Welcome to Forum
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
    url: "searchforum.php",
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
	margin-top:0cm;

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
<input type="text" class="form-control search"  id="searchid" placeholder="Search for Forums" />
	<span class="glyphicon glyphicon-search form-control-feedback" style="padding-top: 15%; color: #3596e0;"></span>
<div id="result"> </div>
</div>
';


$pre = 1;
include("html.inc");
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

//echo "<em>Design Project <b>Kushal Jangid</b></em>";

$num_rec_per_page=4;

if (isset($_GET["page1"])) { $page  = $_GET["page1"]; } else { $page=1; };
$start_from = ($page-1) * $num_rec_per_page;

$str = $_GET['subject'] ;
$v  = strpos("$str","I");
$c = substr("$str",0,$v);
$username = substr("$str",$v-1);

session_start();


$result = pg_query("SELECT * FROM question_forum ORDER BY ts DESC LIMIT $num_rec_per_page OFFSET $start_from");


if(!pg_num_rows($result)) {
	echo '<p>No forums is Created Yet.</p>';
} else {
	while($row = pg_fetch_array($result)) {

	$qid = $row['qid'];

		$result1 = pg_query("SELECT COUNT(*) AS num FROM forum_replies WHERE qid = '$qid' ");
		$row1 = pg_fetch_array($result1);

		echo '<h2>'.$row['title'].'</h2><br/>';
		$body = substr($row['content'], 0, 20);
		echo nl2br($body).'...<br/>';
		echo '<a href="forumview.php?qid='.$row['qid'].'">Read More</a> | ';
		echo '<a href="forumview.php?qid='.$row['qid'].'#comments">'.$row1['num'].' comments</a>';
		echo '<hr style="height:3px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';



$sql = "SELECT * FROM question_forum ORDER BY ts ";
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);

}
}

echo <<<HTML

<a style='color: #FFFFFF'href="addquestion.php"><button class="btn btn-primary">+ Add Your Question Here</button></a><br/>
<br/><br/>

HTML;

echo "<a href='forumWelcome.php?page1=1'>".'Prev-'."</a> "; // Goto 1st page

for ($i=1; $i<=$total_pages; $i++) {
            echo "<a href='forumWelcome.php?page1=".$i."'>".$i."</a> ";
};
echo "<a href='forumWelcome.php?page1=$total_pages'>".'-Next'."</a> "; // Goto last page



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


					$num_rec_per_page=4;

					if (isset($_GET["page2"])) { $page  = $_GET["page2"]; } else { $page=1; };
					$start_from = ($page-1) * $num_rec_per_page;



$result = pg_query("SELECT * FROM question_forum ORDER BY (SELECT COUNT(*) AS num FROM forum_replies WHERE qid = question_forum.qid ) LIMIT $num_rec_per_page OFFSET $start_from");

			if(!pg_num_rows($result)) {
							echo '<p>No forums is Created Yet.</p>';
						     }
			else {

					while($row = pg_fetch_array($result))
				{
			$qid = $row['qid'];

		$result1 = pg_query("SELECT COUNT(*) AS num FROM forum_replies WHERE qid = '$qid' ");
		$row1 = pg_fetch_array($result1);

					echo '<h2>'.$row['title'].'</h2><br/>';
					$body = substr($row['content'], 0, 10);
					echo nl2br($body).'...<br/>';
					echo '<a href="forumview.php?qid='.$row['qid'].'">Read More</a> | ';
					echo '<a href="forumview.php?qid='.$row['qid'].'#comments">'.$row1['num'].' comments</a>';
					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';


					$sql = "SELECT * FROM question_forum ORDER BY (SELECT COUNT(*) AS num FROM forum_replies WHERE qid = question_forum.qid)";
					$rs_result = pg_query($sql); //run the query
					$total_records = pg_num_rows($rs_result);  //count number of records
					$total_pages = ceil($total_records / $num_rec_per_page);

			     }
					 echo "<a href='forumWelcome.php?page2=1'>".'Prev-'."</a> "; // Goto 1st page

					 for ($i=1; $i<=$total_pages; $i++) {
					             echo "<a href='forumWelcome.php?page2=".$i."'>".$i."</a> ";
					 };
					 echo "<a href='forumWelcome.php?page2=$total_pages'>".'-Next'."</a> "; // Goto last page

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
<p class="text-right">Copyright &copy; BeyondBooks</p></div>
</footer>
