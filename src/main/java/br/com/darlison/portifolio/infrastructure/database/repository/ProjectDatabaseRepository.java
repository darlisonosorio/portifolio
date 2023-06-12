package br.com.darlison.portifolio.infrastructure.database.repository;

import br.com.darlison.portifolio.infrastructure.database.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectDatabaseRepository extends JpaRepository<ProjectEntity, UUID> {

    @Query("SELECT p from tb_project p " +
            "WHERE (:name IS null OR p.name LIKE CONCAT('%', :name, '%') " +
            "OR p.description LIKE CONCAT('%', :name, '%'))")
    Page<ProjectEntity> findAllByName(@Param("name") String name, Pageable pageable);

}
