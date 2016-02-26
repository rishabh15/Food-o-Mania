/**
 * Created by nitish.ojha on 25/02/16.
 */
    var customerId= sessionStorage.getItem('username');
var CustomerPage = {
  order_id:"test",
    userid: "",
    order_count: 0,
    placed_orders: [{
        orderid: "",
        item_name: "",
        status: "OPEN",
        time: ""
    }],

    menu: {},
    count: 0,
    order: [{
        item_merchant_id: null,
        item_merchant_name: "",
        item_id: "",
        item_name: "",
        item_count: 0,
        item_price: 0,
        item_time: ""
    }],
    merchant_order: [{
        merchant_present: 0,
        total_price: 0,
        merchant_name: "",
        merchant_id: "",
        customer_name: "Nitish",
        customer_id: customerId,
        customer_number: 9620307284,
        time: ""
    }],

    merchant: "",


    show_orders: function () {
        $.ajax({
            url: "http://localhost:10000/api/foodmania/customer/getOrderSummary/" + CustomerPage.userid,
            type: "GET",
            contentType: "application/json",
            success: function (data) {
                $.each(data, function (i, item) {
                  console.log("item status is " +item.status );
                    if(item.status=="OPEN") {
                        addItemRowOrder(item.orderId, item.itemNameList, item.time, item.status);
                    }
                    })
            },
            error: function (exception) {
                alert("Cannot Create Order" + exception);
            },
            complete: function () {

            }
        });
    }
}

function addItemRowOrder(order_id,item_name,time, status) {
   CustomerPage.order_id=order_id;
    time1=time.split(":");
    order=order_id
    var table=$('#orderTable')
    table.append('<tr><td<div class=\"col-sm-2\">' +
    '<label class="control-label">' + order_id + '</label>' +
    '</div><td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + item_name + '</label>' +
    '</div> <td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + time + '</label>' +
    '</div> </td> <td <div class=\"col-sm-1\">' +
    '<label class="control-label">' + status + '</label>' +
    '</div> </td><td <div id='+order_id+' value='+order_id+' class=\"col-sm-2\">' +
    '<button id='+order_id+' type="button" class="button-xs" onclick="cancel(' + CustomerPage.order_id+ ','+time1[0]+')">Cancel</button>' +
    '</div></tr>');

}

$( document ).ready(function() {

CustomerPage.userid=customerId;
    console.log("called document ready");
    $.ajax({
        url: "http://localhost:10000/api/foodmania/merchant/getMenu",
        type: "GET",
        success: function (data) {
        console.log(data);
        CustomerPage.menu=data;
            var merchantname=""
            $.each(data, function (i, item) {
                var container = $('#customer_pages');
                console.log(item);
              //  console.log(item.merchantInfoEntity.merchantid);

                CustomerPage.merchant[item.merchantInfoEntity.merchantid] =new Object();
                merchantname=item.merchantInfoEntity.name;
                merchantid=item.merchantInfoEntity.merchantid
                merchantid=item.merchantInfoEntity.merchantid;
                $('<label />', {
                    'class': 'panel',
                    text: item.merchantInfoEntity.name
                }).appendTo(container);

                $.each(item.itemEntities, function (i, item) {
                    CustomerPage.order[item.itemid]=new Object();
                    CustomerPage.order[item.itemid].item_count=0
                    $('<button>', {
                        'class': 'col-sm-3 jumbotron',
                        text: item.itemdesc + ' ' + item.price,
                        onclick: 'add('+JSON.stringify(item)+','+JSON.stringify(merchantname)+','+JSON.stringify(merchantid)+')'
                    }).appendTo(container);
                })
            })
        }
    });

})

function add(item,merchantname,merchantid)
{
    CustomerPage.count++;

    CustomerPage.merchant=merchantname
    console.log("item is and merchantid is",item,merchantid);
    $('#pending-hi_tl-badge').text(CustomerPage.count);
    CustomerPage.order[item.itemid].item_id=item.itemid
    CustomerPage.order[item.itemid].item_name =item.itemdesc
    CustomerPage.order[item.itemid].item_count++
    CustomerPage.order[item.itemid].item_price=item.price
    CustomerPage.order[item.itemid].item_merchant_name=merchantname
    CustomerPage.order[item.itemid].item_merchant_id=merchantid
//    CustomerPage.order[item.ite]

}

function addItemRow(item_id,merchantName,itemdesc, price, count,time,merchant_id,table) {

    var table = $('#carttable')


        table.append('<tr id=' + item_id + '><td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + merchantName + '</label>' +
        '</div> <td <div class=\"col-sm-2\">' +
        '<label class="control-label">' + itemdesc + '</label>' +
        '</div> </td> <td <div class=\"col-sm-1\">' +
        '<label class="control-label">' + price + '</label>' +
        '</div> </td><td <div class=\"col-sm-2\">' +
        '<input type="number" id=item_count' + item_id + ' min="0" max="5" value=' + count + '>' + '</label>' +
        '</div> </td> <td <div class=\"col-sm-1\">' +
        '<button type="button" class="button-xs" onclick="save(' + item_id + ')">save</button>' +
        '</div> </td><td <div class=\"col-sm-1\ id="timepicker3">' +
        '<input type="time" id=time'+item_id+'>' + '</label>' +
        '</div> </td><td <div class=\"col-sm-1\">' +
        '<button type="button" class="button-xs" onclick="remove_row(' + $(this).closest('tr').attr('id') + ',' + item_id + ')"><span class="glyphicon glyphicon-remove"></span></button>' +
        '</div> </td></tr>');


}

