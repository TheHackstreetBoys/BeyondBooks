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
Book Description
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
</head>

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





<body>
<!--
                                                                               -->

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
																																							     $.ajax({<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
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
																																									 </ul></li>

																																									 	      </ul>

																																									 	    </div><!-- /.navbar-collapse -->

																																									 	  </div><!-- /.container-fluid -->

																																									 	</nav>



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
																																							 include("html.inc");
																																							 ?></li>


																																							 	        <li><br/><a href="homapage.php">Home</a></li>

																																							 	        <li><br/><a href="buy_sell.php">Buy/Sell</a></li>
																																							 					<li><br/><a href="forum.php">Forum</a></li>
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
																																							 </ul></li>

																																							 	      </ul>

																																							 	    </div><!-- /.navbar-collapse -->

																																							 	  </div><!-- /.container-fluid -->

																																							 	</nav>






<<!--Here starts our welcome to Discussion Forum Page! -->>


<br/><br/><br/>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="page-header">
				<h1>
				 Here is your book!
				</h1>
			</div>
			<div class="row">
				<div class="col-md-6">
					<img alt="Bootstrap Image Preview" src="http://lorempixel.com/140/140/">
					<p class="text-left"><button type="button" class="btn btn-primary">+Add to WishList
					</button></p>


					<div class="row">
						<div class="col-md-12">
							<div class="page-header">
								<h1>
									ISBN
								</h1>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="page-header">
								<h1>
									Books' Name
								</h1>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="page-header">
								<h1>
									Authors' Name
								</h1>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="page-header">
								<h1>
									Ratings
								</h1>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<dl>
						<dt>
							Description of the Book:
						</dt>
						<dd>
							A description list is perfect for defining terms.
						</dd>
						<dt>
							Euismod
						</dt>
						<dd>
							Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.
						</dd>
						<dd>
							Donec id elit non mi porta gravida at eget metus.
						</dd>
						<dt>
							Malesuada porta
						</dt>
						<dd>
							Etiam porta sem malesuada magna mollis euismod.
						</dd>
						<dt>
							Felis euismod semper eget lacinia
						</dt>
						<dd>
							Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.
						</dd>
					</dl>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail First" src="http://lorempixel.com/output/people-q-c-600-200-1.jpg">

					</div>
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail Second" src="http://lorempixel.com/output/city-q-c-600-200-1.jpg">

					</div>
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail Third" src="http://lorempixel.com/output/sports-q-c-600-200-1.jpg">

					</div>
				</div>
			</div>
			<div class="jumbotron">
				<h2>
					Interested in Buying!
				</h2>
				<p>
					Send your RTB to the seller.
				</p>
				<p>
					<a class="btn btn-primary btn-large" href="#">Send Your Request</a>
				</p>
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
<p class="text-right">Copyright &copy; BeyondBooks</p>
</div>
</footer>

</body>
</html>
