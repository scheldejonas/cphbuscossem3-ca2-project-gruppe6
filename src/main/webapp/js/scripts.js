/**
 * Created by William Pfaffe on 15-03-2017.
 */

$(document).ready(function() {
    $('select').material_select();
});

var userArr = [];
var sel = document.getElementById("selectedOption");
var searchQuery = document.getElementById('searchQuery');

function searchFunction() {
    if(searchQuery.value === "" && sel.options[sel.selectedIndex].value === "" || sel.options[sel.selectedIndex].value === "" || searchQuery.value === ""){
        alert("Please fill in all the parameters before searching!");
        return;
    }

    switch(sel.options[sel.selectedIndex].value){
        case "phoneNumber":
            httpGetPhone("http://localhost:8080/api/person/phone/" + searchQuery.value + "?useSSL=false");
            break;
        case "personName":
            alert("Not implemented yet!")
            break;
        case "CVR":
            alert("Not implemented yet!");
            break;
        case "hobbies":
            alert("Not implemented yet!");
            break;
        case "zipcode":
            httpGetCity("http://localhost:8080/api/person/zip/" + searchQuery.value + "?useSSL=false");
            break;


    }
}



function populatePersonContainerPhone(){
    var userDivContainer = document.getElementById("resultUserContainer");
    userDivContainer.innerHTML = "";
    for(var i = 0; i < userArr.length; i++){
        for(var p in userArr[0]){
            userDivContainer.innerHTML += "<div class='col s6 m5'><div class='card-panel #757575 grey darken-1'><span class='white-text'>First Name: " + userArr[i][p].firstName + "<br /> Last Name: " + userArr[i][p].lastName + "<br />Phone Number: " + userArr[i][p].phones[0].number + "<br />Hobbies: " + "NOT WORKING" + "<br /></span></div></div>";
        }
    }

}

function populatePersonContainerZip(){
    var userDivContainer = document.getElementById("resultUserContainer");
    userDivContainer.innerHTML = "";
    for(var i = 0; i < userArr.length; i++){
        for(var p in userArr[0]){
            userDivContainer.innerHTML += "<div class='col s6 m5'><div class='card-panel #757575 grey darken-1'><span class='white-text'>First Name: " + userArr[i][p].firstName + "<br /> Last Name: " + userArr[i][p].lastName + "<br />Hobbies: " + "NOT WORKING" + "<br /></span></div></div>";
        }
    }

}


function httpGetCity(theUrl){
    userArr = [];
    $.get(
        theUrl,
        {paramOne : 1, paramX : 'abc'},
        function(data) {
            userArr = data;
            populatePersonContainerZip();
        }
    );
}

function httpGetPhone(theUrl){
    userArr = [];
    console.log(userArr.length)
    $.get(
        theUrl,
        {paramOne : 1, paramX : 'abc'},
        function(data) {
            userArr = data;
            populatePersonContainerPhone();
        }
    );
}


