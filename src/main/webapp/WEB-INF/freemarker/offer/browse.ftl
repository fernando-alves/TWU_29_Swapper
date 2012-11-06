<!DOCTYPE html>
<html>
<head>
    <title>Browse Offers</title>
</head>

<body>
<h2>Welcome, ${username}</h2>
<div id="content">
    <ol>
    <#assign i=0>
    <#list allOffers as offer>
        <li><a href="viewAnOfferFromBrowse?offerId=${offer.getId()}" id="offer${i+1}">${offer.getTitle()}</a></li>
    </#list>
    </ol>
</div>

</body>
</html>