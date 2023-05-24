package com.farid.artemis.domain.biz.timer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionTimerBizVO implements Serializable {

    private String bizId;

    private String resId;

    private Integer resType;

    private String loops;

    private String timeZoneId;

    private String startTime;

    @JsonIgnore
    private Long localTime;

    private String category;

    private String note;

    private String ext;

    private Integer timerStatus;

}