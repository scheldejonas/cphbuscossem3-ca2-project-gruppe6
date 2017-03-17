/**
 * Created by William Pfaffe on 15-03-2017.
 */

$(document).ready(function() {
    $('select').material_select();
});

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

document.getElementById("searchQuery").addEventListener("keydown", function(event) {
    if (event.keyCode == 13) {
        searchFunction();
    }
});

var userArr =[{
    "firstName": "Test1",
    "lastName": "Test1",
    "hobbies": [
        {
            "id": 1,
            "name": "Fodbold",
            "people": [
                "0x1",
                "0x2"
            ]
        }
    ],
    "id": 1,
    "email": "lam@cphbusiness.dk",
    "address": {
        "id": 1,
        "street": "Street1",
        "cityInfo": {
            "id": 1,
            "zipCode": "0555",
            "city": "Scanning"
        },
        "infoList": [
            "0x1",
            "0x2",
            "0x3"
        ]
    },
    "phones": [
        {
            "id": 1,
            "number": "123124",
            "info": "0x1"
        }
    ]
}, {
    "firstName": "Test2",
    "lastName": "Test2",
    "hobbies": [
        {
            "id": 1,
            "name": "Fodbold",
            "people": [
                "0x1",
                "0x2"
            ]
        }
    ],
    "id": 1,
    "email": "lam@cphbusiness.dk",
    "address": {
        "id": 1,
        "street": "Street1",
        "cityInfo": {
            "id": 1,
            "zipCode": "0555",
            "city": "Scanning"
        },
        "infoList": [
            "0x1",
            "0x2",
            "0x3"
        ]
    },
    "phones": [
        {
            "id": 1,
            "number": "123124",
            "info": "0x1"
        }
    ]
}];

var sel = document.getElementById("selectedOption");
var searchQuery = document.getElementById('searchQuery');
var userDivContainer = document.getElementById("resultUserContainer");


function populateResultContainerWithErrorMessage(errorMessageString) {
        userDivContainer.innerHTML = '' +
            '<div class="col s12 center">' +
                '<i class="large material-icons">error_outline</i>' +
                '<p>' + errorMessageString + '</p>' +
            '</div>';
}

function searchFunctionPerson() {
    if(searchQuery.value === "" && sel.options[sel.selectedIndex].value === "" || sel.options[sel.selectedIndex].value === "" || searchQuery.value === ""){
        populateResultContainerWithErrorMessage('Please type into the search field before submitting');
        return;
    }

    userDivContainer.innerHTML = '' +
        '<br />' +
        '<div class="progress">' +
            '<div class="indeterminate"></div>' +
        '</div>';


    console.log("Selected value: " + sel.options[sel.selectedIndex].value);

    switch(sel.options[sel.selectedIndex].value){
        case "phoneNumber":
            httpGetPerson("http://localhost:8080/api/person/phone/" + searchQuery.value);
            break;
        case "name":
            httpGetPerson("http://localhost:8080/api/person/name/" + searchQuery.value);
            break;
        case "CVR":
            populateResultContainerWithErrorMessage('CVR search not available for person search');
            break;
        case "hobbies":
            httpGetPerson("http://localhost:8080/api/person/hobby/" + searchQuery.value + "?useSSL=false");
            //httpGetHobbies("http://localhost:8080/api/person/hobby/" + searchQuery.value + "?useSSL=false");
            break;
        case "zipcode":
            httpGetPerson("http://localhost:8080/api/person/zip/" + searchQuery.value + "?useSSL=false");
            break;
    }
}

function setLoadBarInResultContainer() {
    userDivContainer.innerHTML = '' +
        '<br />' +
        '<div class="progress">' +
        '<div class="indeterminate"></div>' +
        '</div>';
}

function testThis(placeName, value) {
    if (placeName === undefined) {
        placeName = 'not set yet';
    }
    console.log('Testing value from ' + placeName + ': ' + value);
}

