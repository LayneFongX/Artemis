package com.farid.artemis.domain.biz.energy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyHistoryDetailCalcVO implements Serializable {

    private static final long serialVersionUID = -5532630582190792999L;

    private String periodLabel;

    private Long consumption;

    private Long production;

}
