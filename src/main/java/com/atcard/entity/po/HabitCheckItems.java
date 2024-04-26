package com.atcard.entity.po;

import com.atcard.entity.enums.DateTimePatternEnum;
import com.atcard.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 */
public class HabitCheckItems implements Serializable {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 打卡补充
	 */
	private String itemName;

	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	/**
	 * 完成次数
	 */
	private Integer completedTimes;

	/**
	 * 最后打卡时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastCheckedDate;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	public String getItemName(){
		return this.itemName;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	public Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	public Date getEndDate(){
		return this.endDate;
	}

	public void setCompletedTimes(Integer completedTimes){
		this.completedTimes = completedTimes;
	}

	public Integer getCompletedTimes(){
		return this.completedTimes;
	}

	public void setLastCheckedDate(Date lastCheckedDate){
		this.lastCheckedDate = lastCheckedDate;
	}

	public Date getLastCheckedDate(){
		return this.lastCheckedDate;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，用户id:"+(userId == null ? "空" : userId)+"，打卡补充:"+(itemName == null ? "空" : itemName)+"，开始时间:"+(startDate == null ? "空" : DateUtil.format(startDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+"，结束时间:"+(endDate == null ? "空" : DateUtil.format(endDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()))+"，完成次数:"+(completedTimes == null ? "空" : completedTimes)+"，最后打卡时间:"+(lastCheckedDate == null ? "空" : DateUtil.format(lastCheckedDate, DateTimePatternEnum.YYYY_MM_DD.getPattern()));
	}
}
