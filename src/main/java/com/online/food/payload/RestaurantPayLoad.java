package com.online.food.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RestaurantPayLoad {


    private String restaurantName;

    private String customerName;

    private String customerEmail;

    private Long restaurantPhoneNumber;

    private String customerPassword;

    private Long cityId;

    private Long areaId;

    private String restaurantAddress;
}
