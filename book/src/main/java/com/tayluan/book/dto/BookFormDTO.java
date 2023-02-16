package com.tayluan.book.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookFormDTO {
    @NotBlank(message = "This field may not be empty or null")
    private String bookName;
    @NotBlank(message = "This field may not be empty or null")
    private String author;
    @NotNull(message = "This field may not be empty or null")
    @Digits(integer = 6 , fraction = 2)
    private BigDecimal price;
}
