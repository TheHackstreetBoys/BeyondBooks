<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Demo - Star rating in php using ajax and jquery</title>
		<link href='rating.css' rel='stylesheet' type='text/css'/>
		<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="rating.js"></script>
	</head>
	<body>
		<div id="container">
				<p>
					Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu.
				</p>
				<h3 id="rating_title">Rate This Article</h3>
				<div id="rating_panel" data-pollid="1" data-rated="0">
					<img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /> <img src="zero.png" /><div id="starloader"></div>
				</div>
		</div>
	</body>
</html>