$(document).ready(function() {
    init();
    validateWidgets();
    clickToVerify();
});

function init () {
   $("#colour").attr("disabled", true);
}

function validateWidgets() {
    $("#widgets").keyup(function() {
	if ($("#widgets").val() > 0) {
	    $("#colour").attr("disabled", false);
	} else {
	    $("#colour").attr("disabled", true);
	}
 });
}

function clickToVerify() {
    $("#submitButton").click(function() {
        if ($("#widgets").val() == "") {
            $(document.body).append('<p id="noValue">please enter the number of widgets</p>');
        } 
        // if ($("#colour option:selected").text() == "Please select a colour") 
        if ($("#colour")[0].selectedIndex == 0) {
            $(document.body).append('<p id="selectOfColour">please select a colour</p>')
        }
    });
}
	    
