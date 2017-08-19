
Parse.Cloud.beforeSave("Orders", function(request, response) {
  var email="";
  var alert="";
  var currentDate = new Date();
  query = new Parse.Query("Orders");
  query.lessThanOrEqualTo("createdAt", currentDate);
  query.descending("createdAt");
  query.first({
    success: function(result) {
      if(request.object.isNew())
      {
        request.object.set("orderId", (result.get("orderId") + 1));
        var d = new Date();
        var myNewDate=new Date(d.getTime() + (60000*(d.getTimezoneOffset()+330)));
        request.object.set("dateOrdered",myNewDate );

        email = "umang85patel@gmail.com";
        alert = "New Order available."
      }
      else
      {
        email = request.object.get("email");
        var tempStatus = request.object.get("status");
        if(tempStatus == "Pending") {
          alert = "We are on our way to pick up your clothes as you confirmed the order."
        } else if(tempStatus == "Verifying") {
          alert = "We will contact you soon to confirm your order."
        } else if(tempStatus == "Completed") {
          alert = "We have just completed your your order. We are happy to serve you."
        } else if(tempStatus == "Cancelled") {
          alert = "We are sorry but your order has been CANCELLED as we are not able to confirm."
        } else {
          alert = "Track your Order from My Orders page and know live status."
        }
      }

      //Set push query
      var pushQuery = new Parse.Query(Parse.Installation);
      pushQuery.equalTo("email",email);

      //Send Push message
      Parse.Push.send({
        where: pushQuery,
        data: {
          "data": {
          "message":alert,
          "title":"Wasure",
          "uri":".MyOrdersActivity"
          },
          "is_background":false
        }
        },{
      	useMasterKey: true,
        success: function(){
        response.success("Push Success");
        },
        error: function (error) {
        response.error(error);
        }
      });
      response.success();
    },
    error: function() {
      response.error("error");
    }
  });
});

// Parse.Cloud.afterSave( "Orders", function(request) {

//   //nothing
// });

Parse.Cloud.beforeSave("ItemsOrdered", function(request, response) {
  var currentDate = new Date();
  query = new Parse.Query("ItemsOrdered");
  query.lessThanOrEqualTo("createdAt", currentDate);
  query.descending("createdAt");
  query.first({
    success: function(result) {
      request.object.set("itemsOrderedId", (result.get("itemsOrderedId") + 1));
      response.success();
    },
    error: function() {
      response.error("error");
    }
  });
});