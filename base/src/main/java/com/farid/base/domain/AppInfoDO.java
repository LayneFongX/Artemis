package com.farid.base.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppInfoDO extends BaseDO {
    private static final long serialVersionUID = -7853287369024724037L;
    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.String desc;
    private java.lang.String plant;
    private java.lang.String hashKey;
    private java.lang.String encryptType;
    private java.lang.String publicKey;
    private java.lang.String privateKey;
    private java.lang.String checkKey;
    private java.lang.Long gmtCreate;
    private java.lang.Long gmtModified;
    private java.lang.String clientId;
    private java.lang.Integer bizType;
    private java.lang.Integer appId;
}