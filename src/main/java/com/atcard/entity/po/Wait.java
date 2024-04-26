package com.atcard.entity.po;

import java.util.Date;
import com.atcard.entity.enums.DateTimePatternEnum;
import com.atcard.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 */
public class Wait implements Serializable {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 待办事件详情
	 */
	private String eventInfo;


	/**
	 * 完成时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date okTime;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 是否完成
	 */
	private Integer isOk;

	/**
	 * 
	 */
	private String name;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setEventInfo(String eventInfo){
		this.eventInfo = eventInfo;
	}

	public String getEventInfo(){
		return this.eventInfo;
	}

	public void setOkTime(Date okTime){
		this.okTime = okTime;
	}

	public Date getOkTime(){
		return this.okTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setIsOk(Integer isOk){
		this.isOk = isOk;
	}

	public Integer getIsOk(){
		return this.isOk;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，用户名称:"+(userName == null ? "空" : userName)+"，待办事件详情:"+(eventInfo == null ? "空" : eventInfo)+"，完成时间:"+(okTime == null ? "空" : DateUtil.format(okTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，是否完成:"+(isOk == null ? "空" : isOk)+"，name:"+(name == null ? "空" : name);
	}
}
