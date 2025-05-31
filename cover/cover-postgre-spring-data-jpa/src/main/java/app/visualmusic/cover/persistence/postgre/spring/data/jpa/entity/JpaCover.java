package app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "covers")
public class JpaCover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cover_group_id", nullable = false)
    private JpaGroup group;

    @Column(name = "bucket", length = 32, nullable = false)
    private String bucket;

    @Column(name = "object_name", length = 64, nullable = false, unique = true)
    private String object;
}
