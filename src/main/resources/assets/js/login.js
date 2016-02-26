/**
 * Created by rishabh.sood on 26/02/16.
 */
function signUp() {
    $('#signUpModal').modal();
}

function enterinfo() {
    var username = document.getElementById("username").value.trim();
    var pass = document.getElementById("password").value.trim();
    var name = document.getElementById("name").value.trim();
    var email = document.getElementById("email").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var drop = document.getElementById("dropbox");
    var type = drop.options[drop.selectedIndex].text;

    var json = "{"+
    "\"username\": \""+username+"\","+
        "\"password\": \""+pass+"\",\n"+
        "\"type\": \""+type+"\",\n"+
        "\"name\": \""+name+"\",\n"+
        "\"email\": \""+email+"\",\n"+
        "\"phone\": \""+phone+"\"\n"+
        "}";
    $.ajax({
        url: "/api/foodmania/login/addUser",
        type: "POST",
        contentType: "application/json",
        data: json,
        success: function (data) {
            alert("status updated Successfully");
            $('#itemModal').modal('hide');
        },
        error: function () {

        },
        complete: function () {
        }
    })
}

function enter() {
    var username = document.getElementById("user-name").value.trim();
    var password = document.getElementById("user-password").value.trim();
    $.ajax({
        url: "/api/foodmania/login/getType/"+username+"/"+password,
        type: "POST",
        contentType: "application/json",
        async: false,
        success: function (data) {
            var json = JSON.parse(data);
            var status = json.type;
            if(status == "Customer") {
                sessionStorage.setItem('username',username);
            } else {

            }
        },
        error: function () {

        },
        complete: function () {
        }
    })
}