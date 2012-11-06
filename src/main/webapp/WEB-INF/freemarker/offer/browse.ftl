<!DOCTYPE html>
<html>
<head>
    <title>Browse Offers</title>
</head>

<body>
<h2>Welcome, ${username}</h2>
<div id="content">
    <ol>
    <#list allOffers as offer>
        <li>${offer.getTitle()}</li>
    </#list>
    </ol>
</div>

</body>
</html>