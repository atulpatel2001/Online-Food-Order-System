


$(document).ready(function() {
        $('#reg-form').validate({
            rules: {
                customerName: {
                    required: true,
                    minlength: 4
                },
                customerEmail: {
                    required: true,
                    email: true
                },
                customerPassword: {
                    required: true,
                    minlength: 6,
                    maxlength: 20,
                    strongPassword: true
                },
                password2: {
                    required: true,
                    equalTo: "#customerPassword"
                },
            },
            messages: {
                password2: {
                    equalTo: "Passwords do not match!"
                },
            },
            submitHandler: function(form) {
                let password = $('#customerPassword').val();
                let confirmPassword = $('#password2').val();
                if (password.trim() !== confirmPassword.trim()) {
                   Swal.fire(
                     'Password does not match?',
                     'Try Again',
                     'question'
                   )
                } else {
                    let formData = new FormData(form);
                    $.ajax({
                        url: "/doSignup",
                        type: 'POST',
                        data: formData,
                        success: function(data, textStatus, jqXHR) {
                            if (data.trim() === 'success') {
                                Swal.fire("Good job!", "Registered Successfully. We are going Redirect to Login Page!", "success")
                                    .then((value) => {
                                        window.location = "/signin";
                                    });
                            } else {
                                Swal.fire("Please Try Again ",data);
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            Swal.fire("Something Went Wrong !!! Try Again!!");
                        },
                        processData: false,
                        contentType: false
                    });
                }
            }
        });
    });



$(document).ready(function () {

    $('#citySelect').change(function () {
        var selectedCityId = $(this).val();


        $.ajax({
            type: 'GET',
            url: '/get-areas', // Replace with your actual endpoint
            data: {cityId: selectedCityId},
            success: function (areas) {
                $('#areaSelect').empty();
                $('#areaSelect').append($('<option>', {
                    value: '',
                    text: 'Select Area'
                }));
                $.each(areas, function (index, area) {
                    $('#areaSelect').append($('<option>', {
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



$(document).ready(function() {
        $('#res-reg-form').validate({
            rules: {
                customerName: {
                    required: true,
                    minlength: 4
                },
                restaurantName:{
                required: true,
                minlength: 4
                },
                customerEmail: {
                    required: true,
                    email: true
                },
                customerPassword: {
                    required: true,
                    minlength: 6,
                    maxlength: 20,
                },
                restaurantPhoneNumber:{
                 required: true,
                 minlength: 10,
                 maxlength: 11
                },
                password2: {
                    required: true,
                    equalTo: "#customerPassword"
                },
                cityId:{
                required: true
                },
                areaId:{
                    required: true
                },
            },
            messages: {
            restaurantPhoneNumber:{
            required : "must be required with formate"
            },
            cityId:{
              required: "must be required"
            },
            areaId:{
            required: "must be required"
            },


                password2: {
                    equalTo: "Passwords do not match!"
                },
            },
            submitHandler: function(form) {
                let password = $('#customerPassword').val();
                let confirmPassword = $('#password2').val();
                if (password.trim() !== confirmPassword.trim()) {
                   Swal.fire(
                     'Password does not match?',
                     'Try Again',
                     'question'
                   )
                } else {
                    let formData = new FormData(form);
                    $.ajax({
                        url: "/restaurant-signup",
                        type: 'POST',
                        data: formData,
                        success: function(data, textStatus, jqXHR) {
                            if (data.trim() === 'success') {
                                Swal.fire("Good job!", "Registered Successfully. We are going Redirect to Login Page!", "success")
                                    .then((value) => {
                                        window.location = "/signin";
                                    });
                            } else {
                                Swal.fire("Please Try Again ",data);
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR);
                            Swal.fire("Something Went Wrong !!! Try Again!!");
                        },
                        processData: false,
                        contentType: false
                    });
                }
            }
        });
    });






