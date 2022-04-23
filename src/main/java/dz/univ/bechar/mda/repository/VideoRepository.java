package dz.univ.bechar.mda.repository;

import dz.univ.bechar.mda.entity.User;
import dz.univ.bechar.mda.entity.Video;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VideoRepository extends PagingAndSortingRepository<Video,Long>,CrudRepository<Video,Long> {
    @NotNull
    @Override
    Page<Video> findAll(@NotNull Pageable pageable);
    Page<Video> findByNameContainingIgnoreCase(String name,Pageable pageable);
    boolean existsByCode(String code);

}
