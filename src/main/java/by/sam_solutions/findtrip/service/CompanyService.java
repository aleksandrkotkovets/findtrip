package by.sam_solutions.findtrip.service;

import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.repository.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CompanyService {

    @Transactional
    Page<CompanyEntity> findAll(Pageable pageable);

    void deleteById(Long id);
    

    CompanyDTO findOne(Long aLong);

    Long getCompanyIdByName(String name);


    @Transactional
    void save(CompanyDTO companyDTO);

    @Transactional
    void update(CompanyDTO companyDTO);

    CompanyDTO findCompanyByName(String company);
}
