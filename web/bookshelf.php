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
<input type="text" class="form-control search"  id="searchid" placeholder="Search for Books" />
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
<li><a href="my_sold_books.php">My Sold Books</a></li>
<li><a href="addbook.php">Add a book</a></li>
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






	<div class="bookshelf">
		<?php
			$class1="book book-green";
			$class2="book book-umber";
			$class3="book book-blue";
			$class4="book book-springer";
			$user_id=$_SESSION["user_id"];
			$query_bookshelf="SELECT * from book_shelf where user_id='$user_id'";
			$answer=pg_query($query_bookshelf);
			$rows=pg_num_rows($answer);
			if($rows==0)
			{
				echo '<h3 style="color:blue;">Looks like you are not an avid reader! </h3>';
			}
			while($rows=pg_fetch_array($answer))
			{
				$books=pg_query("SELECT * from books where isbn='$rows[isbn]'");
				$bookname=pg_fetch_array($books);
				$var=rand(1,4);
				if($var==1)
				echo '<div class="'.$class1.'">';
				else if($var==2)
				echo '<div class="'.$class2.'">';
				else if($var==3)
				echo '<div class="'.$class3.'">';
				else
				echo '<div class="'.$class4.'">';
				echo '<h2><a href="book_main_page.php?isbn='.$rows['isbn'].'&page=1">'.$bookname['title'].'</a></h2>';
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
  <p class="text-right">Copyright &copy; BeyondBooks</p>
  </div>
  </footer>

</body>
</html>
