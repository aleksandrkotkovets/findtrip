package by.sum_solutions.findtrip.repository;

import by.sum_solutions.findtrip.repository.entity.CompanyEntity;
import by.sum_solutions.findtrip.repository.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findById(Long id);

    List<CompanyEntity> findAll();

    CompanyEntity findByName(String name);

    Set<CompanyEntity> findByRating(Rating rating);

    CompanyEntity findByNameAndRating(String name, Rating rating);

    void delete(CompanyEntity companyEntity);

    void deleteById(Long id);

}