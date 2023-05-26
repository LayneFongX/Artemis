package com.farid.artemis.domain.biz.timer;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @Description 周循环定时任务VO
* @Author huanzhi.yang@qq.com
* @Date 2021/3/18
* @Version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
public class LoopTimerVO extends SimpleTimerParam {
	/**
	 * 周循环格式，如"1001001"， 第一位为周日，最后一位是周一， true   必传
	 */
	private String loops;


	/**
	 * 更新时间
	 */
	private Long gmtModified;
}
