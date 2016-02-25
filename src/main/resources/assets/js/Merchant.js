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
            var order = data.customerOrderEntities;
            console.log(data);
            for (var i = 0; i < order.length; i++) {
                addRow(order[i].orderid, order[i].status, order[i].time, order[i].price, order[i].customerEntity.name, order[i].customerEntity.phone) ;
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
    } else if (status=="CLOSED") {
        background = "grey";
    } else {
        background = "red";
    }
    var table = $('#orderlist');
    table.append('<tr onclick=\"onRowClick(\''+orderId+'\')\" style=\"background-color:'+background+'\"><td <div class=\"col-sm-2\">' +
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

function onRowClick(orderId) {
    $.ajax({
        url: "api/foodmania/customer/getOrderItem/"+orderId,
        type: "GET",
        contentType: "application/json",
        async: false,
        success: function (data) {
            var order = data.itemForOrderEntities;
            console.log(data);
            for (var i = 0; i < order.length; i++) {
                addItemRow(order[i].itemid, order[i].itemdesc, order[i].price, order[i].count) ;
            }
            $('#itemModal').modal();
        },
        error: function () {
            alert("Error in finding Job List!!");
        },
        complete: function () {
        }
    })
}

function addItemRow(itemid, itemdesc, price, count) {
    var table = $('#itemlist')
    document.getElementById("orderId").innerText= orderId;
    table.append('<tr><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + itemid + '</label>' +
        '</div>  </td> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + itemdesc + '</label>' +
        '</div>  </td> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + price + '</label>' +
        '</div>  </td><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + count + '</label>' +
        '</div>  </td></tr>');
}