function searchFunctionCompany() {
    if(searchQuery.value === "" && sel.options[sel.selectedIndex].value === "" || sel.options[sel.selectedIndex].value === "" || searchQuery.value === ""){
        populateResultContainerWithErrorMessage('Please type into the search field before submitting');
        return;
    }

    setLoadBarInResultContainer();

    testThis(sel.options[sel.selectedIndex].value);

    switch(sel.options[sel.selectedIndex].value){
        case "phoneNumber":
            httpGetPerson("http://localhost:8080/api/company/phone/" + searchQuery.value);
            break;
        case "name":
            httpGetPerson("http://localhost:8080/api/company/name/" + searchQuery.value);
            break;
        case "CVR":
            httpGetCompany("http://localhost:8080/api/company/cvr/" + searchQuery.value);
            break;
        case "hobbies":
            populateResultContainerWithErrorMessage('Company search for hobbies unavailable');
            break;
        case "zipcode":
            httpGetCompany("http://localhost:8080/api/company/zip/" + searchQuery.value + "?useSSL=false");
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

function populatedPersonCard(personElement){
    testThis('PopulatedPersonCard', 'Populating cards...')
    var str = "<div class='resultCard col s12 m6'>" +
        "<div class='card blue-grey darken-1'>" +
        "<div class='card-content white-text'>" +
        "<span class='card-title'>" + personElement.firstName + " " + personElement.lastName + "</span>" +
        "<p>Address: " + personElement.address.street + "</p>" +
        "<p>Hobbies: " + getAllHobbiesToText(personElement.hobbies) + "</p>" +
        "<p>Phone Numbers: " + getAllPhonesToText(personElement.phones) + "</p>" +
         "</div></div></div>";
        return str;
}

function populatedCompanyCard(companyElement){
    console.log("Populating cards...");
    var str = "<div class='resultCard col s12 m6'>" +
        "<div class='card blue-grey darken-1'>" +
        "<div class='card-content white-text'>" +
        "<span class='card-title'>" + companyElement.name + "</span>" +
        "<p>CVR Number: " + companyElement.cvr + "</p>" +
        "<p>Description: " + companyElement.description + "</p>" +
        "<p>Email: " + companyElement.email + "</p>" +
        "<p>Address: " + companyElement.address.street + "</p>" +
        "<p>Phone Numbers: " + getAllPhonesToText(companyElement.phones) + "</p>" +
        "</div></div></div>";
    return str;
}

function addToPersonDivContainer(userArrS){
    var str = "";
    if (userArrS.length == undefined) {
        str += populatedPersonCard(userArrS);
        testThis('Adding single person', str);
        userDivContainer.innerHTML = str;
        return;
    }
    for(var i = 0; i<userArrS.length; i++){
        console.log("Working with object " + (i + 1) + "/" + userArrS.length);
        str += populatedPersonCard(userArrS[i]);
    }
    userDivContainer.innerHTML = str;
    return;
}

function addToCompanyDivContainer(userArrS){
    var str = "";
    if (userArrS.length == undefined) {
        str += populatedCompanyCard(userArrS);
        testThis('Adding single company', str);
        userDivContainer.innerHTML = str;
        return str;
    }
    for(var i = 0; i<userArrS.length; i++){
        testThis("Working with object", (i + 1) + "/" + userArrS.length);
        str += populatedCompanyCard(userArrS[i]);
    }
    userDivContainer.innerHTML = str;
    return str;
}

function populatePersonContainer(){
    userDivContainer.innerHTML = '';
    // console.log('Length of retrieved user array' + userArr.length);
    if (userArr.length === 0) {
        populateResultContainerWithErrorMessage('There was unfortunatly no content to retrieve on your search');
        return;
    }
    addToPersonDivContainer(userArr);
}

function populateCompanyContainer(){
    userDivContainer.innerHTML = '';
    if (userArr.length === 0) {
        populateResultContainerWithErrorMessage('There was unfortunatly no content to retrieve on your search');
        return;
    }
    addToCompanyDivContainer(userArr);
}

function getAllHobbiesToText(hobbyArr){
    console.log("Loading Hobbies: " + hobbyArr.length);
    var hobbyString = "";
    for(var i = 0; i<hobbyArr.length; i++){
        console.log("Current hobby being worked on is: " + hobbyArr[i].name);
        hobbyString += hobbyArr[i].name + ", ";
    }

    if(hobbyString.length === 0){
        return "";
    }

    return hobbyString.substring(0, hobbyString.length-2);
}

function getAllPhonesToText(phonesArr){
    console.log("Loading Phones: " + phonesArr.length);
    var phoneString = "";
    for(var i = 0; i<phonesArr.length; i++){
        console.log("Current phone number being worked on is: " + phonesArr[i].name);
        phoneString += phonesArr[i].number + ", ";
    }

    if(phonesArr.length === 0){
        return "";
    }

    return phoneString.substring(0, phoneString.length-2);
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

function populateCardsAllCities(){
    var userDivContainer = document.getElementById("resultUserContainer");
    userDivContainer.innerHTML = "";
    for(var i = 0; i< allCities["0x1"].length; i++) {
        userDivContainer.innerHTML += "Zipcodes: " + allCities["0x1"][i].zipCode + "<br /><br />";
    }
}

function httpGetCity(theUrl){
    userArr = [];
    $.get(
        theUrl,
        function(data) {
            console.log("Hej vi læser data");
            userArr = data;
            populatePersonContainer();
        }
    );
}

function httpGetPhone(theUrl){
    userArr = [];
    $.get(
        theUrl,
        function(data) {
            userArr = data;
            populatePersonContainer();
        }
    );
}

function httpGetPerson(theUrl){
    userArr = [];
    $.get(
        theUrl,
        function(data) {
            userArr = data;
            populatePersonContainer();
        }
    );
}

function httpGetCompany(theUrl){
    userArr = [];
    $.get(
        theUrl,
        function(data) {
            userArr = data;
            populateCompanyContainer();
        }
    );
}

function httpGetCompanyAsync(theUrl){
    userArr = [];
    console.log("Running getCompany From CVR");
    $.ajax({
        url: theUrl,
        success: function (data) {
            //console.log("Success parsing company from CVR! " + data.toString());
            userArr = data;
            populateCompanyContainer();
        },
        error: function (jqXHR, exception) {
            console.log('Uncaught Error.\n' + jqXHR.responseText);
        },
        async: false,
    });
}