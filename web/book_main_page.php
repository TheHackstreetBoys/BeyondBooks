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
BeyondBooks Book Page
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

<script type="text/javascript">


function deletebookshelf()
{
	var isbn=$('#isbn').val();
	var userid=$('#userid').val();
	jQuery.ajax({
		type: "POST",
		url: "deletebookshelf-script.php",
		data: "isbn="+isbn+"&userid="+userid,
		cache: false,
		success: function(response)
		{
			if(response==1)
			{
alert("Removed from your Bookshelf");



			}
			else
			{
			alert("It is not in your bookshelf");
			}
		}
	})
}

function insertbookshelf()
{
	var isbn=$('#isbn').val();
	var userid=$('#userid').val();
	jQuery.ajax({
		type: "POST",
		url: "insertbookshelf-script.php",
		data: "isbn="+isbn+"&userid="+userid,
		cache: false,
		success: function(response)
		{
			if(response==1)
			{
				alert("Added to Bookshelf");

			}
			else
			{
				alert("Already Added to your Bookshelf");
			}
		}
	})
}
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
<li><a href="bookshelf.php">My Bookshelf</a></li>
<li><a href="my_sold_books.php">My Sold Books</a></li>
<li><a href="addbook.php">Add Book</a></li>
</ul></li>

</ul>


	    </div><!-- /.navbar-collapse -->

	  </div><!-- /.container-fluid -->

	</nav>

<br/><br/><br/><br/>
<hr/>
  <div class="container-fluid">
	<div class="row">
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-6"><br/>
	<?php

			$isbn = $_GET['isbn'];
			$_SESSION['isbn'] = $isbn;

	$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn WHERE books.isbn = '$isbn'");

	$img = 'books_pics/'.$isbn.'.jpg';
	$result1=glob($img);
	if (!empty($result1))
	echo '<img src="'.$result1[0].'" class="img-responsive" style="width:100px; height:150px">';
	else
	{
			echo '<img src="books_pics/nan.jpg" class="img-responsive" style="width:100px; height:150px">';
	}
					while($row = pg_fetch_array($result))
				{
					echo '<h3><b>'.$row['title'].'</b></h3></br>';
					echo "<h4><b> By :".$row['author']."</h4></b>";

			     }
	?>
</br></br>

<?php

	$result = pg_query("SELECT COUNT(uid) AS total FROM rating WHERE isbn = '$isbn'");
	$result1 = pg_query("SELECT sum(rating) AS totalrating FROM rating WHERE isbn = '$isbn'");
	$row = pg_fetch_array($result);
	$row1 = pg_fetch_array($result1);

if($row['total'] != 0)
{
$starNumber = $row1['totalrating']/$row['total'];
echo "Rating :";
    for($x=1;$x<=$starNumber;$x++) {
        echo '<img src="images/full.png" />';
    }
    if (strpos($starNumber,'.')) {
        echo '<img src="images/half.png" />';
        $x++;
    }
    while ($x<=5) {
        echo '<img src="images/zero.png" />';
        $x++;
    }
}
else
{
$starNumber = 0;
echo "Rating :";
    for($x=1;$x<=$starNumber;$x++) {
        echo '<img src="images/full.png" />';
    }
    if (strpos($starNumber,'.')) {
        echo '<img src="images/half.png" />';
        $x++;
    }
    while ($x<=5) {
        echo '<img src="images/zero.png" />';
        $x++;
    }
}
?>

<br/><br/>
<link href='rating.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
<script>

		$(document).ready(function(){
			$('#rating_panel>img').click(function(e){
				var isbn = '<?php echo $isbn; ?>';
				var imgindex	=	$(this).index() + 1;
				var ratingpanel	=	$(this).closest('div');
				var pollid		=	ratingpanel.attr('data-pollid');
				var israted		=	ratingpanel.attr('data-rated');
				if(israted == 1){
					alert('You have already voted!');
					return false;
				}else{
					ratingpanel.attr('data-rated',1);
				}

				$('#starloader').show();
				for( i=0;i<imgindex; i++){
					var imgobj = $("#rating_panel>img:eq( "+i+" )" );
					var img = 'images/full.png';
					imgobj.attr('src',img);

				}
				$.ajax({
					url:'add_rating.php',
					data:'rated='+imgindex+'&isbn='+isbn,
					success:function(){
						$('#starloader').hide();
						location.reload();
					}
				});



			});
		});
</script>


