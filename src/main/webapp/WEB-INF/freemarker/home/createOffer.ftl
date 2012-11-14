<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create Offer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../../twu/static/css/bootstrap.css" rel="stylesheet">
    <link href="../../../twu/static/css/swapper/swapper.css" rel="stylesheet">
    <link href="../../../twu/static/css/bootstrap-responsive.css" rel="stylesheet">

    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>

    <script src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script src="../../../twu/static/js/offerValidator.js" type="text/javascript"></script>
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
                    <li><a href="#">Welcome, ${username}</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>
<div id="myCarousel">
    <div class="carousel-inner header">
        <div class="active pos-1 header-image">
            <img src="../../../twu/static/img/DetailPage.jpg"/>
        </div>
    </div>
</div>
<div class="main-container">
    <div id="sidebar-left">
    </div>
    <div id="sidebar-right">
    </div>
    <div class="content-position">
        <div class="content-position-information">
            <h1>Create Offer</h1>
     <form name="user" action="createAnOffer" method="post" onsubmit="return validateWhenClick()">
         <p>
        <input class="input-block-level" type="text" name="title" maxlength="100" placeholder="Title">
       </p>
         <p>
         <select name="category" id="selectCategory">
                    <option value="Books">Select a category</option>
                    <option value="Books">Books</option>
                    <option value="Cars">Cars</option>
                    <option value="Housing">Housing</option>
                    <option value="Miscellaneous">Miscellaneous</option>
                    <option value="Services">Services</option>
                </select>
</p>
         <p>
           <textarea rows="4" cols="50" name="descriptionTxt" placeholder="Description" maxlength="1000"></textarea>
         </p><input class="btn btn-primary"  type="submit" value="   save   " name="submit" />
            </form>
        </fieldset>
    </div>

</div>
<div style="clear:both"></div>
<footer>
    <div align="center">Copyright © 2012 ThoughtWorks Corporation, TWU-29, Swapper Team, All Rights Reserved</div>
</footer>

</body></html>