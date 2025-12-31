package com.opencode.alumxbackend.groupchatmessages.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupMessageSearchRequest {

    @NotBlank(message = "query is required")
    @Size(max = 16, message = "query length should be less than 100")
    private String query;

    @Min(value = 0, message = "page must be >= 0")
    @Builder.Default
    private Integer page = 0;

    @Min(value = 1, message = "size must be >= 1")
    @Max(value = 50, message = "size must be <= 50")
    @Builder.Default
    private Integer size = 20;
}
