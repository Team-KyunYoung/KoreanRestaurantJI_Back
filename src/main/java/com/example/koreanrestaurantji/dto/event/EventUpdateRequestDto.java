package com.example.koreanrestaurantji.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateRequestDto {
    private String eventTitle;
    private String eventImage;
    private String eventContents;
}
