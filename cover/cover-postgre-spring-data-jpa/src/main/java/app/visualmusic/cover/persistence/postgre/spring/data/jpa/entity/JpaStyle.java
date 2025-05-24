package app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.common.JpaReferenceItem;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class JpaStyle extends JpaReferenceItem {
}
