function validateTitle() {
    var actualTitle = document.forms["user"]["title"].value;

    if (actualTitle === "") {
        alert("Title can not be empty");
        return true;
    }

    actualTitle = trim(actualTitle);

    if (actualTitle.length === 0) {
        alert("Title cannot be blank");
        return true;
    }
}

function validateDescription() {

    var actualDescription = document.forms["user"]["descriptionTxt"].value;

    if (actualDescription === "") {
        alert("description can not be empty");
        return true;
    }
    actualDescription = trim(actualDescription);

    if (actualDescription.length === 0) {
        alert("description can not be blank");
        return true;
    }
}

function validateSelectList() {
    var selectedItem = $("#selectCategory").find(":selected").text();

    if(selectedItem === "Select a category") {
        alert("Please select a category");
        return true;
    }
}

function trim(str) {
    return str.replace(/^\s+|\s+$/g, "");
}

function validateWhenClick() {
    $('[name="submit"]').click(function () {
        if (!validateTitle() && !validateDescription() && !validateSelectList()) {
            return true;
        }
        return false;
    });
}

$('document').ready(function () {
    validateWhenClick();
});

