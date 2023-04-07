package com.farid.base.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiContextDO extends BaseDO {

    private static final long serialVersionUID = 3836954456077493208L;

    private String ttid;

    private String deviceid;

    private String imei;

    private String imsi;                                                    //客户端用户标识,如果没有imsi,传递客户端ip

    private String lat;

    private String lon;

    private String ip;

    private int port = -1;

    private String uid;

    private String gid;

    private String nick;

    private String proxyIp;                                                // 代理ip

    private String sid;                                                    // mtop sid

    private boolean isLongin;

    private String isH5;                                                    //1代表是H5

    private String h5TokenValue;                                            //h5登陆的加密值

    private String channelId;                                                // 渠道ID

    //private String				cst;													//csrftoken，H5需要,,移到外面去

    private String appVersion;                                            // 客户端版本信息

    private boolean apiVersionFromUrl;                                    // api版本是否从url中传递

    private String apiVersion;                                            // api版本

    private String api;                                                    // api接口名

    private String os;                                                    // 客户端类型 ios或android

    private String type;                                                    // 客户端类型

    private String platform;                                                //机型

    private String osSystem;                                                //客户端操作系统

    private String lang = "zh-Hans";                        //语言包

    private BaseUserDO baseUserDO;                                            //基础的用户数据

    private String userAgent;

    private Boolean checkDeviceId;                                            //ajax 是否验证设备

    private String bizId;                                                    //ajax 用业务ID

    private Integer nek;                                                //客户端的网络

    private Integer ele;                                                //客户端的电量

    private String region;                                        // 区域,AY,AZ,EU

    private String site;                                        // 不同于region，site应用场景如下：
    // 同一个区域比如AY，前端会部署两个站点，
    // 但后端服务只有一个，所以要区分来源但又不需要路由

    private String timeZoneId;


    private Map<String, Object> params = new HashMap<String, Object>();    // 入参


    private String appRnVersion;

    private String sdkVersion;

    private String parentUid;

    private Long spaceId;

    /**
     * atop留给业务的扩展参数
     */
    private Map<String, String> bizData;
}