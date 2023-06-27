package com.farid.artemis.domain.biz.kv;

import com.farid.artemis.enums.kv.BusinessStorageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmartBusinessStorageVO {

    /**
     * id
     */
    private Long id;

    /**
     * ##用户体系id
     */
    private Integer bizType;
    /**
     * ##app标识
     */
    private Integer appId;
    /**
     * ##用户id
     */
    private String uid;
    /**
     * 业务资源id
     */
    private String resId;
    /**
     * 业务资源类型
     */
    private BusinessStorageType resType;
    /**
     * ##业务主键key
     */
    private String key;
    /**
     * ##业务数据
     */
    private String value;
    /**
     * ##业务分类id
     */
    private Integer businessType;
    /**
     * ##业务数据来源;1:APP 2:云云对接 9:其他
     */
    private Integer businessFrom;
    /**
     * ##状态；1有效，0无效
     */
    private Boolean status;
    /**
     * ##备注
     */
    private String remark;

}
