// for product
$(document).ready(function () {
    $("#add-product-button").click(function () {
        $("#show-products").hide();
        $("#update-product-form").hide();
        $("#add-product-form").show();
    });
});

$(document).ready(function () {
    $("#show-product-button").click(function () {

        $("#update-product-form").hide();
        $("#add-product-form").hide();
        $("#show-products").show();
    });
})
//select category for product
$(document).ready(function () {

    $('#select-category').change(function () {
        var categoryId = $(this).val();


        $.ajax({
            type: 'GET',
            url: '/restaurant/get-sub-category', // Replace with your actual endpoint
            data: { categoryId: categoryId },
            success: function (subCategories) {
                $('#select-subCategory').empty();
                $('#select-subCategory').append($('<option>', {
                    value: '',
                    text: 'Select SubCategory'
                }));
                $.each(subCategories, function (index, subCategory) {
                    $('#select-subCategory').append($('<option>', {
                        value: subCategory.subCategoryId,
                        text: subCategory.subCategoryName
                    }));
                });
            },
            error: function () {
                console.error('Error fetching areas.');
            }
        });
    });
});


//add product data

$(document).ready(function () {
    $("#product-reg-form").validate({
        rules: {
            productName: "required",
            productPrice: {
                required: true,
                number: true
            },
            productDiscription: "required"
        },
        messages: {
            productName: "Please enter product name",
            productPrice: {
                required: "Please enter product price",
                number: "Please enter a valid number"
            },
            productDiscription: "Please enter product description"
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "/restaurant/saveProduct",
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Product", "success")
                            .then((value) => {
                                window.location = "/restaurant/manage-product/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (xhr, status, error) {

                    console.error("Error saving product:", error);
                }
            });
        }
    });
});


//delete product

$(document).ready(function () {
    $('.delete-product').click(function () {

        var productId = $(this).data('product_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this Product.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/restaurant/delete-product',
                    data: {
                        productId: productId
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The Product has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the Product.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The Product has not been deleted.',
                    'error'
                );
            }
        });
    });
});


//get product detail

//get product data
$(document).ready(function () {
    $(".update-product-button").click(function () {
        var product_id = $(this).data('product_id');

        $.ajax({
            url: '/restaurant/get-product',
            type: 'GET',
            data: { product_id: product_id },
            success: function (response) {
                $("#productid-up").val(response.productId);
                $("#productName-up").val(response.productName);
                $("#productPrice-up").val(response.productPrice);
                $("#productDiscription-up").val(response.productDiscription);
                $("#formFile-up").val(response.image);
                $("#show-products").hide();
                $("#update-product-form").show();
            },
            error: function (xhr, status, error) {
                console.error(error);

            }
        });
    });
});


//update product
$(document).ready(function () {
    $('#product-update-form').validate({
        rules: {
            productId: {
                required: true,
            },
            productName: {
                required: true,
            },
            productPrice: {
                required: true,
            },
            productDiscription: {
                required: true,
            },
        },
        submitHandler: function (form) {

            $.ajax({
                type: 'POST',
                enctype: 'multipart/form-data',
                url: "/restaurant/updateProduct",
                data: new FormData(form),
                cache: false,
                processData: false,
                contentType: false,
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update Product", "success")
                            .then((value) => {
                                window.location = "/restaurant/manage-product/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    Swal.fire("Something Went Wrong !!! Try Again!!");
                }
            });
        }
    });
});



//for offer
$(document).ready(function () {
    $("#add-offer-button").click(function () {
        $("#show-offers").hide();
        $("#update-offer-form").hide();
        $("#add-offer-form").show();
    });
});

$(document).ready(function () {
    $("#show-offer-button").click(function () {

        $("#update-offer-form").hide();
        $("#add-offer-form").hide();
        $("#show-offers").show();
    });
});



//add offer

$(document).ready(function () {
    $("#offer-reg-form").validate({
        rules: {
            offerName: {
                required: true,
                minlength: 4
            },
            categoryId: {
                required: true,
            },
            subCategoryId: {
                required: true,
            },
            offerDiscount: {
                required: true,
                minlength: 2,
                number: true
            },

            startDate: {
                required: true,
                date: true
            },
            lastDate: {
                required: true,
                date: true
            },

            offerDescription: {
                required: true,
                minlength: 2
            },

        },
        messages: {
            offerName: "Please enter offer name",
            offerDiscount: {
                required: "Please enter offer discount",
                number: "Please enter a valid number"
            },
            startDate: {
                required: "Please enter a date",
                date: "Please enter a valid date"
            },
            lastDate: {
                required: "Please enter a date",
                date: "Please enter a valid date"
            },


            offerDescription: "Please enter offer description"
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: "/restaurant/add-offer",
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add offer", "success")
                            .then((value) => {
                                window.location = "/restaurant/manage-offer/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (xhr, status, error) {

                    console.error("Error saving offer:", error);
                }
            });
        }
    });
});