Your Rating:
<div id="rating_panel" name="rating_panel" data-pollid="1" data-rated="0">
<img src="images/zero.png" /> <img src="images/zero.png" /> <img src="images/zero.png" /> <img src="images/zero.png" /> <img src="images/zero.png" /><div id="starloader">
</div>
				</div>

				<br/><br/>
				<input type="hidden" id="isbn" value="<?php echo $isbn; ?>">
				<input type="hidden" id="userid" value='<?php echo $_SESSION["user_id"]; ?>'>
				<input type="button" id="bookshelfbtn" class="btn btn-default btn-primary" value="+Add to my Bookshelf" onclick="insertbookshelf()"> </br></input>
<input type="button" id="bookshelfbtn1" class="btn btn-default btn-danger" value="-Remove from my Bookshelf" onclick="deletebookshelf()" style="padding-top:5px; margin-top:5px;"></input>

				</div>
				<div class="col-md-6">
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<h3>
				Rating and Reviews
			</h3>


<?php

$uid = $_SESSION["user_id"];
$review = pg_escape_string($_POST['content']);
$isbn = $_GET['isbn'];

  //$result = mysql_safe_query('SELECT * FROM teachers WHERE username = %s ', $username);
   // $row = mysql_fetch_assoc($result);

if (!empty($review))
{
$result = pg_query("SELECT * FROM review WHERE uid='$uid' AND isbn = '$isbn'");
if(pg_num_rows($result)!=0 )
{
 $query = pg_query("UPDATE review SET review = '$review' WHERE uid = '$uid' AND isbn = '$isbn'" );
}
else
{
 $query1 = pg_query("INSERT INTO review (uid, review, isbn) VALUES ('$uid', '$review', '$isbn') " );
}

}

?>

	<?php
		$num_rec_per_page=2;

if (isset($_GET["page"])) { $page  = $_GET["page"]; } else { $page=1; };
$start_from = ($page-1) * $num_rec_per_page;

	$result = pg_query("SELECT * FROM review WHERE isbn = '$isbn' LIMIT $num_rec_per_page OFFSET $start_from");

			if(!pg_num_rows($result)) {
							echo '<p>No forums is Created Yet.</p>';
						     }
			else {

					echo "<br/><b>Reviews of the Users:</b><br/><br/>";

					while($row = pg_fetch_array($result))
				{
					$filename1=$row['uid'].'_dp';
					$filename1="pictures/".$filename1."*";
					$result2=glob($filename1);
					if (!empty($result2))
					echo '<img src="'.$result2[0].'"class="img-circle" style="width: 50px">';
					else
					echo '<img src="images/user.png"class="img-circle" style="width: 50px">';
					$uid = $row['uid'];
					$sql1 = "SELECT * FROM rating WHERE isbn = '$isbn' AND uid = '$uid'";
					$rs_result1 = pg_query($sql1); //run the query
					$row10 =  pg_fetch_array($rs_result1);

					$namequery=pg_query("SELECT * from user_profile where user_id='$uid'");
					$namequery_answer=pg_fetch_array($namequery);
					if($namequery_answer['isfaculty']=='t')
					echo '<b> Prof. '.$namequery_answer['f_name'].' '.$namequery_answer['l_name'].'</b> - '.$row10['rating'].'/5<br/>';
					else
					echo '<b> '.$namequery_answer['f_name'].' '.$namequery_answer['l_name'].'</b> - '.$row10['rating'].'/5<br/>';
					$body = $row['review'];

					echo "\r\r".nl2br($body).'<br><br/>';


$sql = "SELECT * FROM review WHERE isbn = '$isbn'";
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);




			     }
echo "<a href='book_main_page.php?isbn=$isbn&page=1'>".'Prev-'."</a> "; // Goto 1st page

for ($i=1; $i<=$total_pages; $i++) {
            echo "<a href='book_main_page.php?isbn=$isbn&page=".$i."'>".$i."</a> ";
};
echo "<a href='book_main_page.php?isbn=$isbn&page=$total_pages'>".'-Next'."</a> "; // Goto last page

					}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
		echo <<<HTML

 <form class="form-horizontal" role="form" method="post" action="">
								<div class="form-group">


									<div class="col-sm-10">

				<textarea class="form-control" name = "content" rows = "1" id="inputEmail3" type="text" required="required"> Add Your Review</textarea>

									</div>
								</div>

<div class="form-group">


									<div class="col-sm-4">

				<input class="form-control" type ="hidden" value = "$isbn" name = "isbn" id="inputEmail3" type="text"/>

									</div>
								</div>



	<div class="form-group">


									<div class="col-sm-4">

				<input class="form-control btn-primary" type ="submit" name = "Submit" value =" Add Review" id="inputEmail3" type="text"/>

									</div>
								</div>



							</form>
