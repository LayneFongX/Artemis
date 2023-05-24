package com.farid.artemis.domain.biz.timer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Banchao
 * @Date 2022/8/19 17:47
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeZoneIdDifferTimeVO implements Serializable {


    private Integer differHour;

    private Integer differMinute;

}
