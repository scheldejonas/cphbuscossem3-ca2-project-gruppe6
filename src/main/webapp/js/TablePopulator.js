/**
 * Created by scheldejonas on 10/03/17.
 */
var xhrAddressesRequest = new XMLHttpRequest();
xhrAddressesRequest.onreadystatechange = function () {
    if (xhrAddressesRequest.readyState === 4 && xhrAddressesRequest.status === 200) {
        let table = document.querySelector('.addressTable');
        console.log(table);
        let tableBody = document.querySelector('.addressTableBody');
        console.log(tableBody);
        var addressObjectModel = JSON.parse(xhrAddressesRequest.responseText);
        console.log(addressObjectModel);
        var tableBodyHtml = '';
        for (var i = 0; i < addressObjectModel.length; i += 1) {
            tableBodyHtml += '<tr>';
            for (var property in addressObjectModel[i]) {
                tableBodyHtml += '<td>' + addressObjectModel[i][property] + '</td>';
            }
            tableBodyHtml += '</tr>';
        }
        console.log(tableBodyHtml);
        tableBody.innerHTML = tableBodyHtml;
    }
}
xhrAddressesRequest.open('GET', 'http://localhost:8080/api/addresses/100?arguments=fname,lname,street,city');
xhrAddressesRequest.send();


// xhrAddressesRequest.onreadystatechange = function () {
//     if (xhrAddressesRequest.readyState === 4 && xhrAddressesRequest.status === 200) {
//         let spanCountryHeadlineArray = document.querySelectorAll('.countryHeadline');
//         let wikiPageLinkParagraph = document.getElementById('wikiPageLinkParagraph');
//         let countryInfoSpan = document.querySelector('.countryInfoBox');
//         //console.log(countryInfoSpan);
//         var countryInfoModel = JSON.parse(xhrAddressesRequest.responseText);
//         console.log(countryInfoModel);
//         for (var i = 0; i < spanCountryHeadlineArray.length; i += 1) {
//             spanCountryHeadlineArray[i].innerText = countryInfoModel[0].name;
//         }
//         wikiPageLinkParagraph.innerHTML = '<a href="https://en.wikipedia.org/wiki/' + countryInfoModel[0].name + '">Wiki page</a>';
//         var countryInfoHTML = '';
//         for (var property in countryInfoModel[0]) {
//             countryInfoHTML += '<p>' + property + ': ' + countryInfoModel[0][property] + '</p>';
//         }
//         //console.log(countryInfoHTML);
//         countryInfoSpan.innerHTML = countryInfoHTML;
//     }
// };
//
// const cardDiv = document.querySelector('div.svg-container');
//
// cardDiv.addEventListener('click', (event) => {
//     if (event.target.tagName === 'path') {
//     let countryId = event.target.id;
//     let svgPopupPathContainer = document.getElementById('svgPopupPathContainer');
//     let pathCountry = event.target;
//     //console.log('id of country: ' + countryId);
//     xhrAddressesRequest.open('GET', "http://restcountries.eu/rest/v1/alpha?codes=" + countryId.substring(0,2));
//     xhrAddressesRequest.send();
//     //console.log(pathCountry);
//     //console.log(svgPopupPathContainer);
//     svgPopupPathContainer.innerHTML = '';
//     svgPopupPathContainer.appendChild(pathCountry.cloneNode());
// }
// });