package com.farid.artemis.domain.apicontext;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiRequestDO implements java.io.Serializable {
    private static final long serialVersionUID = 4957776030070092305L;
    private ApiContextDO apiContextDo;
    private java.lang.String sign;
    private java.lang.String session;
    private java.lang.String h5Token;
    private java.lang.String cst;
    private java.lang.String api;
    private java.lang.String data;
    private java.lang.String t;
    private java.lang.String type;
    private java.lang.String traceId;
    private AppInfoDO appInfoDo;
    private java.lang.Boolean openSign;
    private java.lang.String ddebug;
    private java.lang.String queryString;
    private java.lang.String gwId;
    private java.lang.String otherData;
    private java.lang.String plant;
    private java.lang.String entry;
    private java.lang.String n4h5;
    private java.lang.String sp;
    private java.lang.String irp;
    private java.lang.String requestId;
    private java.lang.String gwIdAlias;
    private java.lang.String gwKey;
    private java.lang.String et;
    private java.lang.String signKey;
    private java.lang.String clientTraceId;
    private java.lang.String bizDomain;
    private java.lang.String targetZone;
    private java.lang.String jumpCount;
    private java.lang.Boolean proxyed;
    private java.lang.String token;
    private java.lang.String bundleId;
    private java.lang.String checkKey;
    private java.lang.String encryptData;
    private java.lang.String trd;
}