package by.sam_solutions.findtrip.service.impl;


import by.sam_solutions.findtrip.controller.dto.CityDTO;
import by.sam_solutions.findtrip.controller.dto.CompanyDTO;
import by.sam_solutions.findtrip.controller.dto.CountryDTO;
import by.sam_solutions.findtrip.controller.dto.PlaneDTO;
import by.sam_solutions.findtrip.exception.EditCityParametersExistException;
import by.sam_solutions.findtrip.exception.EditPlaneParametersExistException;
import by.sam_solutions.findtrip.repository.CompanyRepository;
import by.sam_solutions.findtrip.repository.PlaneRepository;
import by.sam_solutions.findtrip.repository.entity.CityEntity;
import by.sam_solutions.findtrip.repository.entity.PlaneEntity;
import by.sam_solutions.findtrip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl  implements PlaneService {

    @Autowired
    PlaneRepository planeRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Override
    public PlaneEntity add(PlaneEntity plain) {
        PlaneEntity savedPlain = planeRepository.save(plain);
        return savedPlain;
    }

    @Override
    public PlaneEntity update(PlaneEntity plain) {
        return null;
    }

    @Override
    public List<PlaneEntity> getAll() {
        return null;
    }



    @Override
    public PlaneEntity findById() {
        return null;
    }


    @Transactional
    @Override
    public PlaneDTO findOne(Long id) {
        Optional<PlaneEntity> planeEntity =  planeRepository.findById(id);

        if(planeEntity.isPresent()){
            PlaneDTO planeDTO = new PlaneDTO();
            planeDTO.setId(planeEntity.get().getId());
            planeDTO.setName(planeEntity.get().getName());
            planeDTO.setSideNumber(planeEntity.get().getSideNumber());

            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(planeEntity.get().getCompany().getName());
            companyDTO.setId(planeEntity.get().getCompany().getId());
            planeDTO.setCompanyDTO(companyDTO);

            return planeDTO;
        }else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        planeRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(PlaneDTO planeDTO, Long companyId, String  companyName) {

        Long idExistPlane = this.getPlaneIdBySideNumber(planeDTO.getSideNumber());

        if (planeDTO.getId() != null) {

            PlaneEntity planeEntityEdit = planeRepository.findById(planeDTO.getId()).get();
            if(planeEntityEdit != null) {
                if (idExistPlane != null && idExistPlane != planeDTO.getId()) {
                    throw new EditPlaneParametersExistException(
                            "Plane_with_this_side_number_already_exist", planeDTO, companyName, companyId);
                }
                planeEntityEdit.setName(planeDTO.getName());
                planeEntityEdit.setSideNumber(planeDTO.getSideNumber());
                planeRepository.save(planeEntityEdit);
            }else {
                throw new EntityNotFoundException("Plane with id="+planeDTO.getId()+" not found");
            }

        } else {
            if (idExistPlane != null && idExistPlane != planeDTO.getId()) {
                throw new EditPlaneParametersExistException(
                        "Plane_with_this_side_number_already_exist",planeDTO,companyName,companyId );
            }
            PlaneEntity planeEntity = new PlaneEntity(
                    planeDTO.getName(),planeDTO.getSideNumber(), companyRepository.findById(companyId).get());
            planeRepository.save(planeEntity);
        }
    }

    private Long getPlaneIdBySideNumber(String sideNumber) {
       return planeRepository.findIdBySideNumber(sideNumber);
    }
}
