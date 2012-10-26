<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Create</title>
  </head>

  <body>
    <form action="home" method="post">
      <fieldset>
        <h3>CreateProfile</h3>
        <div class="ctrlHolder">
          <label ><em>*</em> Your name</label>
          <input name="name" id="name" data-default-value="Placeholder text" size="35" maxlength="50" type="text" class="textInput required"/>
        </div>
        
        <div class="ctrlHolder">
          <label ><em>*</em> Your email</label>
          <input name="email" id="email" data-default-value="Placeholder text" size="35" maxlength="50" type="text" class="textInput required validateEmail"/>
        </div>
      </fieldset>
      
      <div class="buttonHolder">
        <button type="submit" class="primaryAction">Create Profile</button>
      </div>
    </form>
  </body>
</html>