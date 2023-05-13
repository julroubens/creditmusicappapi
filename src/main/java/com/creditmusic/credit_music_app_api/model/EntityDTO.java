package com.creditmusic.credit_music_app_api.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntityDTO {

    private Long id;

    @Size(max = 255)
    private String name;

}
