<html lang="en"><head>
    <meta charset="utf-8">
    <title>Carousel Template · Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../../twu/static/css/bootstrap.css" rel="stylesheet">
    <link href="../../../twu/static/css/swapper/swapper.css" rel="stylesheet">
    <link href="../../../twu/static/css/bootstrap-responsive.css" rel="stylesheet">

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
            <a class="brand" href="#">FeedMyCat</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">Create Offer</a></li>
                    <li><a href="#contact">Browse Offer</a></li>
                    <li><a href="#contact">About</a></li>
                </ul>
                <ul class="nav pull-right">
                    <li><a href="#">Welcome, username</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>

<div id="myCarousel">
    <div class="carousel-inner">
        <div class="active pos-1" style="background-color: #000;">
            <img src="/../../../twu/static/img/DetailPage.jpg" id="abc" style="margin-bottom: 1%;">
        </div>
    </div>
</div>

<div style="width: 100%;">

    <div style="
    background: #999;
">

        <fieldset style="
    background: #FFF;
    width: 400px;
    margin: 0 auto;
    padding: 20px 214px;
">
            <legend style="
    float: left;
"> Add Offer</legend>
            <form name="user" action="/twu/offer/createAnOffer" method="post">
                Title: <br>
                <input type="text" name="title" maxlength="100"> <br>
                Category: <br>

                <select name="category" id="selectCategory">
                    <option value="Books">Books</option>
                    <option value="Cars">Cars</option>
                    <option value="Housing">Housing</option>
                    <option value="Miscellaneous">Miscellaneous</option>
                    <option value="Services">Services</option>
                </select><br>
                Description:<br>
                <textarea rows="4" cols="50" name="description" style="width: 300px; height: 200px; resize: none;" maxlength="1000"></textarea><br>
                <input type="submit" value="   Save   " name="submit">
            </form>
        </fieldset>
    </div>

</div>
<div style="clear:both"></div>
<footer>
    <div align="center">Copyright © 2012 ThoughtWorks Corporation, TWU-29, Swapper Team, All Rights Reserved</div>
</footer>

</body></html>