<html lang="en">
<head>
    <meta charset="utf-8">
    <title>View An Offer</title>
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
            <a class="brand">FeedMyCat</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="/twu/home">Home</a></li>
                    <li><a href="create">Create Offer</a></li>
                    <li><a href="browse">Browse Offer</a></li>
                </ul>
                <ul class="nav pull-right">
                    <li><a>Welcome, ${username}</a></li>
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

        <h1>${theOffer.title}</h1>
        <span class="label label-info">${theOffer.category}</span>
        <p>${theOffer.description}</p>
        <div>
            <form name="user" action="/twu/offer/takedown" method="get">
                <input type="hidden" name="offerId" id="offerId" value="${theOffer.id}">
            <#if "${username}" == "${theOffer.owner}"><input class="btn btn-primary" id="takeDownButton" type="submit"
                                                             value="   Take Down   " name="submit"/>            </#if>

            </form>

            <form action="https://mail.google.com/mail/?view=cm&fs=1&to=${theOffer.owner}@thoughtworks.com&su=I'm%20interested%20in%20your%20offer&body=I'd%20like%20to%20approach%20you%20regarding%20the%20details%20of%20your%20offer."
                  method="post">
            <#if "${username}"  != "${theOffer.owner}" >
                <input type="submit" class="btn btn-primary" value="  Contact  "/>
            </#if>
            </form>
        </div>
        </div>
    </div>
    <div style="clear:both"></div>
    <footer>
        <div align="center">Copyright © 2012 ThoughtWorks Corporation, TWU-29, Swapper Team, All Rights Reserved</div>
    </footer>
</body>
</html>