$(document).ready(function () {
    $("#add-product-button").click(function () {
        $("#show-products").hide();
        $("#update-product-form").hide();
        $("#add-product-form").show();
    });
});



$(document).ready(function () {

    $('#select-category').change(function () {
        var categoryId = $(this).val();


        $.ajax({
            type: 'GET',
            url: '/restaurant/get-sub-category', // Replace with your actual endpoint
            data: {categoryId: categoryId},
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

$(document).ready(function(){
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
            submitHandler: function(form) {
                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "/restaurant/saveProduct",
                    data: new FormData(form),
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function(data) {
                        if (data.trim() === 'success') {
                                               Swal.fire("Good job!", "Successfully Add Product", "success")
                                                   .then((value) => {
                                                       window.location = "/restaurant/manage-product/0";
                                                   });
                                           } else {
                                               Swal.fire("Please Try Again ", data);
                                           }
                    },
                    error: function(xhr, status, error) {

                        console.error("Error saving product:", error);
                    }
                });
            }
        });
    });