HTML;

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';



					?>







			<p>

			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<h3>
				About the Book
			</h3>
			<p>
				<?php
				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
			$isbn = $_GET['isbn'];
			$_SESSION['isbn'] = $isbn;

	$result = pg_query("SELECT * FROM books JOIN author ON books.isbn = author.isbn WHERE books.isbn = '$isbn'");



			if(!pg_num_rows($result)) {
							echo '<p>No Book is available.</p>';
						     }
			else {

					while($row = pg_fetch_array($result))
				{
					echo '<b> Title :</b> '.$row['title'].'<br/>';
					echo "<b> Authors :</b><em>".$row['author']."</em><br/>";
			    echo '<b> Publication: </b><em>'.$row['publisher'].'</em><br/>';
					echo '<b> Description: </b><em>'.$row['description'].'</em><br/>';
					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
			     }
					}

					?>

			</p>
		</div>
		<div class="col-md-6">
			<h3>
				Any User Selling Book
			</h3>
			<?php

		$num_rec_per_page=1;

if (isset($_GET["page1"])) { $page  = $_GET["page1"]; } else { $page=1; };
$start_from = ($page-1) * $num_rec_per_page;

	$result = pg_query("SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE single_sell.isbn = '$isbn' AND pbase.prodid = single_sell.prodid LIMIT $num_rec_per_page OFFSET $start_from");

			if(!pg_num_rows($result)) {
							echo '<p> No Seller is available.</p>';
						     }
			else {

					echo "<br/><b>Available Seller:</b><br/>";

					echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';

					while($row = pg_fetch_array($result))
				{
					$filename1=$row['sellerid'].'_dp';
					$filename1="pictures/".$filename1."*";
					$result2=glob($filename1);
					if (!empty($result2))
					echo '<img src="'.$result2[0].'"class="img-circle" style="width: 50px">';
					else
					echo '<img src="images/user.png"class="img-circle" style="width: 50px">';
					$uid = $row['sellerid'];
					$sql1 = "SELECT * FROM rating WHERE isbn = '$isbn' AND uid = '$uid'";
					$rs_result1 = pg_query($sql1); //run the query
					$row10 =  pg_fetch_array($rs_result1);

					$namequery=pg_query("SELECT * from user_profile where user_id='$uid'");
					$namequery_answer=pg_fetch_array($namequery);
					if($namequery_answer['isfaculty']=='t')
					echo '<b>Seller:&nbsp; Prof. '.$namequery_answer['f_name'].' '.$namequery_answer['l_name'].'</b><br/>';
					else
					echo '<b>Seller:&nbsp;'.$namequery_answer['f_name'].' '.$namequery_answer['l_name'].'</b><br/>';
					$body = $row['review'];


					echo 'Price:&nbsp;'.$row['price'].'<br/>';
					echo 'Age of the book:&nbsp;'.$row['age'].'<br/>';
					$body = $row['description'];
					echo "Description:&nbsp;".nl2br($body).'';
					$user_id = $_SESSION["user_id"];
		echo "<a href='mailproceed.php?isbn=".$row['isbn']."&sellerid=".$row['sellerid']."&user_id=".$user_id."&prodid=".$row['prodid']."'><br/>Show Interest</a><br/>";
				
$sql = "SELECT * FROM pbase JOIN single_sell ON single_sell.prodid = pbase.prodid WHERE single_sell.isbn = '$isbn' AND pbase.prodid = single_sell.prodid";
$rs_result = pg_query($sql); //run the query
$total_records = pg_num_rows($rs_result);  //count number of records
$total_pages = ceil($total_records / $num_rec_per_page);


			     }


echo "<br/><a href='book_main_page.php?isbn=$isbn&page1=1'>".'Prev-'."</a> "; // Goto 1st page

for ($i=1; $i<=$total_pages; $i++) {
            echo "<a href='book_main_page.php?isbn=$isbn&page1=".$i."'>".$i."</a> ";
};
echo "<a href='book_main_page.php?isbn=$isbn&page1=$total_pages'>".'-Next'."</a> "; // Goto last page


}

				echo '<hr style="height:1px; border:none; color:rgb(60,90,180); background-color:rgb(60,90,180);">';
					?>


		</div>
	</div>
</div>


<footer>
<hr />
<div class="container">
<hr>Beyond Books Everywhere</hr>
</br>
<p class="text-left"><button type="button" class="btn btn-primary">Click here to Download our android app</button></p>
<p class="text-right">Copyright &copy; BeyondBooks</p></div>
</footer>
