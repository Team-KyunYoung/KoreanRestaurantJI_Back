package com.example.koreanrestaurantji.dto.course;

import com.example.koreanrestaurantji.domain.Course;
import com.example.koreanrestaurantji.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUpdateRequestDto {
    private Long courseNumber;
    private String courseName;
    private Dish appetizer;
    private Dish entree1;
    private Dish entree2;
    private Dish entree3;
    private Dish dessert;
    private int coursePrice;

    public Course toEntity() {
        return Course.builder()
                .courseName(courseName)
                .appetizer(appetizer)
                .entree1(entree1)
                .entree2(entree2)
                .entree3(entree3)
                .dessert(dessert)
                .coursePrice(coursePrice)
                .build();
    }
}
