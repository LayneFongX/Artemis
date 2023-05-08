package com.farid.artemis;

import com.farid.artemis.domain.biz.device.DeviceBO;
import com.farid.artemis.enums.PTEMultiEnvEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Banchao
 * @Date 2023/5/8 11:00
 */
public class Junit2 {

    public static void main(String[] args) {
        System.out.println(isCorrespondPteHistoryNotification());
    }

    public static Boolean isCorrespondPteHistoryNotification() {
        Long previewTakeEffectSeconds = 1682870400L;
        Long releaseTakeEffectSeconds = 1683561600L;
        List<DeviceBO> deviceBOList = initDeviceBOList();

        List<String> unBindEquipmentPteIdList =
                deviceBOList.stream().filter(x -> getPushPreviewPteHistoryNotification(previewTakeEffectSeconds, x)
                                || getPushReleasePteHistoryNotification(releaseTakeEffectSeconds, x))
                        .map(DeviceBO::getId).filter(Objects::nonNull)
                        .collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(unBindEquipmentPteIdList);
    }

    private static List<DeviceBO> initDeviceBOList() {
        DeviceBO deviceBO1 = new DeviceBO();
        deviceBO1.setId(UUID.randomUUID().toString().replace("-",""));
        deviceBO1.setCustomName("deviceBO1");
        deviceBO1.setActiveTime(1683561700L);
        deviceBO1.setProductId("of1wzxhd");
        return Arrays.asList(deviceBO1);
    }

    private static Boolean getPushPreviewPteHistoryNotification(Long previewTakeEffectSeconds, DeviceBO deviceBO) {
        String ptePid = deviceBO.getProductId();

        // 如果不是FT的160A的PTE设备 & 如果不是FT的63A的PTE设备
        if (!Objects.equals(ptePid, PTEMultiEnvEnum.FIELD_TEST.getPowertag160APid())
                && !Objects.equals(ptePid, PTEMultiEnvEnum.FIELD_TEST.getPowertag63APid())) {
            return Boolean.FALSE;
        }

        //
        if (deviceBO.getActiveTime() > previewTakeEffectSeconds) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private static Boolean getPushReleasePteHistoryNotification(Long releaseTakeEffectSeconds, DeviceBO deviceBO) {
        String ptePid = deviceBO.getProductId();

        // 如果不是FT的160A的PTE设备 & 如果不是FT的63A的PTE设备
        if (!Objects.equals(ptePid, PTEMultiEnvEnum.RELEASE.getPowertag160APid())
                && !Objects.equals(ptePid, PTEMultiEnvEnum.RELEASE.getPowertag63APid())) {
            return Boolean.FALSE;
        }

        //
        if (deviceBO.getActiveTime() > releaseTakeEffectSeconds) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
