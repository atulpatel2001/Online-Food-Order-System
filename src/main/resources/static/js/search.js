

//search city
const searchCity = async () => {
    let query = $("#search-city").val();
    if (query === '') {
        $(".search-result-city").hide();
    } else {
        $.ajax({
            type: "GET",
            url: "/search/search-city/" + query,
            dataType: "json",
            success: function (data) {
                console.log(data);
                let tableHtml = '';
                data.forEach(city => {
                    let table = cityTable(city);
                    tableHtml += table;
                });
                $(".search-result-city").html(tableHtml);
                $(".search-result-city").show();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }
};


function cityTable(city) {
    let table = '<div class="table-responsive m-5">' +
        '<table class="table no-wrap user-table mb-0">' +
        '<tbody>';

    table += '<tr>' +
        '<td class="pl-4">' + city.cityId + '</td>' +
        '<td><h5 class="font-medium mb-0">' + city.cityName + '</h5></td>' +
        '<td><span class="text-muted">' + city.cityDiscription + '</span><br></td>';

    table += '</tr></tbody></table></div>';
    return table;
}


//search area
const searchArea = async () => {
    let query = $("#search-Area").val();
    if (query === '') {
        $(".search-result-area").hide();
    } else {
        $.ajax({
            type: "GET",
            url: "/search/search-area/" + query,
            dataType: "json",
            success: function (data) {
                let tableHtml = '';
                data.forEach(area => {
                    let table = areaTable(area);
                    tableHtml += table;
                });
                $(".search-result-area").html(tableHtml);
                $(".search-result-area").show();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }
};

function areaTable(area) {
console.log(area);
    let table = '<div class="table-responsive m-5">' +
        '<table class="table no-wrap user-table mb-0">' +
        '<tbody>';

    table += '<tr>' +
        '<td class="pl-4">' + area.areaId + '</td>' +
        '<td><span class="text-muted">' + area.city.cityName + '</span><br></td>'+
        '<td><h5 class="font-medium mb-0">' + area.areaName + '</h5></td>' +
        '<td><span class="text-muted">' + area.areaDiscription + '</span><br></td>';

    table += '</tr></tbody></table></div>';
    return table;
}

//search category
const searchCategory = async () => {
    let query = $("#search-category").val();
    if (query === '') {
        $(".search-result-category").hide();
    } else {
        $.ajax({
            type: "GET",
            url: "/search/search-category/" + query,
            dataType: "json",
            success: function (data) {
                console.log(data);
                let tableHtml = '';
                data.forEach(category => {
                    let table = categoryTable(category);
                    tableHtml += table;
                });
                $(".search-result-category").html(tableHtml);
                $(".search-result-category").show();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }
};


function categoryTable(category) {
    let table = '<div class="table-responsive m-5">' +
        '<table class="table no-wrap user-table mb-0">' +
        '<tbody>';

    table += '<tr>' +
        '<td class="pl-4">' + category.categoryId + '</td>' +
        '<td><h5 class="font-medium mb-0">' + category.categoryName + '</h5></td>' +
        '<td><span class="text-muted">' + category.categoryDiscription + '</span><br></td>';

    table += '</tr></tbody></table></div>';
    return table;
}


//search sub category

const searchSubCategory = async () => {
    let query = $("#search-sub-category").val();
    if (query === '') {
        $(".search-result-sub-category").hide();
    } else {
        $.ajax({
            type: "GET",
            url: "/search/search-sub-category/" + query,
            dataType: "json",
            success: function (data) {
                console.log(data);
                let tableHtml = '';
                data.forEach(subCategory => {
                    let table = subCategoryTable(subCategory);
                    tableHtml += table;
                });
                $(".search-result-sub-category").html(tableHtml);
                $(".search-result-sub-category").show();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }
};



function subCategoryTable(subCategory) {
    let table = '<div class="table-responsive m-5">' +
        '<table class="table no-wrap user-table mb-0">' +
        '<tbody>';

    table += '<tr>' +
        '<td class="pl-4">' + subCategory.subCategoryId + '</td>' +
        '<td><h5 class="font-medium mb-0">' + subCategory.category.categoryName + '</h5></td>' +
        '<td><h5 class="font-medium mb-0">' + subCategory.subCategoryName + '</h5></td>' +
        '<td><span class="text-muted">' + subCategory.subCategoryDiscription + '</span><br></td>';

    table += '</tr></tbody></table></div>';
    return table;
}


//for restaurant

const searchRestaurant = async () => {
    let query = $("#search-restaurant").val();
    if (query === '') {
        $(".search-result-restaurant").hide();
    } else {
        $.ajax({
            type: "GET",
            url: "/search/search-restaurant/" + query,
            dataType: "json",
            success: function (data) {
                console.log(data);
                let tableHtml = '';
                data.forEach(restaurant => {
                    let table = restaurantTable(restaurant);
                    tableHtml += table;
                });
                $(".search-result-restaurant").html(tableHtml);
                $(".search-result-restaurant").show();
            },
            error: function (error) {
                console.error("Error fetching data: ", error);
            }
        });
    }
};



function restaurantTable(restaurant) {
    let table = '<div class="table-responsive m-5">' +
        '<table class="table no-wrap user-table mb-0">' +
        '<tbody>';

    table += '<tr>' +
        '<td class="pl-4">' + restaurant.restaurantId + '</td>' +
        '<td><h5 class="font-medium mb-0">' + restaurant.city.cityName + '</h5></td>' +
        '<td><h5 class="font-medium mb-0">' + restaurant.area.areaName + '</h5></td>' +
        '<td><span class="text-muted">' + restaurant.restaurantName + '</span><br></td>'+
        '<td><span class="text-muted">' + restaurant.restaurantAddress + '</span><br></td>'+
        '<td><span class="text-muted">' + restaurant.restaurantPhoneNumber + '</span><br></td>';

    table += '</tr></tbody></table></div>';
    return table;
}
