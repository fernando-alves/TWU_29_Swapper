<!DOCTYPE html>
<html>
<head>
    <title>TWU Demo</title>
</head>

<body>
<#if user??>
<h1>Hello ${user.name}</a>
<#else>
    <#if username??>
        <h1>Sorry, didn't find a user called "${username}"</h1>
    </#if>
    <p><a href="?username=garima">Try me</a></p>
</#if>
</h1>

</body>
</html>