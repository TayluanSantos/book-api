package com.tayluan.book.dto;

import lombok.*;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookFormDTO {
    private String bookName;
    private String author;
    private BigDecimal price;
}
