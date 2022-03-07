package uz.pdp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Attachment;
import uz.pdp.model.ApiResponse;
import uz.pdp.model.AttachmentDto;

public interface AttachmentService {
    AttachmentDto upload(MultipartFile file);

    ResponseEntity<ApiResponse<Attachment>> validate(Long id);
}
