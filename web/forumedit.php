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
		<li><a href="addbook.php">Add a book</a></li>
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
if(!empty($_POST))
{
$title = $_POST['title'];
$content = $_POST['content'];
$htag = $_POST['htag'];

$qid = $_GET['qid'];
$result1 = pg_query("UPDATE qtags SET htag = '$htag' WHERE qid='$qid'" );
$result = pg_query("UPDATE question_forum SET title = '$title', content = '$content', ts = CURRENT_TIMESTAMP WHERE qid='$qid'" );
//redirect to forumview.php?qid=$qid;
}

$id1 = $_GET['qid'];
$result = pg_query("SELECT * FROM question_forum WHERE qid='$id1'" );

if(!pg_num_rows($result)) {
	echo 'Post #'.$_GET['qid'].' not found';
	exit;
}

$row = pg_fetch_array($result);

$result1 = pg_query("SELECT * FROM qtags WHERE qid='$id1'" );

if(!pg_num_rows($result1)) {
	echo 'Post #'.$_GET['qid'].' not found';
	exit;
}

$row1 = pg_fetch_array($result1);


echo <<<HTML

			<form class="form-horizontal" role="form" method="post" action ="" >
				<div class="form-group">

					<label for="inputEmail3" class="col-sm-4 control-label">
						Enter the title :
					</label>
					<div class="col-sm-5">
						<input class="form-control" value="{$row['title']}" name = "title" id="inputEmail3" type="text" required="required">
					</div>
				</div>
				<div class="form-group">

					<label for="inputPassword3" class="col-sm-4 control-label">
						Enter the Content of the Question :
					</label>
					<div class="col-sm-5">
						<textarea class="form-control" name = "content" id="inputPassword3" rows = "4" type="text">{$row['content']} </textarea>
					</div>
				</div>

				<div class="form-group">

					<label for="inputPassword3" class="col-sm-4 control-label">
						Enter the Name of the Hash Tag :
					</label>
					<div class="col-sm-5">
						<input class="form-control" name= "htag" value="{$row1['htag']}"  id="inputPassword3" type="text" required="required" >
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
<p class="text-right">Copyright &copy; BeyondBooks</p></div>
</footer>



  </body>
</html>
