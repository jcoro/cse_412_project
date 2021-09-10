package com.example.cse_412_project.service.impl;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.NutDataKey;
import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.exceptions.FoodDescriptionNotFoundException;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.NutrientDataRepository;
import com.example.cse_412_project.service.ParseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class ParseDataServiceImpl implements ParseDataService {

    @Autowired
    private FoodDescriptionRepository foodDescriptionRepository;

    @Autowired
    private NutrientDataRepository nutrientDataRepository;

    public List<FoodDescription> parseFoodDescription(String filePath) {
        List<FoodDescription> list = new LinkedList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] foodDesFields = line.split("\\^");
                FoodDescription foodDescription = new FoodDescription();
                foodDescription.setNdbNo(Integer.parseInt(Objects.requireNonNull(getActualData(foodDesFields[0]))));
                foodDescription.setFdGrpCd(Integer.parseInt(Objects.requireNonNull(getActualData(foodDesFields[1]))));
                foodDescription.setLongDesc(getActualData(foodDesFields[2]));
                foodDescription.setShortDesc(getActualData(foodDesFields[3]));
                list.add(foodDescription);
            }
            list.forEach(foodDes -> {
                Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(foodDes.getNdbNo());
                if (!foodDescription.isPresent()) {
                    foodDescriptionRepository.save(foodDes);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NutrientData> parseNutrientData(String filePath) throws FoodDescriptionNotFoundException {
        List<NutrientData> list = new LinkedList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] nutDataFields = line.split("\\^");
                NutrientData nutrientData = new NutrientData();
                nutrientData.setNutDataKey(new NutDataKey(
                    Integer.parseInt(Objects.requireNonNull(getActualData(nutDataFields[0]))),
                    Integer.parseInt(Objects.requireNonNull(getActualData(nutDataFields[1])))
                ));
                nutrientData.setNutrVal(Float.parseFloat(Objects.requireNonNull(getActualData(nutDataFields[2]))));
                list.add(nutrientData);
            }
            for (NutrientData nutrientData : list) {
                final int ndbNo = nutrientData.getNutDataKey().getNdbNo();
                Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(ndbNo);
                if (!foodDescription.isPresent()) {
                    throw new FoodDescriptionNotFoundException(String.format("Food description with nbdNo (%d) does not exist", ndbNo));
                }
                foodDescription.get().getNutDataList().add(nutrientData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getActualData(String foodDesField) {
        if (foodDesField.equals("^^") || foodDesField.equals("~~")) {
            return null;
        }
        return foodDesField.substring(1, foodDesField.length() - 1);
    }
}
