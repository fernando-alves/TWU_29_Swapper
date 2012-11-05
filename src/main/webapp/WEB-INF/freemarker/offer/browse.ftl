<!DOCTYPE html>
<html>
<head>
    <title>Browse Offers</title>
</head>

<body>

<div id="content">
    <ol>
    <#list model["allOffers"] as offer>
        <li>${offer.getTitle()}</li>
    </#list>
    </ol>
</div>

</body>
</html>