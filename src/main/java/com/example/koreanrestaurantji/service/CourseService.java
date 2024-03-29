package com.example.koreanrestaurantji.service;

import com.example.koreanrestaurantji.domain.Course;
import com.example.koreanrestaurantji.domain.Dish;
import com.example.koreanrestaurantji.domain.User;
import com.example.koreanrestaurantji.dto.SuccessResponseDto;
import com.example.koreanrestaurantji.dto.course.*;
import com.example.koreanrestaurantji.exception.BaseException;
import com.example.koreanrestaurantji.exception.BaseResponseCode;
import com.example.koreanrestaurantji.repository.CourseRepository;
import com.example.koreanrestaurantji.repository.DishRepository;
import com.example.koreanrestaurantji.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    public User findUserByToken(){
        return userRepository.findByUserEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName())
                .orElseThrow(() -> new BaseException(BaseResponseCode.USER_NOT_FOUND));
    }

    public SuccessResponseDto create(CourseCreateRequestDto courseCreateRequestDto) {
        User user = findUserByToken();
        if(user.isRole()) {
            Dish appetizer = dishRepository.findByDishName(courseCreateRequestDto.getAppetizer()).orElseThrow(() -> new BaseException(BaseResponseCode.DISH_NOT_FOUND));
            Dish entree1 = dishRepository.findByDishName(courseCreateRequestDto.getEntree1()).orElseThrow(() -> new BaseException(BaseResponseCode.DISH_NOT_FOUND));
            Dish entree2 = dishRepository.findByDishName(courseCreateRequestDto.getEntree2()).orElseThrow(() -> new BaseException(BaseResponseCode.DISH_NOT_FOUND));
            Dish entree3 = dishRepository.findByDishName(courseCreateRequestDto.getEntree3()).orElseThrow(() -> new BaseException(BaseResponseCode.DISH_NOT_FOUND));
            Dish dessert = dishRepository.findByDishName(courseCreateRequestDto.getDessert()).orElseThrow(() -> new BaseException(BaseResponseCode.DISH_NOT_FOUND));

            System.out.println(courseCreateRequestDto.getCourseName());

            CourseRequestDto courseRequestDto = CourseRequestDto.builder()
                    .courseName(courseCreateRequestDto.getCourseName())
                    .appetizer(appetizer)
                    .entree1(entree1)
                    .entree2(entree2)
                    .entree3(entree3)
                    .dessert(dessert)
                    .coursePrice(courseCreateRequestDto.getCoursePrice())
                    .build();

            try {
                courseRepository.save(courseRequestDto.toEntity());
            } catch (Exception e) {
                throw new BaseException(BaseResponseCode.FAILED_TO_SAVE_COURSE);
            }
        } else {
            throw new BaseException(BaseResponseCode.METHOD_NOT_ALLOWED);
        }
        return new SuccessResponseDto(HttpStatus.OK);
    }

    public List<CourseAllResponseDto> allCourse() {
        return courseRepository.findAll()
                .stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
    }

    public CourseResponseDto findCourse(Long number) {
        Course course = courseRepository.findByCourseNumber(number).orElseThrow(() -> new BaseException(BaseResponseCode.COURSE_NOT_FOUND));
        return new CourseResponseDto(course);
    }

    public List<CourseSearchResponseDto> searchCourse(CourseSearchRequestDto courseSearchRequestDto) {
        List<Course> courses = courseRepository.findByCourseNameContaining(courseSearchRequestDto.getInput());
        if(courses.isEmpty() || courses == null){
            throw new BaseException(BaseResponseCode.COURSE_NOT_FOUND);
        } else {
            return courses.stream().map(CourseSearchResponseDto::new).collect(Collectors.toList());
        }
    }

    public SuccessResponseDto update(CourseUpdateRequestDto courseUpdateRequestDto) {
        Course course = courseRepository.findByCourseNumber(courseUpdateRequestDto.getCourseNumber()).orElseThrow(() -> new BaseException(BaseResponseCode.COURSE_NOT_FOUND));

        courseRepository.save(courseUpdateRequestDto.toEntity());

        return new SuccessResponseDto(HttpStatus.OK);
    }

    public SuccessResponseDto delete(Long number){
        User user = findUserByToken();
        if(user.isRole()) {
            Course course = courseRepository.findByCourseNumber(number).orElseThrow(() -> new BaseException(BaseResponseCode.COURSE_NOT_FOUND));
            courseRepository.delete(course);
        } else {
            throw new BaseException(BaseResponseCode.METHOD_NOT_ALLOWED);
        }

        return new SuccessResponseDto(HttpStatus.OK);
    }
}
