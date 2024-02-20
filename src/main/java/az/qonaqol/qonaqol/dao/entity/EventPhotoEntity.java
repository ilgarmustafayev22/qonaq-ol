package az.qonaqol.qonaqol.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_photos")
public class EventPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @NotBlank
    private String url;

    //@ManyToOne
    //@JoinColumn(name = "event_id", referencedColumnName = "id")
    //private EventEntity event;

}
