package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentDto {
    private Long id;
    private String name;
    private Long size;
    private String contentType;
}