function save(item)
{
    var single_price=(CustomerPage.order[item.id].item_price)/CustomerPage.order[item.id].item_count;
    console.log("single price" + single_price);
    var count=document.getElementById('item_count'+item.id).value;

    CustomerPage.order[item.id].item_count=count;
    console.log("count" + count);
    //CustomerPage.order[item.id].item_price=(single_price)*(count);
    console.log("single price and count" + single_price +' '+ count);
    console.log("finalPrice"+ CustomerPage.order[item.id].item_price);
    show_cart()

}
function remove_row(row_id,item)
{
    CustomerPage.count=CustomerPage.count-CustomerPage.order[item.id].item_count;
    CustomerPage.order[item.id].item_count=0
    document.getElementById("carttable").deleteRow(row_id);
}
function show_cart()
{
    $('#carttable').empty();

    Object.keys(CustomerPage.order).forEach(function (key) {
        var item = CustomerPage.order[key]
            if(item.item_count>0)
            {
            addItemRow(item.item_id,item.item_merchant_name,item.item_name, item.item_price * item.item_count, item.item_count,item.merchant_id,$('#carttable'));
            }
        })


}

function place_order() {

    var orders = "";

    var primary = 1
    Object.keys(CustomerPage.order).forEach(function (key) {
        var item = CustomerPage.order[key]

        console.log("final item is", item);
        if (item.item_count > 0) {
            item.item_time = document.getElementById('time' + item.item_id).value;


            if(item.item_time=="")
            {
                alert("Please Fill Time");
                return;
            }
            var order = "{" + "\n" +
                "\"@id\":\"" + primary + "\",\n" +
                "\"orderid\":\"" + null + "\",\n" +
                "\"price\":" + (item.item_price) * (CustomerPage.order[item.item_id].item_count) + ",\n" +
                "\"time\":\"" + item.item_time + "\",\n" +
                "\"comments\":" + "\"hot and spicy\"" + ",\n" +
                "\"status\":" + "\"OPEN\"" + ",\n" +
                "\"customerEntity\":" + "{\"customerid\":" + "\""+customerId+"\"" + "\n" +
                "},\n" +
                "\"merchantInfoEntity\":" + "{\"merchantid\":" + "\"" + CustomerPage.order[item.item_id].item_merchant_id + "\"" + "\},\n" +
                "\"itemForOrderEntities\":" + "[{\"itemid\":" + "\"" + item.item_id + "\"" + ",\n" +
                "\"itemdesc\":\"" + item.item_name + "\",\n" +
                "\"price\":\"" + item.item_price + "\",\n" +
                "\"count\":\"" + CustomerPage.order[item.item_id].item_count + "\",\n" +
                "\"customerOrderEntity\":\"" + primary + "\"\n" +
                "}]}";

            orders = orders.concat(order).concat(',')

        }
        primary++
    })
    orders = orders.slice(0, -1);
    var final_order = "[" + orders + "]"


    console.log("final_order" + final_order)
    $.ajax({
        url: " http://localhost:10000/api/foodmania/customer/createOrder",
        type: "POST",
        contentType: "application/json",
        data: final_order,
        crossDomain: true,
        success: function (data) {
        },
        error: function (exception) {
            alert("Cannot Create Order" + exception);
        },
        complete: function () {

        }
    });
}


function cancel(id,time)
{


    console.log("time is,id is"+time +"id is"+ CustomerPage.order_id);
    var d = new Date();
    var current_time = d.getHours();
    console.log("current_hour is" + current_time , "time is " + time)
    if(time-current_time>1)
    {
        var json ="{ \"status\": \"CLOSED\""+"}"
        $.ajax({
            url: "http://localhost:10000/api/foodmania/customer/updateOrder/"+CustomerPage.order_id,
            type: "POST",
            contentType: "application/json",
            data: json,
            success: function (data) {
                alert("Successfully Canceled")
                $('#orderTable').empty();
                CustomerPage.show_orders();
            },
            error: function (exception) {
                alert("Cannot Update Order" + exception);
            },
            complete: function () {
            }
        });
    }
    else{
        var json ="{ \"status\": \"CUSTOMER_UNRESPONSIVE\""+"}"
        console.log("json is" + json);
        $.ajax({
            url: "http://localhost:10000/api/foodmania/customer/updateOrder/"+CustomerPage.order_id,
            type: "POST",
            contentType: "application/json",
            data: json,
            success: function (data) {
                alert("You have cancelled very late")
                $('#orderTable').empty();
                CustomerPage.show_orders();
            },

            error: function (exception) {
                alert("Cannot Update Order" + exception);
            },
            complete: function () {
            }
        });
    }
}
function signOut()
{
    window.location = "/login.html";
}
