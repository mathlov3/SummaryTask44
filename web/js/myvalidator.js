$('#registration').submit(function (e) {
    if (signUpValidate()) {
    }
    else {
        e.preventDefault();
    }
});
var loginRegex = /^[a-zA-Z0-9_]{5,24}$/;
var passwordRegex = /^[a-zA-Z0-9_]{5,24}$/;
var nameRegex = /^[a-zA-Z0-9]{1,20}$/;
var emailRegex = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;

function signUpValidate() {

    var result = true;
    if (document.getElementById("login").value.match(loginRegex) === null) {
        document.getElementById("login").style.borderColor = "red";
        result = false;
    }
    if (document.getElementById("name").value.match(nameRegex) === null) {
        document.getElementById("name").style.borderColor = "red";
        result = false;
    }
    if (document.getElementById("email").value.match(emailRegex) === null) {
        document.getElementById("email").style.borderColor = "red";
        result = false;
    }
    if (document.getElementById("password").value.match(passwordRegex) === null) {
        document.getElementById("password").style.borderColor = "red";
        result = false;
    }
    if (document.getElementById("repassword").value.match(passwordRegex) === null) {
        document.getElementById("repassword").style.borderColor = "red";
        result = false;
    }
    if (document.getElementById("repassword").value != document.getElementById("password").value){
        document.getElementById("repassword").style.borderColor = "red";
        result = false;
    }
    return result;
}

$('#login').focusout(function () {
    if (document.getElementById("login").value.match(loginRegex) === null) {
        document.getElementById("login").style.borderColor = "red";
    }else {
        document.getElementById("login").style.borderColor = "green";
    }

});

$('#email').focusout(function () {
    if (document.getElementById("email").value.match(emailRegex) === null) {
        document.getElementById("email").style.borderColor = "red";
    }else {
        document.getElementById("email").style.borderColor = "green";
    }

});

$('#name').focusout(function () {
    if (document.getElementById("name").value.match(nameRegex) === null) {
        document.getElementById("name").style.borderColor = "red";
    }else {
        document.getElementById("name").style.borderColor = "green";
    }

});

$('#password').focusout(function () {
    if (document.getElementById("password").value.match(passwordRegex) === null) {
        document.getElementById("password").style.borderColor = "red";
    }else {
        document.getElementById("password").style.borderColor = "green";
    }

});
$('#repassword').focusout(function () {
    if (document.getElementById("repassword").value.match(passwordRegex) === null) {
        document.getElementById("repassword").style.borderColor = "red";
    }else {
        document.getElementById("repassword").style.borderColor = "green";
        if (document.getElementById("repassword").value != document.getElementById("password").value){
            document.getElementById("repassword").style.borderColor = "red";
        }
    }

});