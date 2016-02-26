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
            var order = data;//data.customerOrderEntities;
            console.log(data);
            for (var i = 0; i < order.length; i++) {
                addRow(order[i].orderId, order[i].status, order[i].time, order[i].price, order[i].name, order[i].phone) ;
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
    table.append('<tr onclick=\"onRowClick(\''+orderId+'\',\''+status+'\')\" style=\"background-color:'+background+'\"><td <div class=\"col-sm-2\">' +
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

function onRowClick(orderId, status) {
    $.ajax({
        url: "api/foodmania/customer/getOrderItem/"+orderId,
        type: "GET",
        contentType: "application/json",
        async: false,
        success: function (data) {
            document.getElementById("orderId").innerHTML = orderId;
            var table = $('#itemlist');
            table.empty();
            table.append('<thead>' +
                '<tr>'+
                '<th>Item Name</th>'+
            '<th>Price</th>'+
            '<th>Count</th>'+
            '</tr>'+
            '</thead>');
            var order = data.itemForOrderEntities;
            console.log(data);
            for (var i = 0; i < order.length; i++) {
                addItemRow(order[i].itemdesc, order[i].price, order[i].count);
            }
            $("#statuslist").html(status+"<span class=\"caret\"></span>")
            $('#itemModal').modal();
        },
        error: function () {
            alert("Error in finding Order List!!");
        },
        complete: function () {
        }
    })
}

function addItemRow(itemdesc, price, count) {
    var table = $('#itemlist')
    table.append('<tr><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + itemdesc + '</label>' +
        '</div>  </td> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + price + '</label>' +
        '</div>  </td><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + count + '</label>' +
        '</div>  </td></tr>');
}

//Dropdown box
$(document).on('click', '#statuslistdropdown li a', function() {
    var jobName = $(this).text();
    $("#statuslist").html(jobName+"<span class=\"caret\"></span>")
});

function updateOrderStatus() {
    var orderId = $("#orderId").html();//document.getElementById("orderId").textContent
    var status = document.getElementById("statuslist").firstChild.data.trim();
    var json = "{\"status\":\""+status+"\"}"
    $.ajax({
        url: "/api/foodmania/customer/updateOrder/"+orderId,
        type: "POST",
        contentType: "application/json",
        data: json,
        success: function (data) {
            alert("status updated Successfully");
            $('#itemModal').modal('hide');
        },
        error: function () {
            alert("Error in updating status!!");
        },
        complete: function () {
        }
})
}

function addMenu() {
    $('#menuModal').modal();
}

function csvFile() {
    /*var x = document.getElementById("myFile");
    var txt = "";
    if ('files' in x) {
        if (x.files.length == 0) {
            txt = "Select one or more files.";
        } else {
            for (var i = 0; i < x.files.length; i++) {
                txt += "<br><strong>" + (i+1) + ". file</strong><br>";
                var file = x.files[i];
                if ('name' in file) {
                    txt += "name: " + file.name + "<br>";
                }
                if ('size' in file) {
                    txt += "size: " + file.size + " bytes <br>";
                }
            }
        }
    }
    else {
        if (x.value == "") {
            txt += "Select one or more files.";
        } else {
            txt += "The files property is not supported by your browser!";
            txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead.
        }
    }
    document.getElementById("demo").innerHTML = txt;*/
    var itemTable = $('#menuItemlist');
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
    if (regex.test($("#myFile").val().toLowerCase())) {
        if (typeof (FileReader) != "undefined") {
            var reader = new FileReader();
            reader.onload = function (e) {
                var table = $("<table />");
                var rows = e.target.result.split("\n");
                for (var i = 0; i < rows.length; i++) {
                    var row = $("<tr />");
                    var cells = rows[i].split(",");
                    for (var j = 0; j < cells.length; j++) {
                        var cell = $("<td />");
                        cell.html(cells[j]);
                        row.append(cell);
                        console.log(cells[j]);
                    }
                    table.append(row);
                    itemTable.append(row);
                }
                //$("#dvCSV").html('');
                //$("#dvCSV").append(table);
            }
            reader.readAsText($("#myFile")[0].files[0]);
        } else {
            alert("This browser does not support HTML5.");
        }
    } else {
        alert("Please upload a valid CSV file.");
    }
}



function SignoutMerchant()
{
    window.location = "/login.html";
}