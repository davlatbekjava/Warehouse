package uz.pdp.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Attachment;
import uz.pdp.model.AttachmentDto;

public interface AttachmentService {
    AttachmentDto upload(MultipartFile file);

    Attachment validate(Long id);
}
