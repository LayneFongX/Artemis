package com.farid.artemis.domain.biz.log;

import lombok.Data;

@Data
public class LogLevelUpdateVO {

    private String logName;

    private String level;

    private Integer type = 3;
}
