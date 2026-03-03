package com.cap.springDto.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {

    private Integer id;
    private String name;
    private String email;
    private String course;

}