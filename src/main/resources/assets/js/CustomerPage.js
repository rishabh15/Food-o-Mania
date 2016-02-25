/**
 * Created by nitish.ojha on 25/02/16.
 */


var CustomerPage = {
    menu: {},
    count: 0,
    order: [{
        item_merchant_id:"",
        item_id:"",
        item_name:"",
        item_count:0,
        item_price:0
    }]
}

$( document ).ready(function() {

    console.log("called document ready");
    $.ajax({
        url: "http://localhost:10000/api/foodmania/merchant/getMenu",
        type: "GET",
        success: function (data) {
        console.log(data);
        CustomerPage.menu=data;
            var merchantName=0
            $.each(data, function (i, item) {
                var container = $('#customer_pages');
                merchantnAME=item.merchantInfoEntity.merchantid;
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
                        onclick: 'add('+JSON.stringify(item)+','+JSON.stringify(merchant)+')'
                    }).appendTo(container);
                })
            })
        }
    });
})

function add(item,merhantid)
{
    CustomerPage.count++;
    console.log(item.itemdesc);
    console.log(item.itemid)
    console.log(merhantid);
    $('#pending-hi_tl-badge').text(CustomerPage.count);
    CustomerPage.order[item.itemid].item_id=item.itemid
    CustomerPage.order[item.itemid].item_name =item.itemdesc
    CustomerPage.order[item.itemid].item_count++
    CustomerPage.order[item.itemid].item_price=item.price
    CustomerPage.order[item.itemid].item_merchant_id=merhantid
}

function addItemRow(itemdesc, price, count,time) {
    var table = $('#carttable')
    table.append('<tr><td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + itemdesc + '</label>' +
    '</div> </td> <td <div class=\"col-sm-2\">' +
    '<label class="control-label">' + price + '</label>' +
    '</div> </td><td <div class=\"col-sm-2\">' +
  //  '<input type="number" class="control-label col-xs-3"  value="count+" >' + '</label>' +
     '<input type="number" id="item_count" min="0" max="5" value='+count+'>' + '</label>' +
    '</div> </td> <td <div class=\"col-sm-2\">' +
    '<input type="time">' + '</label>' +
    '</div> </td></tr>');

}

function show_cart()
{
    $('#carttable').empty();

    Object.keys(CustomerPage.order).forEach(function (key) {
        var item = CustomerPage.order[key]
            if(item.item_count>0)
            {
            addItemRow(item.item_name, item.item_price * item.item_count, item.item_count);
            }
        })

}