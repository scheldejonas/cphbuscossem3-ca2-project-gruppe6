/**
 * Created by William Pfaffe on 15-03-2017.
 */

$(document).ready(function() {
    $('select').material_select();
});

var userArr =[
    {
        "0x1": {
            "firstName": "Test1",
            "lastName": "Test1",
            "hobbies": [{"id": 1,"name": "Fodbold","people": ["0x1"]}],
            "id": 1,
            "address": {"id": 1,"street": "Street1","cityInfo": {"id": 1,"zipCode": "0555","city": "Scanning"},
                "infoList": ["0x1","0x2"]},
            "phones": [{"id": 1,"number": "123124","info": "0x1"}]
        }
    }
];
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
            httpGetHobbies("http://localhost:8080/api/person/hobby/" + searchQuery.value + "?useSSL=false");
            break;
        case "zipcode":
            if(searchQuery.value === ":all"){
                httpGetAllCities();
            }else{
                httpGetCity("http://localhost:8080/api/person/zip/" + searchQuery.value + "?useSSL=false");
            }
            break;


    }
}

function getHobbies() {
    var hobbies = "";
    for(var i = 0; i < userArr.length; i++){
        for(var p in userArr[0]){
            for(var k = 0; k<userArr[i][p].hobbies.length; k++){
                hobbies += userArr[i][p].hobbies[k].name + ", ";
            }
        }
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

function populatePersonContainerHobbies(){
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
            userDivContainer.innerHTML += "<div class='col s6 m5'><div class='card-panel #757575 grey darken-1'><span class='white-text'>First Name: " + userArr[i][p].firstName + "<br /> Last Name: " + userArr[i][p].lastName + "<br />Hobbies: " + getHobbies() + "<br />Address: " + userArr[i][p].address.street + "<br /></span></div></div>";
        }
    }

}

function populateCardsAllCities(){
    var userDivContainer = document.getElementById("resultUserContainer");
    userDivContainer.innerHTML = "";
    for(var i = 0; i< allCities["0x1"].length; i++) {
        userDivContainer.innerHTML += "Zipcodes: " + allCities["0x1"][i].zipCode + "<br /><br />";
    }
}

function httpGetAllCities(){
    userArr = [];
    $.get(
        "http://localhost:8080/api/person/city/getAllCities?useSSL=false",
        {paramOne : 1, paramX : 'abc'},
        function(data) {
            allCities = data;
            populateCardsAllCities();
        }
    );
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
