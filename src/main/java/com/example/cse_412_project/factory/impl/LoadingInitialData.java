package com.example.cse_412_project.factory.impl;

import com.example.cse_412_project.entities.FoodDescription;
import com.example.cse_412_project.entities.Weight;
import com.example.cse_412_project.entities.WeightKey;
import com.example.cse_412_project.repositories.FoodDescriptionRepository;
import com.example.cse_412_project.repositories.WeightRepository;
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
    private WeightRepository weightRepository;

    @Override
    public void run(String...args) throws Exception {
        List<FoodDescription> foodDescriptions = parseFoodDescription("Place Holder For Now");
        foodDescriptions.forEach(foodDes -> {
            Optional<FoodDescription> foodDescription = foodDescriptionRepository.findByNdbNo(foodDes.getNdbNo());
            if (!foodDescription.isPresent()) {
                foodDescriptionRepository.save(foodDes);
            }
        });

        List<Weight> weights = parseWeight("Place Holder For Now");
        for (Weight weight : weights) {
            Optional<FoodDescription> tempFoodDescription = foodDescriptionRepository.findByNdbNo(weight.getWeightKey().getNdbNo());
            if (!tempFoodDescription.isPresent()) {
                // Todo: After merge, add the logger.error here for not having foodDescription
            } else {
                FoodDescription foodDescription = tempFoodDescription.get();
                foodDescription.getWeights().add(weight);
                foodDescriptionRepository.save(foodDescription);
                weight.setFoodDescription(foodDescription);
                weightRepository.save(weight);
            }
        }
        // Todo: After merge, add a logger.info here for finish parsing weight
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

    private List<Weight> parseWeight(String filePath) {
        List<Weight> weights = new LinkedList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] weightFields = line.split("\\^");
                Weight weight = new Weight();
                weight.setWeightKey(new WeightKey(
                        Integer.parseInt(Objects.requireNonNull(getActualData(weightFields[0]))),
                        Integer.parseInt(Objects.requireNonNull(getActualData(weightFields[1])))
                ));
                weight.setAmount(Integer.parseInt(Objects.requireNonNull(getActualData(weightFields[2]))));
                weight.setDescription(Objects.requireNonNull(getActualData(weightFields[3])));
                weight.setGramWeight(Float.parseFloat(Objects.requireNonNull(getActualData(weightFields[4]))));
                weights.add(weight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weights;
    }

    private String getActualData(String foodDesField) {
        if (foodDesField.equals("^^") || foodDesField.equals("~~")) {
            return null;
        }
        if (foodDesField.charAt(0) == '~') {
            return foodDesField.substring(1, foodDesField.length() - 1);
        }
        return foodDesField;
    }
}
