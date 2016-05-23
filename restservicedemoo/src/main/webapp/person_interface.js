/**
 * Created by elzoy on 5/23/2016.
 */

$().ready(function() {
    var personsList = $("#personsList table");
    var personADD = $(".add");
    var personVIEW = $(".view");
    var personAddContainer = $("#personAddContainer");
    var personAddSubmit = $(".personAddSubmit");
    var personDetails = $("#personDetails table");
    personAddContainer.hide();
    personsList.hide();
    //personDetails.hide();
    personVIEW.click(function() {
        printList();
    });

    personADD.click(function() {
        personAddContainer.show();
        personsList.hide();
        personAddContainer.append(
            '<p><input type="text" name="personFirstName"></p>' +
            '<p><input type="text" name="personYOB"></p>' +
            '<p><input type="submit" onclick="addperson()" value="Add"></p>'
        )
    });

    personAddSubmit.click(function() {
        alert("poszlo");
    });

    function printList() {
        $.ajax({
            url: 'api/person/all',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            async: false,
            success: function (data) {
                updateList(data);
            }
        });
    }

    function updateDetails(data) {
        personDetails.show();
        if(typeof data.car.length === 'undefined') {
            personDetails.append('<tr>' +
                '<td>' + data.car.make + '</td>' +
                '<td>' + data.car.model + '</td>' +
                '<td>' + data.car.yop + '</td>' +
                '<td>' + data.car.owner.firstName + '</td>' +
                '<td><span class="remove" onclick="removeCar(\'' + data.car.id + '\')">Remove</span></td>' +
                '</tr>');
        }
        for(var i = 0; i < data.car.length; i++) {
            personDetails.append('<tr>' +
                '<td>' + i + '</td>' +
                '<td>' + data.car[i].make + '</td>' +
                '<td>' + data.car[i].model + '</td>' +
                '<td>' + data.car[i].yop + '</td>' +
                '<td>' + data.car[i].owner.firstName + '</td>' +
                '<td><span class="remove" onclick="removeCar(\'' + data.car[i].id + '\')">Remove</span></td>' +
                '</tr>');
        }
    }


    function updateList(data) {
        personsList.show();
        personAddContainer.hide();
        
        personsList.append("<tr>" +
            "<th>#</th>" +
            "<th>Name</th>" +
            "<th>YOB</th>" +
            "</tr>");
        if(typeof data.person.length === 'undefined') {
            personsList.append('<tr>' +
                '<td>' + 1 + '</td>' +
                '<td><span class="active" data-target=data.person.id onclick="viewPersonDetails(\'' + data.person.id + '\')">' + data.person.firstName + '</span></td>' +
                '<td>' + data.person.yob + '</td>' +
                '<td><span class="remove" onclick="removeperson(\'' + data.person.id + '\')">Remove</span></td>' +
                '</tr>');
        }
        for(var i = 0; i < data.person.length; i++) {
            personsList.append('<tr>' +
                '<td>' + i + '</td>' +
                '<td><span class="active" onclick="viewPersonDetails(\'' + data.person[i].id + '\')">' + data.person[i].firstName + '</span></td>' +
                '<td>' + data.person[i].yob + '</td>' +
                '<td><span class="remove" onclick="removeperson(\'' + data.person[i].id + '\')">Remove</span></td>' +
                '</tr>');
        }
    }
    $(".active").click(function() {
        alert($(this).data("target"));
    });
});

function addperson(person) {
    var personToAdd = {
        "firstName": $("input[name='personFirstName']").val(),
        "yob": $("input[name='personYOB']").val()
    };

    $.ajax({
        url: 'api/person/add',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(personToAdd),
        async: false,
        success: function () {
        }
    });
}

function viewPersonDetails(id) {
    //personDetails.show();
    var something = {
        "id": id
    };

    $.ajax({
        url: 'api/car/byOwner',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(something),
        async: false,
        success: function (data) {
            //personDetails.show();
            alert(JSON.stringify(data.car));
        }
    });
}

function removeperson(id) {
    var personToRemove = {
        "id": id
    };

    $.ajax({
        url: 'api/person/remove',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(personToRemove),
        async: false,
        success: function (data) {
            updateList(data);
        }
    });
}