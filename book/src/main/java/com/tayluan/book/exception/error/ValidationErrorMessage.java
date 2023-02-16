package com.tayluan.book.exception.error;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ValidationErrorMessage {
   private String message;
   private String field;
}
