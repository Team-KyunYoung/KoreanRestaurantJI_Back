package com.example.koreanrestaurantji.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FAQUpdateRequestDto {
    private String questionTitle;
    private String questionContents;
}
