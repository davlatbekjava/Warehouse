package uz.pdp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.AttachmentContent;
import uz.pdp.entity.Product;
import uz.pdp.helper.MapstructMapper;
import uz.pdp.model.AttachmentDto;
import uz.pdp.repository.AttachmentContentRepository;
import uz.pdp.repository.AttachmentRepository;
import uz.pdp.service.AttachmentService;

import java.io.IOException;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final MapstructMapper mapstructMapper;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository, MapstructMapper mapstructMapper) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public AttachmentDto upload(MultipartFile file) {

        //File dan Attachment yaratib olamiz
        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());

        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent=new AttachmentContent();
        try {
            attachmentContent.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachmentContent.setAttachment(savedAttachment);

        attachmentContentRepository.save(attachmentContent);

        return mapstructMapper.toAttachmentDto(savedAttachment);
    }

    @Override
    public Attachment validate(Long id) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isEmpty()){
            throw new RuntimeException("Product id = " + id + ", not found!");
        }

        return attachmentOptional.get();
    }
}
