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
