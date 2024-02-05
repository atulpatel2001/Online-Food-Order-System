const body = document.querySelector("body"),
      modeToggle = body.querySelector(".mode-toggle");
      sidebar = body.querySelector("nav");
      sidebarToggle = body.querySelector(".sidebar-toggle");

let getMode = localStorage.getItem("mode");
if(getMode && getMode ==="dark"){
    body.classList.toggle("dark");
}

let getStatus = localStorage.getItem("status");
if(getStatus && getStatus ==="close"){
    sidebar.classList.toggle("close");
}

modeToggle.addEventListener("click", () =>{
    body.classList.toggle("dark");
    if(body.classList.contains("dark")){
        localStorage.setItem("mode", "dark");
    }else{
        localStorage.setItem("mode", "light");
    }
});

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


      $(document).ready(function () {
        $("#update-city-button").click(function () {
          $("#show-city").hide();
          $("#update-city-form").show();
        });
      });


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



