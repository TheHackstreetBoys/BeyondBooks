<!doctype html>
<?php
include_once 'db_conn.php';
?>
<html>
<head>
<title>
Set new passsword
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

	  </div><!-- /.container-fluid -->

	</nav>

<div class="container" id="forgot_pd">
<form method="POST" class="form-forgotpass">
<div class="form-group">

<label for="password">Password</label>
<i class="glyphicon glyphicon-lock form-control-feedback" style="position: absolute; padding-top: 4.5%;"></i>
<input type="password" id="password" name="password" class="form-control login-field" required="required">
<input type="hidden" id="email" name="email" class="form-control login-field" value="<?php $email=$_GET['email']; echo  $email; ?>" >
</div>
<button type="submit" class="btn btn-primary" onclick="setnewpassword()">Submit</button>
</form>
</div>

<footer class="generalfooter">
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


<script>
function setnewpassword()
		{
			alert("working");
			var password=$('#password').val();
			var email=$('#email').val();
			jQuery.ajax({
				type: "POST",
				url: "setpassword-script.php",
				data: 'email='+email+'&password='+password,
				cache: false,
				success: function(response)
				{
					if(response==1)
					{
						alert("Password set! You are good to go now.");
						document.location.href="index.php";
					}
			})
		}
</script>

