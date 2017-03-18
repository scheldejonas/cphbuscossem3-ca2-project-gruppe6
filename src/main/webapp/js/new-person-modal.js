/**
 * Created by scheldejonas on 10/03/17.
 */

var newPersonDiv = document.getElementById('divNewPersonInModal');
var submitButtonInFormNewPersonModal = document.getElementById('submitButtonInFormNewPersonModal');

function sendPersonAsJsonToServer(email, firstName, lastName) {
    var xhrPostPersonRequest = new XMLHttpRequest();
    var postBodyWithValues = '' +
        'email=' + email + '&' +
        'firstName=' + firstName + '&' +
        'lastName=' + lastName;
    /**
     * This method is making updating the result container, through scripts, as soon as the JSON of the person is returned
     */
    xhrPostPersonRequest.onreadystatechange = function() {
        if (xhrPostPersonRequest.readyState === 4 && xhrPostPersonRequest.status === 200) {
            var personAsJsonFromPostRequest = JSON.parse(xhrPostPersonRequest.responseText)
                testThis('returned object from post ajax',personAsJsonFromPostRequest);
            addToPersonDivContainer(personAsJsonFromPostRequest);
                testThis('succes on post ajax','I succesfully received the created person');
        }
        if (xhrPostPersonRequest.readyState === 4 && xhrPostPersonRequest.status === 500) {
            populateResultContainerWithErrorMessage('When we tried to create your newly typed in person,' +
                'there unfortunatly happened an error');
                testThis('error on post ajax','I did not got the created person back from the server');
        }
    }
    xhrPostPersonRequest.open('POST', 'http://viter.dk/yellowpages/api/person?useSSL=true');
    xhrPostPersonRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhrPostPersonRequest.send(postBodyWithValues);
}

/**
 * This method is listening on the submit button in the New Person Modal, and the sending the data to the server
 */
submitButtonInFormNewPersonModal.addEventListener('click', (event) => {
    setLoadBarInResultContainer();
    inputFields = newPersonDiv.getElementsByTagName('input');
    var firstName = inputFields['firstName'].value;
    var lastName = inputFields['lastName'].value;
    var email = inputFields['email'].value;
        testThis('eventlistener for submit button', firstName + lastName + email);
    sendPersonAsJsonToServer(email, firstName, lastName);
});

