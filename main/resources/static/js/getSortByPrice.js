$("#sortByPrice").click(function(event){

        $.ajax({
            type : "GET",
            url : "api/apartment/all?sort=price",
            success: function(result){
                if(result.status === "Done"){
                    $('#tbodyId tr').empty();
                    $.each(result.data, function(i, apart){
                        var customer = "<tr><td>" +
                            apart.name +
                            "</td><td>" +
                            apart.description +
                            "</td><td>" +
                            apart.price +
                            "</td><td><a href=\"" +
                            "/buyApartment/" +
                            apart.id +
                            "\" class=\"btn btn-primary\">" +
                            "Buy" +
                            "</a></td></tr>";
                        $('#tbodyId').append(customer)
                    });
                    console.log("Success: ", result);
                }else{
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                console.log("ERROR: ", e);
            }
        });
})