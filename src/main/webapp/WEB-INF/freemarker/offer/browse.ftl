<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add An Offer</title>
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
                    <li><a href="/twu">Home</a></li>
                    <li><a href="/twu/offer/create">Create Offer</a></li>
                    <li  class="active"><a href="#">Browse Offers</a></li>
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
            <ol>
                <#assign i=0>
                <#list allOffers as offer>
                <li><a href="viewAnOfferFromBrowse?offerId=${offer.getId()}" id="offer${i+1}" class="offer">${offer.getTitle()}</a></li>
                </#list>
            </ol>
        </div>
    </div>

</div>
<div style="clear:both"></div>
<footer>
    <div align="center">Copyright © 2012 ThoughtWorks Corporation, TWU-29, Swapper Team, All Rights Reserved</div>
</footer>

</body></html>