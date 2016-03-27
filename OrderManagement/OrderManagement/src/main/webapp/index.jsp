<html>

<head>

<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

</head>

<body>
	<jsp:include page="/WEB-INF/views/includes/header.jsp" />

	<div id="navBar">
		<ul class="nav nav-pills">
			<li role="presentation" nav="home" class="active"><a
				href="#search">Home</a></li>
			<li role="presentation" nav="profile" id="link_profile"><a
				href="#profile">Profile</a></li>
			<li role="presentation" nav="messages"><a href="#msgs">Messages</a></li>
		</ul>
	</div>

	<div id="searchBar">
		<form class="form-inline">
			<div class="form-group">
				<label for="isbn_input">ISBN</label> <input type="text"
					class="form-control" id="isbn_input" placeholder="ISBN123456">
			</div>
			<div class="form-group">
				<label for="title_input">Title</label> <input type="text"
					class="form-control" id="title_input" placeholder="Book Title">
			</div>
			<div class="form-group">
				<label for="authors_input">Authors</label> <input type="text"
					class="form-control" id="authors_input"
					placeholder="Author1, Author2">
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form>
	</div>
	<h2>Hello World!</h2>
</body>
</html>
