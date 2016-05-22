/**
 * Created by elzoy on 5/22/2016.
 */
$().ready(function() {

    var x = {
        "model": "fajny"
    };
    console.log(5);

    $.ajax({
        url: 'api/car/add',
        type: 'POST',
        data: JSON.stringify(x),
        contentType: 'application/json',
        dataType: 'json',
        async: false,
        success: function () {

        }
    });
});