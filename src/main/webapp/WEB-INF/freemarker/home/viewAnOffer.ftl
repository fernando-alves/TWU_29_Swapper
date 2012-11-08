<html>
<head>
    <title>View An Offer</title>
</head>
<body>


<table>
    Offer owner : ${theOffer.owner} <br/>
    Title : ${theOffer.title} <br/>
    Category : ${theOffer.category} <br/>
    Description : ${theOffer.description} <br/>
    Contact offer owner : ${theOffer.owner}@thoughtworks.com</br>
</table>
<form name="user" action="/twu/offer/takedown" method="get">
    <input type="hidden" name="offerId" id="offerId" value="${theOffer.id}">
    <input id="takeDownButton" type="submit" value="   Take Down   " name="submit" />
</form>
</body>
</html>
