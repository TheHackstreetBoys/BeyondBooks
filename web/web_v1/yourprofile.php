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




<script type="text/javascript" >
$(document).ready(function() {

            $('#images').on('change', function()      {
                 $("#profile-pic").html('');
          $("#profile-pic").html('<img src="icons/loader.gif" alt="Uploading...." style="margin:0 25%;"/>');
      $("#imageform").ajaxForm({
            target: '#profile-pic'
    }).submit();

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

		<li><form action="" class="search-form">
                <div class="form-group has-feedback" id="search">

            		<input type="text" class="form-control" name="search" id="search1" placeholder="search">
              		<span class="glyphicon glyphicon-search form-control-feedback"></span>
            	</div>
            </form></li>

	        <li><a href="homepage.php">Home</a></li>

	        <li><a href="#about">About</a></li>
	        <li><a href="logout-script.php">Log Out <span class="glyphicon glyphicon-log-out"></span></li>

		<li class="dropdown"><a href="#" data-toggle="dropdown"  class="dropdown-toggle">
			<?php
			$filename=$row['user_id'].'_dp';
			$filename="pictures/".$filename."*";
			$result1=glob($filename);
			if (!empty($result1))
			echo '<img src="'.$result1[0].'"class="img-circle" style="width: 50px">';
			else
				echo '<img src="images/user.png"class="img-circle" style="width: 50px">';
				?>
		</a>

<ul class="dropdown-menu">
<li><a href="#">My uploads</a></li>
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
                    Upload your image <input type="file" name="images" id="images" />
										<input type="submit">
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
								 		echo '<input type="text" class="form-control" id="fname" placholder="'.$row['f_name'].'"/>';
								 		?>
								 	</div>
								 <label for="lname" class="col-sm-3 control-label">
								 	Last Name:
								 </label>
								 	<div class="col-sm-7">
								 	<?php
								 		echo '<input type="text" class="form-control" id="lname" placholder="'.$row['l_name'].'"/>';
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
			</div>
<hr/>

			<div class="row">
				<div class="col-md-6">
				</div>
				<div class="col-md-6" style="background-color:lavender;">
					<h3> <b>Change Password</b></h3>
						<h4>To change your password, enter your current password and then the password you desire!</h4> <hr/>

	<form class="form-horizontal" role="form">
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
<p class="text-right">Copyright &copy; <img class="img-thumbnail" alt="Bootstrap Image Preview" src="images/hackstreetboys.png" height="42" width="42"> The Hackstreet Boys </p>
</div>
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
