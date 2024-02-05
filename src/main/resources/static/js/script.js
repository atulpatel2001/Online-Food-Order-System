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

