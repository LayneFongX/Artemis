package com.farid.artemis.domain.biz.timer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
* @Description 简单定时任务参数
* @Author huanzhi.yang@qq.com
* @Date 2021/3/18
* @Version 1.0
*/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SimpleTimerParam extends BaseTimerParam{

	private Long localTime;

	/**
	 * 定时执行执行的日期  例如：2021-03-01
	 */
	private String localDate;

}
