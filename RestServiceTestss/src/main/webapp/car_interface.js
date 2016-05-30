/**
 * Created by elzoy on 5/23/2016.
 */

$().ready(function() {
    var carsList = $("#carsList table");
    var carADD = $(".add");
    var carVIEW = $(".view");
    var carAddContainer = $("#carAddContainer");
    var carAddSubmit = $(".carAddSubmit");

    carAddContainer.hide();
    carsList.hide();

    carVIEW.click(function() {
        //printList();
    });
    
    carADD.click(function() {
        clearing($("#carAddContainer p"));
        carAddContainer.show();
        carsList.hide();
        carAddContainer.append(
            '<p><input type="text" name="carModel" placeholder="Model"></p>' +
            '<p><input type="number" name="carYOP" min="1900" max="2016" placeholder="Year of production"></p>' +
            '<p><input type="submit" onclick="addCar()" value="Add"></p>'
        )
    });

    function clearing(space) {
        $(space).each(function() {
            $(this).remove();
        })
    }

    (function printList() {
        $.ajax({
            url: 'api/car/all',
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            async: false,
            success: function (data) {
                updateList(data);
            }
        });
    })();

    function updateList(data) {
        console.log(data);
        clearing("#carsList table tr");
        carsList.show();
        carAddContainer.hide();

        carsList.append("<tr>" +
            "<th>&nbsp;</th>" +
            "<th>Model</th>" +
            "<th>YOP</th>" +
            "<th>Owner</th>" +
            "<th>Operations</th>" +
            "</tr>");
        if(typeof data.car.length === 'undefined') {
            carsList.append('<tr>' +
                '<td>' + data.car.model + '</td>' +
                '<td>' + data.car.yop + '</td>' +
                '<td>' + data.car.owner.firstName + '</td>' +
                '<td><span class="remove" onclick="removeCar(\'' + data.car.id + '\')">Remove</span></td>' +
                '</tr>');
        }
        for(var i = 0; i < data.car.length; i++) {
            carsList.append('<tr>' +
                '<td>' + i + '</td>' +
                '<td>' + data.car[i].yop + '</td>' +
                '<td>' + data.car[i].model + '</td>' +
                '<td>' + data.car[i].owner.firstName + '</td>' +
                '<td><span class="remove" onclick="removeCar(\'' + data.car[i].id + '\')">Remove</span></td>' +
                '</tr>');
        }
    }
});

function addCar(car) {
    var carToAdd = {
        "model": $("input[name='carModel']").val(),
        "yop": $("input[name='carYOP']").val()
    };

    $.ajax({
        url: 'api/car/add',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(carToAdd),
        async: false,
        success: function () {

        }
    });
    location.reload();
}

function removeCar(id) {
    var carToRemove = {
        "id": id
    };

    $.ajax({
        url: 'api/car/remove',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(carToRemove),
        async: false,
        success: function (data) {
            updateList(data);
        }
    });
    location.reload();
}