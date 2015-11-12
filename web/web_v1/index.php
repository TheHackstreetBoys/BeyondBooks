<!DOCTYPE HTML>
<?php
include_once 'db_conn.php';
session_start();
if (isset($_SESSION["user_id"]))
{
	$user_id=$_SESSION["user_id"];
	$result1=pg_query("SELECT f_name from user_profile where user_id='$user_id'");
	$num1=pg_num_rows($result1);
	if($num1!=0)
	{
			header('Location: homepage.php');
	}
}
?>
<HTML>
<HEAD>
<TITLE>
Index Page
</TITLE>

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

<script>

function checkstudent()
		{
			var email=$('#email').val();
			var password=$('#password').val();
			jQuery.ajax({
				type: "POST",
				url: "login-script.php",
				data: "email="+email+"&password="+password,
				cache: false,
				success: function(response)
				{
					if(response==1)
					{
						document.location.href="homepage.php";
					}
					else if(response==3)
					{
						$("#email").val("");
						$("#password").val("");
						alert("It seems like you are invisible! Please sign up first!");
					}
					else
					{
						$("#email").val("");
						$("#password").val("");
						alert("Whoops! Seems like wrong password!");
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

	      <a class="navbar-brand" href="#">Beyond Books</a>

	    </div>



	    <div class="collapse navbar-collapse" id="navbar-collapse-main">

	      <ul class="nav navbar-nav navbar-right">

	        <li><a href="#home">Home</a></li>

	        <li><a href="#about">About</a></li>

	        <li><a href="about_us.php">Our Team</a><li>

		<li> <a data-toggle="modal" href="#login-modal">Login<span class="glyphicon glyphicon-log-in"></span></a></li>
	      </ul>

	    </div><!-- /.navbar-collapse -->

	  </div><!-- /.container-fluid -->

	</nav>

<!-- Navigation bar ends                                                -->








<!-- Pop up Modal                                                      -->

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
    		<div class="modal-content">
	      		<div class="modal-header login_modal_header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h2 class="modal-title" id="myModalLabel">Login to Your Account</h2>
	      		</div>
      		<div class="modal-body login-modal">
      			<br/>
	        	<div class='modal-body-center'>
	        	 	<div class="form-group">
					<form action="login-script.php" method="POST">
				      	<input type="email" id="email" name="email" placeholder="Enter Email-ID"  class="form-control login-field" required="required">
				      	<i class="fa fa-user login-field-icon"></i>
		  		</div>
				<div class="form-group">
		            	  <input type="password" id="password" name="password" placeholder="Enter Password" class="form-control login-field" required="required">
		              		<i class="fa fa-lock login-field-icon"></i>


		        	<button type="button" class="btn btn-success modal-login-btn" onclick="checkstudent()">Sign in</button>
		            	<a href="forgot-password.php" class="login-link text-center">Lost your password</a>
	        		</form>

				</div>


	</div>
    	</div>
</div>
</div>
</div>

<!-- Pop Up Modal ends                                                 -->



	<!-- first section - Home -->

	<div id="home" class="home">

	  <div class="text-vcenter">

	    <h1>Beyond Books</h1>

	    <h3>Buy/Sell, Discuss And Rate</h3>

	  </div>

	</div>

	<!-- /first section -->



<div id="about" class="pad-section">

  <div class="container">

	    <div class="row">

	      <div class="col-sm-6">

	      </div>

	      <div class="col-sm-6 text-center">

	        <h2>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec in sem cras amet.</h2>

	        <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed interdum metus et ligula venenatis, at rhoncus nisi molestie. Pellentesque porttitor elit suscipit massa laoreet metus.</p>

	      </div>

	    </div>

	  </div>
	</div>






	<footer>

	  <hr />

	  <div class="container">

	    <p class="text-right">Copyright &copy; BeyondBooks</p>

	  </div>

	</footer>

</body>
</html>
