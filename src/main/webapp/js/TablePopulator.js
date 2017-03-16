/**
 * Created by scheldejonas on 10/03/17.
 */
var xhrAddressesRequest = new XMLHttpRequest();
xhrAddressesRequest.onreadystatechange = function() {
    if (xhrAddressesRequest.readyState === 4 /*&& xhrAddressesRequest.status === 200*/) {
        var table = document.querySelector('.table');
        console.log(table);
        var tableBody = document.querySelector('.tableBody');
        console.log(tableBody);
        var dataObjectModel = JSON.parse(xhrAddressesRequest.responseText);
        console.log(dataObjectModel);
        var tableBodyHtml = '';
        for (var i = 0; i < dataObjectModel.length; i += 1) {
            tableBodyHtml += '<tr>';
            for (var property in dataObjectModel[i]) {
                tableBodyHtml += '<td>' + dataObjectModel[i][property] + '</td>';
            }
            tableBodyHtml += '</tr>';
        }
        console.log(tableBodyHtml);
        tableBody.innerHTML = tableBodyHtml;
    }
}
xhrAddressesRequest.open('GET', 'http://localhost:8080/api/person/phone/123124?useSSL=false');
xhrAddressesRequest.send();

var xhrAddressesRequest = new XMLHttpRequest();
xhrAddressesRequest.onreadystatechange = function() {
    if (xhrAddressesRequest.readyState === 4 /*&& xhrAddressesRequest.status === 200*/) {
        var resultContainer = document.getElementById("resultContainer");
        var dataObjectModel = JSON.parse(xhrAddressesRequest.responseText);
        console.log(dataObjectModel);
        var tableBodyHtml = '';
        for (var i = 0; i < dataObjectModel.length; i += 1) {
            tableBodyHtml += '<tr>';
            for (var property in dataObjectModel[i]) {
                tableBodyHtml += '<td>' + dataObjectModel[i][property] + '</td>';
            }
            tableBodyHtml += '</tr>';
        }
        console.log(tableBodyHtml);
        tableBody.innerHTML = tableBodyHtml;
    }
}
xhrAddressesRequest.open('GET', 'http://localhost:8080/api/person/phone/123124?useSSL=false');
xhrAddressesRequest.send();