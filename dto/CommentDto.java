package kz.medet.onlineshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommentDto {
    private Long id;
    private String comment;
}
