package com.example.cse_412_project.factory.impl;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.NutDataKey;
import com.example.cse_412_project.entities.NutrientData;
import com.example.cse_412_project.exceptions.FoodDescriptionNotFoundException;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.NutrientDataRepository;
import com.example.cse_412_project.service.ParseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class LoadingInitialData implements CommandLineRunner {
    @Autowired
    FoodDescriptionRepository foodDescriptionRepository;

    @Autowired
    private NutrientDataRepository nutrientDataRepository;

    @Override
    public void run(String...args) throws Exception {
        List<FoodDescription> foodDescriptions = parseFoodDescription("Place Holder For Now");
        foodDescriptions.forEach(foodDes -> {
            Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(foodDes.getNdbNo());
            if (!foodDescription.isPresent()) {
                foodDescriptionRepository.save(foodDes);
            }
        });

        List<NutrientData> nutrientData = parseNutrientData("Placeholder for now");
        for (NutrientData nutrientData : list) {
            final int ndbNo = nutrientData.getNutDataKey().getNdbNo();
            Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(ndbNo);
            if (!foodDescription.isPresent()) {
                throw new FoodDescriptionNotFoundException(String.format("Food description with nbdNo (%d) does not exist", ndbNo));
            }
            foodDescription.get().getNutDataList().add(nutrientData);
        }
    }

    private List<FoodDescription> parseFoodDescription(String filePath) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<NutrientData> parseNutrientData(String filePath) throws FoodDescriptionNotFoundException {
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
