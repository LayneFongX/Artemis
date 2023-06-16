package com.farid.artemis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.farid.artemis.domain.biz.energy.EnergyHistoryDetailCalcVO;

import java.util.*;

/**
 * @Author Banchao
 * @Date 2023/6/16 16:08
 */
public class Junit4 {

    public static void main(String[] args) {
        List<EnergyHistoryDetailCalcVO> energyHistoryDetailCalcVOS = generateTestData();
        energyHistoryDetailCalcVOS.sort(Comparator.comparing(EnergyHistoryDetailCalcVO::getPeriodLabel));
        System.out.println(JSON.toJSONString(energyHistoryDetailCalcVOS, SerializerFeature.WriteMapNullValue));
        System.out.println("---------------------------------------------------------------------------------------");

        supplyEnergyHistoryDetail(energyHistoryDetailCalcVOS);
        System.out.println(JSON.toJSONString(energyHistoryDetailCalcVOS, SerializerFeature.WriteMapNullValue));
    }

    public static List<EnergyHistoryDetailCalcVO> generateTestData() {
        List<EnergyHistoryDetailCalcVO> testData = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            EnergyHistoryDetailCalcVO data = new EnergyHistoryDetailCalcVO();

            String periodLabel = generateRandomPeriodLabel();
            data.setPeriodLabel(periodLabel);

            if (random.nextBoolean()) {
                long consumption = random.nextInt(10001);
                data.setConsumption(consumption);
            }
            if (random.nextBoolean()) {
                long production = random.nextInt(10001);
                data.setProduction(production);
            }

            testData.add(data);
        }

        return testData;
    }

    private static String generateRandomPeriodLabel() {
        Random random = new Random();
        int year = 2023 + random.nextInt(2); // Random year between 2023 and 2024
        int month = random.nextInt(12) + 1; // Random month between 1 and 12
        int day = random.nextInt(28) + 1; // Random day between 1 and 28
        int hour = random.nextInt(24); // Random hour between 0 and 23

        return String.format("%04d-%02d-%02d-%02d", year, month, day, hour);
    }

    private static void supplyEnergyHistoryDetail(List<EnergyHistoryDetailCalcVO> energyHistoryDetailCalcVOList) {
        EnergyHistoryDetailCalcVO startEnergyHistoryVO = energyHistoryDetailCalcVOList.get(0);
        Long startEnergyHistoryConsumption = startEnergyHistoryVO.getConsumption();
        Long startEnergyHistoryProduction = startEnergyHistoryVO.getProduction();

        if (Objects.isNull(startEnergyHistoryConsumption)) {
            EnergyHistoryDetailCalcVO firstNonNullConsumptionCalcVO =
                    energyHistoryDetailCalcVOList.stream()
                            .filter(x -> Objects.nonNull(x.getConsumption()))
                            .findFirst()
                            .orElse(startEnergyHistoryVO);
            startEnergyHistoryVO.setConsumption(firstNonNullConsumptionCalcVO.getConsumption());
        }

        if (Objects.isNull(startEnergyHistoryProduction)) {
            EnergyHistoryDetailCalcVO firstNonNullProductionCalcVO =
                    energyHistoryDetailCalcVOList.stream()
                            .filter(x -> Objects.nonNull(x.getProduction()))
                            .findFirst()
                            .orElse(startEnergyHistoryVO);
            startEnergyHistoryVO.setProduction(firstNonNullProductionCalcVO.getProduction());
        }

        for (int i = 1; i < energyHistoryDetailCalcVOList.size(); i++) {
            EnergyHistoryDetailCalcVO energyHistoryDetailCalcVO = energyHistoryDetailCalcVOList.get(i);
            EnergyHistoryDetailCalcVO preEnergyHistoryDetailCalcVO = energyHistoryDetailCalcVOList.get(i - 1);

            if (Objects.isNull(energyHistoryDetailCalcVO.getConsumption())) {
                energyHistoryDetailCalcVO.setConsumption(preEnergyHistoryDetailCalcVO.getConsumption());
            }

            if (Objects.isNull(energyHistoryDetailCalcVO.getProduction())) {
                energyHistoryDetailCalcVO.setProduction(preEnergyHistoryDetailCalcVO.getProduction());
            }
        }
    }
}
