


$(document).ready(function () {
    $("#add-city").click(function () {
        $("#show-city").hide();
        $("#update-city-form").hide();
        $("#add-city-form").show();
    });
});


      $(document).ready(function () {
        $("#show-city-button").click(function () {

          $("#add-city-form").hide();
          $("#update-city-form").hide();
          $("#show-city").show();
        });
      });


//add city
$(document).ready(function () {
    $('#city-reg-form').validate({
        rules: {
            cityName: {
                required: true,
                minlength: 4
            },
            cityDiscription: {
                required: true,
            },
        },

        submitHandler: function (form) {
            let formData = {
                cityName: $('#cityName').val(),  // Adjust the selector based on your actual form field IDs
                cityDiscription: $('#cityDiscription').val(),
            };

            $.ajax({
                url: "/admin/add-cityData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',  // Set content type to JSON
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add City", "success")
                            .then((value) => {
                                window.location = "/admin/manage-city/0";
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


//get city data
$(document).ready(function () {
    $(".update-city-button").click(function () {
        var cityId = $(this).data('city_id');

        $.ajax({
            url: '/admin/get-city',
            type: 'GET',
            data: { cityId: cityId },
            success: function (response) {
                $("#cityId-up").val(response.cityId);
                $("#cityName-up").val(response.cityName);
                $("#cityDiscription-up").val(response.cityDiscription);
                $("#show-city").hide();
                $("#update-city-form").show();
            },
            error: function (xhr, status, error) {
                console.error(error);

            }
        });
    });
});


//update city
$(document).ready(function () {
    $('#city-update-reg-form').validate({
        rules: {
            cityName: {
                required: true,
                minlength: 4
            },
            cityDiscription: {
                required: true,
            },
        },
        submitHandler: function (form) {
            let formData = {
                cityId: $('#cityId-up').val(),
                cityName: $('#cityName-up').val(),
                cityDiscription: $('#cityDiscription-up').val(),
            };

            $.ajax({
                url: "/admin/update-cityData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update City", "success")
                            .then((value) => {
                                window.location = "/admin/manage-city/0";
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



// delete City

$(document).ready(function () {
    $('.delete-city').click(function () {

        var city_id = $(this).data('city_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this City.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/admin/delete-city',
                    data: {
                        city_id: city_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The City has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        // Handle error, e.g., display an error message
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the City.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The City has not been deleted.',
                    'error'
                );
            }
        });
    });
});




// for area

$(document).ready(function () {
    $("#add-area").click(function () {
        $("#show-area").hide();
         $("#update-area-form").hide();
        $("#add-area-form").show();
    });
});

$(document).ready(function () {
    $("#show-area-button").click(function () {

        $("#add-area-form").hide();
        $("#update-area-form").hide();
         $("#show-area").show();
    });
});



//add city
$(document).ready(function () {
    $('#area-reg-form').validate({
        rules: {
            areaName: {
                required: true,
                minlength: 4
            },
            areaDiscription: {
                required: true,
            },
        },

        submitHandler: function (form) {
            let formData = {
                cityId: $('#cityId').val(),
                areaName: $('#areaName').val(),
                areaDiscription: $('#areaDiscription').val()
            };

            $.ajax({
                url: "/admin/add-AreaData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Area", "success")
                            .then((value) => {
                                window.location = "/admin/manage-area/0";
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


//get area
$(document).ready(function () {
    $(".update-area-button").click(function () {
        var areaId = $(this).data('area_id');

        $.ajax({
            url: '/admin/get-area',
            type: 'GET',
            data: { areaId: areaId },
            success: function (response) {
                $("#cityId-up").val(response.cityId);
                $("#areaId-up").val(response.areaId)
                $("#areaName-up").val(response.areaName);
                $("#city-select").val(response.cityName);
                $("#areaDiscription-up").val(response.areaDiscription);
                $("#show-area").hide();
                $("#update-area-form").show();
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });
});

//update area
$(document).ready(function () {
    $('#area-update-form').validate({
        rules: {
            cityId: {
                required: true,
            },
            areaName: {
                required: true,
                minlength: 3
            },
            areaDiscription: {
                required: true,
                minlength: 3
            },
        },
        submitHandler: function (form) {
            let formData = {
                cityId: $('#cityId-up').val(),
                areaId: $('#areaId-up').val(),
                areaName: $('#areaName-up').val(),
                areaDiscription: $('#areaDiscription-up').val(),
            };

            $.ajax({
                url: "/admin/update-areaData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update Area", "success")
                            .then((value) => {
                                window.location = "/admin/manage-area/0";
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


// delete area
$(document).ready(function () {
    $('.delete-area').click(function () {

        var area_id = $(this).data('area_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this Area.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/admin/delete-area',
                    data: {
                        area_id: area_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The Area has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        // Handle error, e.g., display an error message
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the Area.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The Area has not been deleted.',
                    'error'
                );
            }
        });
    });
});



// for category

$(document).ready(function () {
    $("#add-category").click(function () {
        $("#show-category").hide();
         $("#update-category-form").hide();
        $("#add-category-form").show();
    });
});


$(document).ready(function () {
    $("#show-category-button").click(function () {

        $("#add-category-form").hide();
          $("#update-category-form").hide();
           $("#show-category").show();

    });
});


//add category
$(document).ready(function () {
    $('#category-reg-form').validate({
        rules: {
            categoryName: {
                required: true,
                minlength: 4
            },
            categoryDiscription: {
                required: true,
            },
        },

        submitHandler: function (form) {
            let formData = {
                categoryName: $('#categoryName').val(),
                categoryDiscription: $('#categoryDiscription').val()
            };

            $.ajax({
                url: "/admin/add-categoryData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Category", "success")
                            .then((value) => {
                                window.location = "/admin/manage-category/0";
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

//get category
$(document).ready(function () {
    $(".update-category-button").click(function () {
        var categoryId = $(this).data('category_id');

        $.ajax({
            url: '/admin/get-category',
            type: 'GET',
            data: { categoryId: categoryId },
            success: function (response) {
                $("#categoryId-up").val(response.categoryId);
                $("#categoryName-up").val(response.categoryName);
                $("#categoryDiscription-up").val(response.categoryDiscription);
                $("#show-category").hide();
                $("#update-category-form").show();
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });
});

//update category

//update area
$(document).ready(function () {
    $('#category-update-form').validate({
        rules: {
            categoryId: {
                required: true,
            },
            categoryName: {
                required: true,
                minlength: 3
            },
            categoryDiscription: {
                required: true,
                minlength: 3
            },
        },
        submitHandler: function (form) {
            let formData = {
                categoryId: $('#categoryId-up').val(),
                categoryName: $('#categoryName-up').val(),
                categoryDiscription: $('#categoryDiscription-up').val(),

            };

            $.ajax({
                url: "/admin/update-categoryData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update Category", "success")
                            .then((value) => {
                                window.location = "/admin/manage-category/0";
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

// delete Category
$(document).ready(function () {
    $('.delete-category').click(function () {

        var category_id = $(this).data('category_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this Category.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/admin/delete-category',
                    data: {
                        category_id: category_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The category has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {

                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the Category.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The Category has not been deleted.',
                    'error'
                );
            }
        });
    });
});



//for sub category


$(document).ready(function () {
    $("#add-sub-category").click(function () {
        $("#show-sub-category").hide();
         $("#update-sub-category-form").hide();
        $("#add-sub-category-form").show();
    });
});



$(document).ready(function () {
    $("#show-subcategory-button").click(function () {

        $("#add-sub-category-form").hide();
          $("#update-sub-category-form").hide();
        $("#show-sub-category").show();
    });
});

//add sub category
$(document).ready(function () {
    $('#sub-category-reg-form').validate({
        rules: {
            categoryId: {
                required: true
            },
            subCategoryName: {
                required: true,
                minlength: 4
            },
            subCategoryDiscription: {
                required: true,
            },
        },

        submitHandler: function (form) {
            let formData = {
                categoryId: $('#categoryId').val(),
                subCategoryName: $('#subCategoryName').val(),
                subCategoryDiscription: $('#subCategoryDiscription').val()
            };

            $.ajax({
                url: "/admin/add-subcategoryData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add  SubCategory", "success")
                            .then((value) => {
                                window.location = "/admin/manage-sub-category/0";
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


//get sub category

//get area
$(document).ready(function () {
    $(".update-sub-category").click(function () {
        var subCategoryId = $(this).data('sub_category_id');

        $.ajax({
            url: '/admin/get-sub-category',
            type: 'GET',
            data: { subCategoryId: subCategoryId },
            success: function (response) {
                $("#subCategoryId-up").val(response.subCategoryId);
                $("#categoryId-up").val(response.categoryId)
                $("#select-category-name").val(response.categoryName);
                $("#subCategoryName-up").val(response.subCategoryName);
                $("#subCategoryDiscription-up").val(response.subCategoryDiscription);
                $("#show-sub-category").hide();
                $("#update-sub-category-form").show();
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    });
});

//update sub category
$(document).ready(function () {
    $('#sub-category-update-form').validate({
        rules: {
            categoryId: {
                required: true,
            },
            subCategoryId:{
            required:true,
            },
            subCategoryName: {
                required: true,
                minlength: 3
            },
            subCategoryDiscription: {
                required: true,
                minlength: 3
            },
        },
        submitHandler: function (form) {
            let formData = {
                categoryId: $('#categoryId-up').val(),
                subCategoryId: $('#subCategoryId-up').val(),
                subCategoryName: $('#subCategoryName-up').val(),
                subCategoryDiscription: $('#subCategoryDiscription-up').val(),

            };

            $.ajax({
                url: "/admin/update-sub-categoryData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',
                success: function (data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Update SubCategory", "success")
                            .then((value) => {
                                window.location = "/admin/manage-sub-category/0";
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
// delete sub Category
$(document).ready(function () {
    $('.delete-sub-category').click(function () {

        var sub_category_id = $(this).data('sub_category_id');
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success ml-2',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: 'You are about to delete this SubCategory.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true,
        }).then((result) => {
            if (result.isConfirmed) {

                $.ajax({
                    type: 'POST',
                    url: '/admin/delete-sub-category',
                    data: {
                        sub_category_id: sub_category_id
                    },
                    success: function (response) {

                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                            'The subcategory has been deleted.',
                            'success'
                        );

                        $(this).closest('tr').remove();
                    },
                    error: function (xhr, status, error) {
                        // Handle error, e.g., display an error message
                        swalWithBootstrapButtons.fire(
                            'Error!',
                            'An error occurred while deleting the SubCategory.',
                            'error'
                        );
                    }
                });
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'The SubCategory has not been deleted.',
                    'error'
                );
            }
        });
    });
});


