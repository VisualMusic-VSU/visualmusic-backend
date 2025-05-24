package app.visualmusic.cover.port.output.spring.data.jpa;

import app.visualmusic.cover.domain.Group;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.entity.JpaGroup;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaGroupRepository;
import app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.JpaSavedGroupRepository;
import app.visualmusic.cover.port.output.GroupOutputPort;
import app.visualmusic.cover.port.output.spring.data.jpa.mapper.JpaGroupMapper;
import app.visualmusic.cover.shared.dto.PageResponse;
import app.visualmusic.cover.shared.dto.group.GroupFiltersRequest;
import app.visualmusic.cover.shared.param.GroupSortParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static app.visualmusic.cover.persistence.postgre.spring.data.jpa.repository.specification.JpaGroupSpecifications.*;

@Repository
@RequiredArgsConstructor
public class JpaGroupRepositoryAdapter implements GroupOutputPort {
    private final JpaGroupRepository jpaGroupRepository;
    private final JpaSavedGroupRepository jpaSavedGroupRepository;

    private final JpaGroupMapper mapper;

    @Override
    public PageResponse<Group> findAll(
            int page, int size,
            GroupFiltersRequest filters
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        Specification<JpaGroup> spec = Specification
                .where(getSpecificationWithFilters(filters));

        Page<JpaGroup> result = jpaGroupRepository.findAll(spec, pageable);
        return mapper.toPage(result);
    }

    @Override
    public PageResponse<Group> findAllPublic(
            Long userId,
            int page, int size,
            GroupSortParams sort,
            GroupFiltersRequest filters
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<JpaGroup> spec = Specification
                .where(excludeUserId(userId))
                .and(hasPublicAccess())
                .and(getSpecificationWithFilters(filters))
                .and(getSpecificationWithSort(sort));

        Page<JpaGroup> result = jpaGroupRepository.findAll(spec, pageable);
        return mapper.toPage(result);
    }

    private Specification<JpaGroup> getSpecificationWithFilters(GroupFiltersRequest filters) {
        if (filters == null) {
            return null;
        }

        return Specification
                .where(hasStyleId(filters.getStyleId()))
                .and(hasMoodId(filters.getMoodId()))
                .and(hasGenreIds(filters.getGenreIds()));
    }

    private Specification<JpaGroup> getSpecificationWithSort(GroupSortParams sort) {
        return switch (sort) {
            case POPULAR -> Specification.where(orderByPopularityDesc());
            case CREATED_DATE -> Specification.where(orderByCreatedAtDesc());
        };
    }

    @Override
    public PageResponse<Group> findAllGenerated(long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<JpaGroup> result = jpaGroupRepository.findAllByUserId(userId, pageable);
        return mapper.toPage(result);
    }

    @Override
    public PageResponse<Group> findAllSaved(long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<JpaGroup> result = jpaSavedGroupRepository.findAllGroupsByUserId(userId, pageable);
        return mapper.toPage(result);
    }

    @Override
    public Optional<Group> findGeneratedById(long userId, long groupId) {
        return jpaGroupRepository.findByIdAndUserId(groupId, userId)
                .map(mapper::toModel);
    }

    @Override
    public Optional<Group> findPublicById(long id) {
        return jpaGroupRepository.findByIdAndIsPrivateFalse(id)
                .map(mapper::toModel);
    }

    @Override
    public Optional<Group> findSavedById(long userId, long groupId) {
        return jpaSavedGroupRepository.findGroupByUserId(userId, groupId)
                .map(mapper::toModel);
    }

    @Override
    public long save(Group group) {
        JpaGroup saved = mapper.toEntity(group);

        saved = jpaGroupRepository.save(saved);

        return saved.getId();
    }

    @Override
    public void deleteById(long id) {
        jpaGroupRepository.deleteById(id);
    }

    @Override
    public boolean existsGeneratedById(long userId, long groupId) {
        return jpaGroupRepository.existsByIdAndUserId(groupId, userId);
    }

    @Override
    public boolean existsSavedById(long userId, long groupId) {
        return jpaSavedGroupRepository.existsByUserIdAndGroupId(userId, groupId);
    }

    @Override
    public boolean existsById(long id) {
        return jpaGroupRepository.existsById(id);
    }

    @Override
    public void removeSavedById(long userId, long groupId) {
        jpaSavedGroupRepository.deleteByUserIdAndGroupId(userId, groupId);
    }
}
