<!DOCTYPE html>
<html>
<head>
    <title>Create Offer</title>
</head>

<body>

<div id="content">

    <fieldset>
        <legend>Add Offer</legend>
        <form name="user" action="home.ftl" method="post">
            Title: <br/>
            <input type="text" name="firstname" /> <br/>
            Catagory: <br/>
            <select name="selectCatagory" id="selectCatagory">
                <option value="1">Books</option>
                <option value="2">Cars</option>
                <option value="3">Housing</option>
                <option value="4">Miscellaneous</option>
                <option value="5">Services</option>
            </select><br/>
            Description:<br/>
            <textarea rows="4" cols="50"></textarea><br/>
            <input type="submit" value="   Save   "/>
        </form>
    </fieldset>

</div>

</body>
</html>