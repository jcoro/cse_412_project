package com.example.cse_412_project.repositories;

import com.example.cse_412_project.DTO.FoodDescriptionDto;
import com.example.cse_412_project.entities.FoodDescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodDescriptionRepository extends CrudRepository<FoodDescription, Integer> {
    Optional<FoodDescription> findByNdbNo(int ndbNo);

    @Query("SELECT new com.example.cse_412_project.DTO.FoodDescriptionDto(fd.ndbNo, fd.foodGrpCode, fd.longDesc, fd.shortDesc) FROM FoodDescription fd")
    List<FoodDescriptionDto> findAllDto();
}
