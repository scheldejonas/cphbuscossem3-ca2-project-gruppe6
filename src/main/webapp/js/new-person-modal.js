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
        if (xhrPostPersonRequest.readyState === 4) {
            var personAsJsonFromPostRequest = JSON.parse(xhrPostPersonRequest.responseText)
            if (personAsJsonFromPostRequest['code'] === 204) {
                populateResultContainerWithErrorMessage('Unfortunatly your person did not got created the right way ' +
                    'Please try again');
                return;
            }
            if (personAsJsonFromPostRequest['code'] === 500) {
                populateResultContainerWithErrorMessage('When we tried to create your newly typed in person,' +
                    'there unfortunatly happened an error');
                return;
            }
            testThis('sendPersonAsJsonToServer.onreadystatechange', personAsJsonFromPostRequest);
            addToPersonDivContainer(personAsJsonFromPostRequest);
        }
        testThis('sendPersonAsJsonToServer.onreadystatechange', xhrPostPersonRequest.status);
        if (xhrPostPersonRequest.readyState === 4 && xhrPostPersonRequest.status === 500) {
            populateResultContainerWithErrorMessage('When we tried to create your newly typed in person,' +
                'there unfortunatly happened an error');
            return;
        }
    };
    xhrPostPersonRequest.open('POST', appURN + '/api/person' + useSSLQueryStringParameter);
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
    sendPersonAsJsonToServer(email, firstName, lastName);
    inputFields['firstName'].value = '';
    inputFields['lastName'].value = '';
    inputFields['email'].value = '';
    $(document).ready(function() {
        Materialize.updateTextFields();
    });
});

