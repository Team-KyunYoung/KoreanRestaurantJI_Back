package com.example.koreanrestaurantji.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UserSuccessResponseDto {
    private HttpStatus status;
}
