package com.farid.artemis.enums;

/**
 * @Author Banchao
 * @Date 2022/8/15 18:38
 */
public enum PTEMultiEnvEnum {

    RELEASE("kq5sn8rwfxycg9apkmgu", "of1wzxhd", "gcc6gcg4", "mm4qky4b", "p12rcfcl"),

    FIELD_TEST("u5q35vfra3x8ctm3mdds", "84db4ryf", "16fqwsld", "fk8rcfta", "cismgxvl"),

    STAGING("ss57d9urn4xvcuxx7tte", "nccooqhf", "6zbww3v4", "jpltgcy4", "wynoqkwl"),

    DEVELOPMENT("hg9nsfk3bipzlj879yxy", "gvqbxz9m", "vzvf6q1c", "azulfoeo", ""),
    ;

    private final String clientId;
    private final String powertag63APid;
    private final String powertag160APid;
    private final String gridPid;
    private final String equipmentPid;


    PTEMultiEnvEnum(String clientId, String powertag63APid, String powertag160APid, String gridPid, String equipmentPid) {
        this.clientId = clientId;
        this.powertag63APid = powertag63APid;
        this.powertag160APid = powertag160APid;
        this.gridPid = gridPid;
        this.equipmentPid = equipmentPid;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPowertag63APid() {
        return powertag63APid;
    }

    public String getPowertag160APid() {
        return powertag160APid;
    }

    public String getGridPid() {
        return gridPid;
    }

    public String getEquipmentPid() {
        return equipmentPid;
    }

    public static PTEMultiEnvEnum getEnumByPowertagPid(String powertagPid) {
        for (PTEMultiEnvEnum item : PTEMultiEnvEnum.values()) {
            if (item.getPowertag63APid().equals(powertagPid) || item.getPowertag160APid().equals(powertagPid)) {
                return item;
            }
        }
        return PTEMultiEnvEnum.RELEASE;
    }
}
