const body = document.querySelector("body"),
      modeToggle = body.querySelector(".mode-toggle");
      sidebar = body.querySelector("nav");
      sidebarToggle = body.querySelector(".sidebar-toggle");

//let getMode = localStorage.getItem("mode");
//if(getMode && getMode ==="dark"){
//    body.classList.toggle("dark");
//}

let getStatus = localStorage.getItem("status");
if(getStatus && getStatus ==="close"){
    sidebar.classList.toggle("close");
}

//modeToggle.addEventListener("click", () =>{
//    body.classList.toggle("dark");
//    if(body.classList.contains("dark")){
//        localStorage.setItem("mode", "dark");
//    }else{
//        localStorage.setItem("mode", "light");
//    }
//});

sidebarToggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
    if(sidebar.classList.contains("close")){
        localStorage.setItem("status", "close");
    }else{
        localStorage.setItem("status", "open");
    }
})



      $(document).ready(function () {
        $("#add-city").click(function () {
          $("#show-city").hide();
          $("#add-city-form").show();
        });
      });


//      $(document).ready(function () {
//        $("#update-city-button").click(function () {
//          $("#show-city").hide();
//          $("#update-city-form").show();
//        });
//      });


//add city
$(document).ready(function() {
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

        submitHandler: function(form) {
            let formData = {
                cityName: $('#cityName').val(),  // Adjust the selector based on your actual form field IDs
                cityDiscription: $('#cityDiscription').val(),
            };

            $.ajax({
                url: "/admin/add-cityData",
                type: 'POST',
                data: JSON.stringify(formData),
                contentType: 'application/json',  // Set content type to JSON
                success: function(data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add City", "success")
                            .then((value) => {
                                window.location = "/admin/manage-city/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    Swal.fire("Something Went Wrong !!! Try Again!!");
                }
            });
        }
    });
});

//update city
$(document).ready(function() {
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
            submitHandler: function(form) {
                let formData = {
                    cityId: $('#cityId').val(),
                    cityName: $('#cityName1').val(),
                    cityDiscription: $('#cityDiscription1').val(),
                };

                $.ajax({
                    url: "/admin/update-cityData",
                    type: 'POST',
                    data: JSON.stringify(formData),
                    contentType: 'application/json',
                    success: function(data, textStatus, jqXHR) {
                        if (data.trim() === 'success') {
                            Swal.fire("Good job!", "Successfully Update City", "success")
                                .then((value) => {
                                    window.location = "/admin/manage-city/0";
                                });
                        } else {
                            Swal.fire("Please Try Again ", data);
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                        Swal.fire("Something Went Wrong !!! Try Again!!");
                    }
                });
            }
        });
    });








    // delete City

      $(document).ready(function () {
                              $('#delete-city').click(function () {

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
                                              url:'/admin/delete-city',
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




//area

  $(document).ready(function () {
        $("#add-area").click(function () {
          $("#show-area").hide();
          $("#add-area-form").show();
        });
      });



//add city
$(document).ready(function() {
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

        submitHandler: function(form) {
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
                success: function(data, textStatus, jqXHR) {
                    if (data.trim() === 'success') {
                        Swal.fire("Good job!", "Successfully Add Area", "success")
                            .then((value) => {
                                window.location = "/admin/manage-area/0";
                            });
                    } else {
                        Swal.fire("Please Try Again ", data);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                    Swal.fire("Something Went Wrong !!! Try Again!!");
                }
            });
        }
    });
});


 // delete area
      $(document).ready(function () {
                              $('#delete-area').click(function () {

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
                                              url:'/admin/delete-area',
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
          $("#add-category-form").show();
        });
      });


      //add category
      $(document).ready(function() {
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

              submitHandler: function(form) {
                  let formData = {
                              categoryName: $('#categoryName').val(),
                              categoryDiscription: $('#categoryDiscription').val()
                          };

                  $.ajax({
                      url: "/admin/add-categoryData",
                      type: 'POST',
                      data: JSON.stringify(formData),
                      contentType: 'application/json',
                      success: function(data, textStatus, jqXHR) {
                          if (data.trim() === 'success') {
                              Swal.fire("Good job!", "Successfully Add Category", "success")
                                  .then((value) => {
                                      window.location = "/admin/manage-category/0";
                                  });
                          } else {
                              Swal.fire("Please Try Again ", data);
                          }
                      },
                      error: function(jqXHR, textStatus, errorThrown) {
                          console.log(jqXHR);
                          Swal.fire("Something Went Wrong !!! Try Again!!");
                      }
                  });
              }
          });
      });



// delete Category
      $(document).ready(function () {
                              $('#delete-category').click(function () {

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
                                              url:'/admin/delete-category',
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
                                                  // Handle error, e.g., display an error message
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
          $("#add-sub-category-form").show();
        });
      });


//add sub category
      $(document).ready(function() {
          $('#sub-category-reg-form').validate({
              rules: {
              categoryId:{
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

              submitHandler: function(form) {
                  let formData = {
                               categoryId :$('#categoryId').val(),
                              subCategoryName: $('#subCategoryName').val(),
                              subCategoryDiscription: $('#subCategoryDiscription').val()
                          };

                  $.ajax({
                      url: "/admin/add-subcategoryData",
                      type: 'POST',
                      data: JSON.stringify(formData),
                      contentType: 'application/json',
                      success: function(data, textStatus, jqXHR) {
                          if (data.trim() === 'success') {
                              Swal.fire("Good job!", "Successfully Add  SubCategory", "success")
                                  .then((value) => {
                                      window.location = "/admin/manage-sub-category/0";
                                  });
                          } else {
                              Swal.fire("Please Try Again ", data);
                          }
                      },
                      error: function(jqXHR, textStatus, errorThrown) {
                          console.log(jqXHR);
                          Swal.fire("Something Went Wrong !!! Try Again!!");
                      }
                  });
              }
          });
      });



// delete Category
      $(document).ready(function () {
                              $('#delete-sub-category').click(function () {

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
                                              url:'/admin/delete-sub-category',
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


