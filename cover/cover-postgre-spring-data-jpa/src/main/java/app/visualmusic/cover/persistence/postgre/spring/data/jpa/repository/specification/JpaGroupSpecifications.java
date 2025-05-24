package app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.specification;

import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGroup;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGenre;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaSavedGroup;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

public class JpaGroupSpecifications {

    private JpaGroupSpecifications() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<JpaGroup> excludeUserId(Long userId) {
        return (root, q, cb) ->
                userId == null
                        ? null
                        : cb.notEqual(root.get("userId"), userId);
    }

    public static Specification<JpaGroup> hasPublicAccess() {
        return (root, q, cb) ->
                cb.equal(root.get("isPrivate"), false);
    }

    public static Specification<JpaGroup> hasStyleId(Long styleId) {
        return (root, q, cb) ->
                styleId == null
                        ? null
                        : cb.equal(root.get("style").get("id"), styleId);
    }

    public static Specification<JpaGroup> hasMoodId(Long moodId) {
        return (root, q, cb) ->
                moodId == null
                        ? null
                        : cb.equal(root.get("mood").get("id"), moodId);
    }

    public static Specification<JpaGroup> hasGenreIds(List<Long> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            return null;
        }

        return (root, query, cb) -> {
            Objects.requireNonNull(query);

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<JpaGroup> subRoot = subquery.from(JpaGroup.class);
            Join<JpaGroup, JpaGenre> subGenres = subRoot.join("genres", JoinType.INNER);

            subquery.select(cb.literal(1L))
                    .where(cb.and(
                            cb.equal(root.get("id"), subRoot.get("id")),
                            subGenres.get("id").in(genreIds)
                    ))
                    .groupBy(subRoot.get("id"))
                    .having(cb.equal(cb.countDistinct(subGenres.get("id")), genreIds.size()));

            return cb.exists(subquery);
        };
    }

    public static Specification<JpaGroup> orderByCreatedAtDesc() {
        return (root, query, cb) -> {
            Objects.requireNonNull(query);

            query.orderBy(cb.desc(root.get("createdAt")));

            return cb.conjunction();
        };
    }

    public static Specification<JpaGroup> orderByPopularityDesc() {
        return (root, query, cb) -> {
            Objects.requireNonNull(query);

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<JpaSavedGroup> subRoot = subquery.from(JpaSavedGroup.class);

            subquery.select(cb.count(subRoot.get("id")))
                    .where(cb.equal(subRoot.get("group").get("id"), root.get("id")));
            query.orderBy(cb.desc(subquery));

            return cb.conjunction();
        };
    }
}
