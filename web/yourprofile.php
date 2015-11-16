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
Edit Your Profile
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
<?php
   $user_id=$_SESSION["user_id"];
	 $query="SELECT * FROM user_profile where user_id='$user_id'";
   $result=pg_query($query);
   $row=pg_fetch_array($result);
?>





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
<li><a href="bookshelf.php">My Bookshelf</a></li>
<li><a href="my_sold_books.php">My Sold Books</a></li>
<li><a href="addbook.php">Add a Book</a></li>
</ul></li>

	      </ul>

	    </div><!-- /.navbar-collapse -->

	  </div><!-- /.container-fluid -->

	</nav>





<br/><br/><br/><br/>

    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<h3>
				Edit Your Profile <hr/>
			</h3>

			<div class="row">
				<div class="col-md-6">

<div class="verticalLine">


</div>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12" style="background-color:lavender;">
				<div class="form-group">
					 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12" id="profile-pic">
			<?php
			$filename=$row['user_id'].'_dp';
			$filename="pictures/".$filename."*";
			$result1=glob($filename);
			if (!empty($result1))
                        echo '<img style="margin:0 25%;" src="'.$result1[0].'" alt="" class="img-circle img-responsive" id="dp" height=200px width=200px >';
												else
												echo '<img style="margin:0 25%;" src="images/user.png" alt="" class="img-circle img-responsive" id="dp" height=200px width=200px >';
												?>
		</div>
	</div>
</div>
					<form id="imageform" method="post" enctype="multipart/form-data" action='upload-dp-script.php' style="left:5%;">
										<span class="btn btn-default btn-file">
										Browse for your avatar <input type="file" name="images" id="images" /></span>
										<button type="submit"class="btn btn-primary">Change my Avatar</button>
                  </form>
				</div>

		</div>
	</div>
</div>
				</div>
				<div class="col-md-6" style="background-color:lavender;">
					<h3>
						<b>Personel Details</b> <hr/>
					</h3>
					<form class="form-horizontal" role="form" method="post" action="yourprofile-script.php">
						<div class="form-group">
								 <label for="fname" class="col-sm-3 control-label">
								 	First Name:
								 </label>
								  <div class="col-sm-7">
								 	<?php
								 		echo '<input type="text" class="form-control" id="fname" name="fname" placeholder="'.$row['f_name'].'"/>';
								 		?>
								 	</div>
								 <label for="lname" class="col-sm-3 control-label">
								 	Last Name:
								 </label>
								 	<div class="col-sm-7">
								 	<?php
								 		echo '<input type="text" class="form-control" id="lname" name="lname" placeholder="'.$row['l_name'].'"/>';
								 		?>
								 	</div>
							<label for="inputEmail3" class="col-sm-3 control-label">
								Your Email ID:
							</label>
							<div class="col-sm-7">
								 <?php
							        echo '<input type="text" class="form-control" id="inputEmail3" placeholder="'.$row['email_id'].'" readonly/>';
							        ?>
							</div>
						</div>
						</div>
				</div>
<hr/>

			<div class="row">
				<div class="col-md-6">
				</div>
				<div class="col-md-6" style="background-color:lavender;">
					<h3> <b>Change Password</b></h3>
						<h4>To change your password, enter your current password and then the password you desire!</h4> <hr/>

						<div class="form-group">

							<label for="oldpwd" class="col-sm-3 control-label">
								Current Password:
							</label>
							<div class="col-sm-7">
								<input class="form-control" id="oldpwd" name="oldpwd" type="password" placeholder="Current Password" >
							</div>
						</div>
						<div class="form-group" id="newpwddiv"  style="display:none;">
						        <label for="newpwd" class="col-sm-3 control-label">
								New Password:
							</label>
						        <div class="col-sm-7">
						          <input type="password" name="newpwd" placeholder="New Password" id="newpwd"  class="form-control"/>
						        </div>


						</div>

						<div class="form-group">
							<div class="col-sm-offset-4 col-sm-10">
<br>
								<button type="submit" class="btn btn-default">
									Save!
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
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

<script>
    $(document).ready(function(){$('#oldpwd').blur(password_check);});
    function password_check()
    {
      var id=$('#oldpwd').val();
      jQuery.ajax({
        type: "GET",
        url: "password-checker-script.php",
        data: 'pwd='+id,
        cache: false,
        success: function(response)
        {
          if(response==0)
          {
            $("#oldpwd").val("");
            alert("Wrong Password");
          }
          else
          {

            $("#newpwddiv").fadeIn();
          }
        }
      })
    }

    </script>
