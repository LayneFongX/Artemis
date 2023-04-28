package com.farid.artemis.domain.biz.upgrade;

import lombok.Data;

@Data
public class UpgradeConfig {

  /**
   * elko app版本号
   * */
  private String elkoVersion;

  /**
   * wiser app版本号
   * */
  private String wiserVersion;

  /**
   * 网关 pid版本号
   * */
  private String pidVersion;

  /**
   * 网关版本号
   * */
  private String hubVersion;

  /**
   * pte设备Pid版本号
   * */
  private String ptePidVersion;
}
