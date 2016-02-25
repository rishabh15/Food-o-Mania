/**
 * Created by rishabh.sood on 25/02/16.
 */
$(document).ready(function() {
    $.ajax({
        url: "api/foodmania/merchant/getOrders/MER001",
        type: "GET",
        contentType: "application/json",
        async: false,
        success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                addRow(data[i].orderid, data[i].status, data[i].time, data[i].price, data[i].customerEntity.name, data[i].customerEntity.phone);
            }
        },
        error: function () {
            alert("Error in finding Job List!!");
        },
        complete: function () {
        }
    })
});

function addRow(orderId, status, time, price, name, phone) {
    console.log(orderId + status + time + price + name + phone);
    var background = "";
    if(status=="OPEN") {
        background = "lightgreen";
    } else if (status=="PROCESSED") {
        background = "yellow";
    }
    var table = $('#orderlist');
    table.append('<tr style=\"background-color:'+background+'\"><td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + orderId + '</label>' +
    '</div>  </td> <td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + status + '</label>' +
    '</div>  </td> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + time + '</label>' +
        '</div>  </td> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + price + '</label>' +
        '</div>  </td><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + name + '</label>' +
        '</div>  </td><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + phone + '</label>' +
        '</div>  </td></tr>')
}
