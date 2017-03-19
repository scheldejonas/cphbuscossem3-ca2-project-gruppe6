/**
 * Created by William Pfaffe on 15-03-2017.
 */

var appURN = 'https://viter.dk/yellowpages';
var useSSLQueryStringParameter = '?useSSL=true';

$(document).ready(function() {
    $('select').material_select();
});

$(document).ready(function(){
    // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});

document.getElementById("searchQuery").addEventListener("keydown", function(event) {
    if (event.keyCode == 13) {
        searchFunctionPerson();
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
            '</br>' +
            '</br>' +
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
            httpGetPerson(appURN + '/api/person/phone/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "name":
            httpGetPerson(appURN + '/api/person/name/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "CVR":
            populateResultContainerWithErrorMessage('CVR search is existing on a person');
            break;
        case "hobbies":
            httpGetPerson(appURN + '/api/person/hobby/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "zipcode":
            httpGetPerson(appURN + '/api/person/zip/' + searchQuery.value + useSSLQueryStringParameter);
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
            httpGetCompany(appURN + '/api/company/phone/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "name":
            httpGetCompany(appURN + '/api/company/name/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "CVR":
            httpGetCompany(appURN + '/api/company/cvr/' + searchQuery.value + useSSLQueryStringParameter);
            break;
        case "hobbies":
            populateResultContainerWithErrorMessage('Companys does not have hobbies');
            break;
        case "zipcode":
            httpGetCompany(appURN + '/api/company/zip/' + searchQuery.value + useSSLQueryStringParameter);
            break;
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
        testThis('addToPersonDivContainer', str);
        userDivContainer.innerHTML = str;
        return;
    }
    for(var i = 0; i<userArrS.length; i++){
        testThis('addToPersonDivContainer',"Working with object " + (i + 1) + "/" + userArrS.length);
        str += populatedPersonCard(userArrS[i]);
    }
    userDivContainer.innerHTML = str;
    return;
}

function addToCompanyDivContainer(userArrS){
    var str = "";
    if (userArrS.length == undefined) {
        str += populatedCompanyCard(userArrS);
        testThis('addToCompanyDivContainer', str);
        userDivContainer.innerHTML = str;
        return str;
    }
    for(var i = 0; i<userArrS.length; i++){
        testThis('addToCompanyDivContainer', (i + 1) + "/" + userArrS.length);
        str += populatedCompanyCard(userArrS[i]);
    }
    userDivContainer.innerHTML = str;
    return str;
}

function populatePersonContainer(){
    userDivContainer.innerHTML = '';
    testThis('populateCompanyContainer', userArr[0] + userArr[1]);
    if (userArr.length === 0) {
        populateResultContainerWithErrorMessage('There was unfortunatly no content people on your search');
        return;
    }
    addToPersonDivContainer(userArr);
}

function populateCompanyContainer() {
    userDivContainer.innerHTML = '';
    testThis('populateCompanyContainer', userArr[0] + userArr[1]);
    if (userArr.length === 0) {
        populateResultContainerWithErrorMessage('There was unfortunatly no content companies on your search');
        return;
    }
    addToCompanyDivContainer(userArr);
}

function getAllHobbiesToText(hobbyArr){
    console.log("Loading Hobbies: " + hobbyArr.length);
    var hobbyString = "";
    for(var i = 0; i<hobbyArr.length; i++){
        testThis('getAllHobbiesToText',"Current hobby being worked on is: " + hobbyArr[i].name);
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
        testThis('getAllPhonesToText',"Current phone number being worked on is: " + phonesArr[i].name);
        phoneString += phonesArr[i].number + ", ";
    }

    if(phonesArr.length === 0){
        return "";
    }

    return phoneString.substring(0, phoneString.length-2);
}

function httpGetPerson(theUrl){
    userArr = [];
    var xhrGetPerson = new XMLHttpRequest();
    xhrGetPerson.onreadystatechange = function () {
        if (xhrGetPerson.readyState === 4 && xhrGetPerson.status === 200) {
            userArr = JSON.parse(xhrGetPerson.responseText);
            if (userArr['code'] === 204) {
                populateResultContainerWithErrorMessage('Unfortunatly there was no people for this search');
                return;
            }
            if (userArr['code'] === 404) {
                populateResultContainerWithErrorMessage('We are sorry, it seems the server does not have this api option yet');
                return;
            }
            if (userArr['code'] === 500) {
                populateResultContainerWithErrorMessage('We are very sorry to inform, that our server had a problem fetching' +
                    'you content</br>' +
                    'Please try again');
                return;
            }
            populatePersonContainer();
            return;
        }
        if (xhrGetPerson.readyState === 4 && xhrGetPerson.status === 204) {
            populateResultContainerWithErrorMessage('Unfortunatly there was no people for this search');
            return;
        }
        if (xhrGetPerson.readyState === 4 && xhrGetPerson.status === 404) {
            populateResultContainerWithErrorMessage('We are sorry, it seems the server does not have this api option yet');
            return;
        }
        if (xhrGetPerson.readyState === 4 && xhrGetPerson.status === 500) {
            populateResultContainerWithErrorMessage('We are very sorry to inform, that our server had a problem fetching ' +
                'your content</br>' +
                'Please try again');
            return;
        }
    };
    xhrGetPerson.open('GET', theUrl);
    xhrGetPerson.setRequestHeader('Content-Type', 'application/json');
    xhrGetPerson.send();
}

function httpGetCompany(theUrl){
    userArr = [];
    var xhrGetCompany = new XMLHttpRequest();
    xhrGetCompany.onreadystatechange = function () {
        if (xhrGetCompany.readyState === 4 && xhrGetCompany.status === 200) {
            userArr = JSON.parse(xhrGetCompany.responseText);
            if (userArr['code'] === 204) {
                populateResultContainerWithErrorMessage('Unfortunatly there was no companies for this search');
                return;
            }
            if (userArr['code'] === 404) {
                populateResultContainerWithErrorMessage('We are sorry, it seems the server does not have this api option yet');
                return;
            }
            if (userArr['code'] === 500) {
                populateResultContainerWithErrorMessage('We are very sorry to inform, that our server had a problem fetching' +
                    'you content</br>' +
                    'Please try again');
                return;
            }
            populateCompanyContainer();
            return;
        }
        if (xhrGetCompany.readyState === 4 && xhrGetCompany.status === 204) {
            populateResultContainerWithErrorMessage('Unfortunatly there was no companies for this search');
            return;
        }
        if (xhrGetCompany.readyState === 4 && xhrGetCompany.status === 404) {
            populateResultContainerWithErrorMessage('We are sorry, it seems the server does not have this api option yet');
            return;
        }
        if (xhrGetCompany.readyState === 4 && xhrGetCompany.status === 500) {
            populateResultContainerWithErrorMessage('We are very sorry to inform, that our server had a problem fetching ' +
                'your content</br>' +
                'Please try again');
            return;
        }
    };
    xhrGetCompany.open('GET', theUrl);
    xhrGetCompany.setRequestHeader('Content-Type', 'application/json');
    xhrGetCompany.send();
}

// function httpGetCompanyAsync(theUrl){
//     userArr = [];
//     console.log("Running getCompany From CVR");
//     $.ajax({
//         url: theUrl,
//         success: function (data) {
//             //console.log("Success parsing company from CVR! " + data.toString());
//             userArr = data;
//             populateCompanyContainer();
//         },
//         error: function (jqXHR, exception) {
//             console.log('Uncaught Error.\n' + jqXHR.responseText);
//         },
//         async: false,
//     });
// }