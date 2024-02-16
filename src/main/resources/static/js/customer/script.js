//get area

$(document).ready(function () {

    $('#citySelect-com').change(function () {
        var selectedCityId = $(this).val();


        $.ajax({
            type: 'GET',
            url: '/order-food/get-areas',
            data: {cityId: selectedCityId},
            success: function (areas) {
                $('#areaSelect-com').empty();
                $('#areaSelect-com').append($('<option>', {
                    value: '',
                    text: 'Select Area'
                }));
                $.each(areas, function (index, area) {
                    $('#areaSelect-com').append($('<option>', {
                        value: area.areaId,
                        text: area.areaName
                    }));
                });
            },
            error: function () {
                console.error('Error fetching areas.');
            }
        });
    });
});


//get restaurant

$(document).ready(function () {

    $('#areaSelect-com').change(function () {
        var selectedAreaId = $(this).val();


        $.ajax({
            type: 'GET',
            url: '/order-food/get-restaurant',
            data: {areaId: selectedAreaId},
            success: function (restaurants) {
                $('#restaurantSelect-com').empty();
                $('#restaurantSelect-com').append($('<option>', {
                    value: '',
                    text: 'Select Restaurant'
                }));
                $.each(restaurants, function (index, restaurant) {
                    $('#restaurantSelect-com').append($('<option>', {
                        value: restaurant.restaurantId,
                        text: restaurant.restaurantName
                    }));
                });
            },
            error: function () {
                console.error('Error fetching areas.');
            }
        });
    });
});






//add complain



$(document).ready(function () {
    $("#do-complain-form").validate({
        rules: {
            complainSubject: {
                required:true,
                minlength: 2

            },
            cityId:{
            required: true
            },
            areaId:{
            required:true
            },restaurantId:{
              required:true
            },
            attachment:{
            required: true
            },
            complainDescription: {
                required: true,
            },
        },
        messages: {
            complainSubject: "Please enter Complain Subject",
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/order-food/add-complain",
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Complain", "success")
                            .then((value) => {
                                window.location = "/order-food/complain";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (xhr, status, error) {

                    console.error("Error saving Complain:", error);
                }
            });
        }
    });
});


//add to cart

$(document).ready(function () {

    $('.add-cart').click(function () {
        var product_id = $(this).data('product_id');
        $.ajax({
            type: 'POST',
            url: '/order-food/add-to-cart',
            data: {
                product_id: product_id,
            },
            success: function (response) {
                Swal.fire("Successfully Add To Cart","", "success");
                    location.reload();
            },
            error: function (xhr, status, error) {
                Swal.fire("Not Added To Cart","","error");
            }
        });
    });
});






//     cart qunatity update
function adjustQuantity(change,cartId) {
    var quantityInput = document.getElementById('cart' + cartId);
    var newQuantity = parseInt(quantityInput.value) + change;
    quantityInput.value = newQuantity;
    $.ajax({
        type: 'POST',
        url: '/order-food/change-quantity',
        data: {
            quantity : newQuantity,
            cartId:cartId
        },
        success: function (response) {
            location.reload();
        },
        error: function (xhr, status, error) {

        }
    });
}



//delete cart
$(document).ready(function () {
    $('.delete-cart').click(function () {
        var cart_id = $(this).data('cart_id');
        $.ajax({
            type: 'POST',
            url: '/order-food/delete-cart',
            data: {
                cart_id: cart_id,
            },
            success: function (response) {

                Swal.fire("Remove Product From Cart","", "success");
                location.reload();
            },
            error: function (xhr, status, error) {
                Swal.fire("Something Went Wrong","","error");
            }
        });
    });
});



$(document).ready(function(){
    $('#add-address-form').validate({
        rules: {
            fullName: {
                required: true
            },
            phoneNumber: {
                required: true,
                digits: true,
                minlength: 10
            },
            city: {
                required: true
            },
            pinCode: {
                required: true,
                digits: true,
                minlength: 6
            },
            houseNo: {
                required: true
            },
            buildingName: {
                required: true
            },
            colony: {
                required: true
            }
        },
        submitHandler: function(form) {
            $.ajax({
                enctype: 'multipart/form-data',
                url: '/order-food/add-address',
                type: 'POST',
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function(data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Address", "success")
                            .then((value) => {
                                window.location = "/order-food/checkout";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function(xhr, status, error) {

                    console.error("error");
                }
            });
        }
    });
});




$(document).ready(function(){
    $('#update-address-form').validate({
        rules: {
            fullName: {
                required: true
            },
            phoneNumber: {
                required: true,
                digits: true,
                minlength: 10
            },
            city: {
                required: true
            },
            pinCode: {
                required: true,
                digits: true,
                minlength: 6
            },
            houseNo: {
                required: true
            },
            buildingName: {
                required: true
            },
            colony: {
                required: true
            }
        },
        submitHandler: function(form) {
            $.ajax({
                enctype: 'multipart/form-data',
                url: '/order-food/update-address',
                type: 'POST',
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function(data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update Address", "success")
                            .then((value) => {
                                window.location = "/order-food/checkout";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function(xhr, status, error) {

                    console.error("error");
                }
            });
        }
    });
});