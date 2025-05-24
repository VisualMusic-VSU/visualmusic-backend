package app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.common.JpaReferenceItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class JpaGenre extends JpaReferenceItem {
}
