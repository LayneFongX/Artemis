package com.farid.artemis.domain.biz.timer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
* @Description TODO
* @Author huanzhi.yang@qq.com
* @Date 2021/3/18
* @Version 1.0
*/
@Data
@NoArgsConstructor
@SuperBuilder
public class BaseTimerParam implements Serializable {
	/**
	 * 时间点业务唯一主键id 必传
	 */
	private String bizId;


	private Integer bizType;

	/**
	 * 涂鸦用户id   必传
	 */
	private String uid;


	private String resId;

	private Integer resType;

	private String timeZoneId;

	/**
	 * 定时任务的分类
	 */
	private String category;

	private String actionJson;

	/**
	 * 定时任务当前工作状态 0:运行 1:暂停(Stop) 2:恢复(RESUME)
	 */
	private Integer timerStatus;

	/**
	 * 定时任务备注
	 */
	private String note;

	/**
	 * 定时任务拓展字段 格式为JSON格式 用于存储跟定时任务相关业务 如{"temp":24}
	 */
	private String ext;

	/**
	 * 自定义topic
	 */
	private String customTopic;

	/**
	 * 是否为本地定时，true：sigmax那边不会关联，false：关联sigmax
	 */
	private Boolean isLocalTimer;

}
