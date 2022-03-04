package uz.pdp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "attachment_content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "attachmnet_id",referencedColumnName = "id")
    private Attachment attachment;

}
