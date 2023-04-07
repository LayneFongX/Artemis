package com.farid.base.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseUserDO extends BaseDO {

    private static final long serialVersionUID = 5822103672841929667L;

    private String uid;

    private String username;

    private Integer regFrom;

    private String nickname;

    private Long attribute;

    private Integer sex;

    private String rewardCode;

    private String headPic;

    private Long regTime;

    private Long totalSpace;                                //总空间大小（KB）

    private Long freeSpace;                                    //免费空间大小

    private Long proSpace;                                    //购买空间大小

    private Long rewardSpace;                                //赠送空间大小

    private Long inviterSpace;                                //推荐获得空间大小

    private Long invitedSpace;                                //被推荐获得空间大小

    private Long usedSpace;                                    //已使用空间

    private Integer storageNums;                                //用户文件数

    private String sessionId;                                    //用户请求的sessionId,用于验证防止cache污染

    private Long modifyTime;

    private String h5Token;

    private Long sessionModifyTime;

    private Integer bizType;

    private Integer status;

    private Integer isChild;

    private Boolean guestUser;

}
