package com.example.koreanrestaurantji.dto.dish;

import com.example.koreanrestaurantji.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DishResponseDto {
    private final String dishName;
    private final String dishPhoto;
    private final int dishPrice;
    private final String dishCategory;
    private final String dishDescription;

    public DishResponseDto(Dish dish) {
        this.dishName = dish.getDishName();
        this.dishPhoto = dish.getDishPhoto();
        this.dishPrice = dish.getDishPrice();
        this.dishCategory = dish.getDishCategory();
        this.dishDescription = dish.getDishDescription();
    }
}
