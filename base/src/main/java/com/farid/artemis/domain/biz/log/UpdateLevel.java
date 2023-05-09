package com.farid.artemis.domain.biz.log;

import lombok.Data;

/**
 * @author falcon
 * @since 2021/11/5
 */
@Data
public class UpdateLevel {

    private String logName;

    private String level;

    private Integer type = 3;
}
