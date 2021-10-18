package com.example.cse_412_project.factory.impl;

import com.example.cse_412_project.entities.*;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.FoodGroupRepository;
import com.example.cse_412_project.repositories.NutrientDataRepository;
import com.example.cse_412_project.repositories.NutrientDefinitionRepository;
import org.slf4j.LoggerFactory;
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
import org.slf4j.Logger;

@Component
public class LoadInitialData implements CommandLineRunner {
    @Autowired
    private FoodDescriptionRepository foodDescriptionRepository;

    @Autowired
    private NutrientDefinitionRepository nutrientDefinitionRepository;

    @Autowired
    private NutrientDataRepository nutrientDataRepository;

    @Autowired
    private FoodGroupRepository foodGroupRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadInitialData.class);

    @Override
    public void run(String...args) throws Exception {
        List<FoodGroup> foodGroups = parseFoodGroup("Place Holder For Now");
        foodGroups.forEach(foodGrp -> {
            Optional<FoodGroup> foodGroup = foodGroupRepository.findByFoodGrpCode(foodGrp.getFoodGrpCode());
            if (!foodGroup.isPresent()) {
                foodGroupRepository.save(foodGrp);
            }
        });
        logger.info("Completed parsing Food Group");

        List<FoodDescription> foodDescriptions = parseFoodDescription("Place Holder For Now");
        foodDescriptions.forEach(foodDes -> {
            Optional<FoodDescription> tempFoodDescription = foodDescriptionRepository.findByNdbNo(foodDes.getNdbNo());
            if (!tempFoodDescription.isPresent()) {
                int foodGrpCode = foodDes.getFoodGrpCode();
                Optional<FoodGroup> tempFoodGroup = foodGroupRepository.findByFoodGrpCode(foodGrpCode);
                if (!tempFoodGroup.isPresent()) {
                    logger.error(String.format("Food group with foodGrpCode (%d) does not exist", foodGrpCode));
                } else {
                    FoodGroup foodGroup = tempFoodGroup.get();
                    foodDes.setFoodGroup(foodGroup);
                    foodGroup.getFoodDescriptionList().add(foodDes);
                }
                foodDescriptionRepository.save(foodDes);
            }
        });
        logger.info("Completed parsing Food Description");

        List<NutrientDefinition> nutrientDefinitions = parseNutrientDefinition("Place holder for now");
        nutrientDefinitions.forEach(nutrDef -> {
            Optional<NutrientDefinition> nutrientDefinition = nutrientDefinitionRepository.findByNutrNo(nutrDef.getNutrNo());
            if (!nutrientDefinition.isPresent()) {
                nutrientDefinitionRepository.save(nutrDef);
            }
        });
        logger.info("Completed parsing Nutrient Definition");

        List<NutrientData> listOfNutrientData = parseNutrientData("Placeholder for now");
        for (NutrientData nutrientData : listOfNutrientData) {
            boolean foodDesExist = false, nutrientDefExist = false;
            final int ndbNo = nutrientData.getNutDataKey().getNdbNo();
            Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(ndbNo);
            if (!foodDescription.isPresent()) {
                logger.error(String.format("Food description with nbdNo (%d) does not exist", ndbNo));
            } else {
                foodDesExist = true;
            }
            final int nutrNo = nutrientData.getNutDataKey().getNutrNo();
            Optional<NutrientDefinition> nutrientDefinition = nutrientDefinitionRepository.findByNutrNo(nutrNo);
            if (!nutrientDefinition.isPresent()) {
                logger.error(String.format("Nutrient definition with nutrNo (%d) does not exist", nutrNo));
            } else {
                nutrientDefExist = true;
            }
            if (foodDesExist && nutrientDefExist) {
                foodDescription.get().getNutDataList().add(nutrientData);
                foodDescriptionRepository.save(foodDescription.get());
                nutrientData.setFoodDescription(foodDescription.get());

                nutrientDefinition.get().getNutDataList().add(nutrientData);
                nutrientDefinitionRepository.save(nutrientDefinition.get());
                nutrientData.setNutrientDefinition(nutrientDefinition.get());

                nutrientDataRepository.save(nutrientData);
            }
        }
        logger.info("Completed parsing Nutrient Data");
    }

    private List<FoodDescription> parseFoodDescription(final String filePath) {
        List<FoodDescription> list = new LinkedList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] foodDesFields = line.split("\\^");
                FoodDescription foodDescription = new FoodDescription();
                foodDescription.setNdbNo(Integer.parseInt(Objects.requireNonNull(getActualData(foodDesFields[0]))));
                foodDescription.setFoodGrpCode(Integer.parseInt(Objects.requireNonNull(getActualData(foodDesFields[1]))));
                foodDescription.setLongDesc(getActualData(foodDesFields[2]));
                foodDescription.setShortDesc(getActualData(foodDesFields[3]));
                list.add(foodDescription);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<NutrientData> parseNutrientData(final String filePath) {
        List<NutrientData> list = new LinkedList<>();
        String line;
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

    private List<NutrientDefinition> parseNutrientDefinition(final String filePath) {
        List<NutrientDefinition> list = new LinkedList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] nutrDefFields = line.split("\\^");
                NutrientDefinition nutrientDefinition = new NutrientDefinition();
                nutrientDefinition.setNutrNo(Integer.parseInt(Objects.requireNonNull(getActualData(nutrDefFields[0]))));
                nutrientDefinition.setUnit(Objects.requireNonNull(getActualData(nutrDefFields[1])));
                nutrientDefinition.setNutrDesc(Objects.requireNonNull(getActualData(nutrDefFields[3])));
                list.add(nutrientDefinition);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private List<FoodGroup> parseFoodGroup(final String filePath) {
        List<FoodGroup> list = new LinkedList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] foodGroupFields = line.split("\\^");
                FoodGroup foodGroup = new FoodGroup();
                foodGroup.setFoodGrpCode(Integer.parseInt(Objects.requireNonNull(getActualData(foodGroupFields[0]))));
                foodGroup.setFoodGrpDesc(Objects.requireNonNull(getActualData(foodGroupFields[1])));
                list.add(foodGroup);
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
