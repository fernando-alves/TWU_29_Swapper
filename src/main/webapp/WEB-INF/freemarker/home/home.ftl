<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="static/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="static/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="static/css/swapper/swapper.css" rel="stylesheet" type="text/css">
    <link href="static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">

    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
</head>
<body>

<div class="container navbar-wrapper">

    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand">FeedMyCat</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="offer/create">Create Offer</a></li>
                    <li><a href="offer/browse">Browse Offer</a></li>
                </ul>
                <ul class="nav pull-right">
                    <li><a href="#" id="username">Welcome, ${username}</a></li>
                </ul>
            </div>

        </div>
    </div>
</div>

<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <div class="item active">
            <img src="static/img/slide-03.jpg" id="homepage">

            <div class="container">
                <div class="carousel-caption">
                    <h1>Welcome To FeedMyCat</h1>

                    <p class="lead">FeedMyCat is an online platform for employees of multi-city companies, who can benefit from
                        each other by swapping goods or services in an efficient, trusted user base.</p>
                    <div class="offer-button">
                        <a href="offer/create" id="createOffer" class="btn btn-large btn-primary" href="#">Create Offer</a>
                        <a href="offer/browse" id="browse" class="btn btn-large btn-primary" href="#">Browse Offer</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<footer>
    <div align="center">Copyright © 2012 ThoughtWorks Corporation, TWU-29, Swapper Team, All Rights Reserved</div>
</footer>
</body>
</html>