//delete offer
$(document).ready(function () {
    $('.delete-offer').click(function () {

        var offer_id = $(this).data('offer_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this Offer.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/restaurant/delete-offer',
                    data: {
                        offer_id: offer_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The Offer has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the Offer.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The Offer has not been deleted.',
                    'error'
                );
            }
        });
    });
});


//get Offer data
$(document).ready(function () {
    $(".update-offer-button").click(function () {
        var offer_id = $(this).data('offer_id');

        $.ajax({
            url: '/restaurant/get-offer',
            type: 'GET',
            data: { offer_id: offer_id },
            success: function (response) {
                $("#offerId-up").val(response.offerId);
                $("#offerName-up").val(response.offerName);
                $("#offerDiscount-up").val(response.offerDiscount);
                $("#startDate-up").val(response.startDate);
                $("#lastDate-up").val(response.lastDate);
                $("#offerDescription-up").val(response.offerDescription);
                $("#show-offers").hide();
                $("#update-offer-form").show();
            },
            error: function (xhr, status, error) {
                console.error(error);

            }
        });
    });
});


//update offer
$(document).ready(function () {
    $("#offer-update-form").validate({
        rules: {
            offerName: {
                required: true,
                minlength: 4
            },
            offerId: {
                required: true,
            },

            offerDiscount: {
                required: true,
                minlength: 2,
                number: true
            },

            startDate: {
                required: true,
                date: true
            },
            lastDate: {
                required: true,
                date: true
            },

            offerDescription: {
                required: true,
                minlength: 2
            },

        },
        messages: {
            offerName: "Please enter offer name",
            offerDiscount: {
                required: "Please enter offer discount",
                number: "Please enter a valid number"
            },
            startDate: {
                required: "Please enter a date",
                date: "Please enter a valid date"
            },
            lastDate: {
                required: "Please enter a date",
                date: "Please enter a valid date"
            },


            offerDescription: "Please enter offer description"
        },
        submitHandler: function (form) {
            $.ajax({
                type: "POST",
                url: "/restaurant/update-offer",
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully update offer", "success")
                            .then((value) => {
                                window.location = "/restaurant/manage-offer/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (xhr, status, error) {

                    console.error("Error update offer:", error);
                }
            });
        }
    });
});


$(document).ready(function () {
    $(".select-status").change(function () {
        var complainId = $(this).data('complain_id');
        var status = $(this).val();

        $.ajax({
            type: 'POST',
            url: '/restaurant/update-complain-status',
            data: { complainId: complainId, status: status },
            success: function (data) {
                Swal.fire("Good job!", "Successfully Update Status", "success");
            },
            error: function (xhr, status, error) {
                console.error("Error Something Went Wrong:", error);
            }
        });
    });
});


$(document).ready(function () {
    $(".button-reply").click(function () {
        var complainId = $(this).data('complain_id');
        $("#reply-"+complainId).show();
    });

});


//replay complain
$(document).ready(function () {
     $(".reply-form-data").validate({

        rules:{
            replydes:{
                required:true,
                minlength:2,
            }
        },
        messages:{
            replydes:{
                required:"must be require!!",
                minlength:"minimum 2 character require"
            }
        },
        submitHandler:function(form){
            $.ajax({
                type: "POST",
                url: "/restaurant/reply-data",
                data: new FormData(form),
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Give Reply", "success")
                            .then((value) => {
                                window.location = "/restaurant/manage-complain/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function (xhr, status, error) {

                    console.error("Error:", error);
                }
            });


        }

     })
})


//for staff
$(document).ready(function () {
    $("#add-staff-button").click(function () {
        $("#show-staffs").hide();
        $("#update-staff-form").hide();
        $("#add-staff-form").show();
    });
});

$(document).ready(function () {
    $("#show-staff-button").click(function () {

        $("#update-staff-form").hide();
        $("#add-staff-form").hide();
        $("#show-staffs").show();
    });
})


//staff from


$(document).ready(function() {
    $('#staff-reg-form').validate({
        rules: {
            staffName: {
                required: true,
                minlength: 4
            },
            staffEmail: {
                required: true,
                email: true
            },
            staffPassword: {
                required: true,
                minlength: 6,
                maxlength: 20,

            },
            password2: {
                required: true,
                equalTo: "#staffPassword"
            },

            phoneNumber:{
                required: true,
                minlength: 10,
                maxlength: 11,
                number:true
            },
            address:{
                required: true,
                minlength: 4,
            },
        },
        messages: {
            customerPassword:   {
                required: "Please enter a password.",
                minlength: "Password must be at least 8 characters long."
            },
            password2: {
                equalTo: "Passwords do not match!"
            },
        },
        submitHandler: function(form) {
            let password = $('#staffPassword').val();
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
                    url: "/restaurant/add-staff",
                    type: 'POST',
                    data: formData,
                    success: function(data, textStatus, jqXHR) {
                        if (data.trim() === 'success') {
                            Swal.fire("Good job!", "Registered Successfully. We are going Redirect to Index Page!", "success")
                                .then((value) => {
                                    window.location = "/restaurant/index";
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

//delete staff


$(document).ready(function () {
    $('.delete-staff').click(function () {

        var staff_id = $(this).data('staff_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to ban this staff.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, Ban it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/restaurant/delete-staff',
                    data: {
                        staff_id: staff_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The Staff has been Baned.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while baning the Staff.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The Staff has not been Baned.',
                    'error'
                );
            }
        });
    });
});



//get Staff data
$(document).ready(function () {
    $(".update-staff-button").click(function () {
        var staff_id = $(this).data('staff_id');

        $.ajax({
            url: '/restaurant/get-staff',
            type: 'GET',
            data: { staff_id: staff_id },
            success: function (response) {
                $("#staffId-up").val(response.id);
                $("#staffName-up").val(response.name);
                $("#phoneNumber-up").val(response.phoneNumber);
                $("#address-up").val(response.address);


                $("#add-staff-form").hide();
                $("#show-staffs").hide();
                $("#update-staff-form").show();
            },
            error: function (xhr, status, error) {
                console.error(error);

            }
        });
    });
});





$(document).ready(function() {
    $('#staff-update-form').validate({
        rules: {
            staffName: {
                required: true,
                minlength: 4
            },
            phoneNumber:{
                required: true,
                minlength: 10,
                maxlength: 11,
                number:true
            },
            address:{
                required: true,
                minlength: 4,
            },
        },
        messages: {

        },
        submitHandler: function(form) {
                let formData = new FormData(form);
                $.ajax({
                    url: "/restaurant/update-staff",
                    type: 'POST',
                    data: formData,
                    success: function(data, textStatus, jqXHR) {
                        if (data.trim() === 'success') {
                            Swal.fire("Good job!", "Updated Successfully. We are going Redirect to Index Page!", "success")
                                .then((value) => {
                                    window.location = "/restaurant/index";
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
    });
});