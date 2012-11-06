<!DOCTYPE html>
<html>
<head>
    <title>Create Offer</title>
</head>

<body>

<div id="content">

    <fieldset>
        <legend> ${username} / Add Offer</legend>
        <form name="user" action="/twu/offer/createAnOffer" method="post">
            Title: <br/>
            <input type="text" name="title" maxlength="100"/> <br/>
            Category: <br/>
            <select name="category" id="selectCategory">
                <option value="Books">Books</option>
                <option value="Cars">Cars</option>
                <option value="Housing">Housing</option>
                <option value="Miscellaneous">Miscellaneous</option>
                <option value="Services">Services</option>
            </select><br/>
            Description:<br/>
            <textarea rows="4" cols="50" name="description" style="width: 300px; height: 200px; resize: none;" maxlength="1000"></textarea><br/>
            <input type="submit" value="   Save   " name="submit"/>
        </form>
    </fieldset>

</div>

</body>
</html>