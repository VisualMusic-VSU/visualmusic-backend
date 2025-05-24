package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGroup;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaSavedGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaSavedGroupRepository extends JpaRepository<JpaSavedGroup, Long> {
    @Query("""
                select scg.group
                from JpaSavedGroup scg
                where scg.userId = :userId
                    and scg.group.isPrivate = false
            """)
    Page<JpaGroup> findAllGroupsByUserId(@Param("userId") long userId, Pageable pageable);

    @Query("""
                select scg.group
                from JpaSavedGroup scg
                where scg.userId = :userId
                    and scg.group.id = :groupId
                    and scg.group.isPrivate = false
            """)
    Optional<JpaGroup> findGroupByUserId(@Param("userId") Long userId, @Param("groupId") Long groupId);

    void deleteByUserIdAndGroupId(Long userId, Long groupId);

    boolean existsByUserIdAndGroupId(Long userId, Long groupId);
}
