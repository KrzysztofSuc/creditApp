package com.creditApp.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {
    private String timestamp;
    private String message;
    private String details;
}
