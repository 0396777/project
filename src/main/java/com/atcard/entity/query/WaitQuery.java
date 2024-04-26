package com.atcard.entity.query;

import java.util.Date;


/**
 * 参数
 */
public class WaitQuery extends BaseParam {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 用户名称
	 */
	private String userName;

	private String userNameFuzzy;

	/**
	 * 待办事件详情
	 */
	private String eventInfo;

	private String eventInfoFuzzy;

	/**
	 * 完成时间
	 */
	private String okTime;

	private String okTimeStart;

	private String okTimeEnd;

	/**
	 * 创建时间
	 */
	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	/**
	 * 是否完成
	 */
	private Integer isOk;

	/**
	 * 
	 */
	private String name;

	private String nameFuzzy;


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

	public void setUserNameFuzzy(String userNameFuzzy){
		this.userNameFuzzy = userNameFuzzy;
	}

	public String getUserNameFuzzy(){
		return this.userNameFuzzy;
	}

	public void setEventInfo(String eventInfo){
		this.eventInfo = eventInfo;
	}

	public String getEventInfo(){
		return this.eventInfo;
	}

	public void setEventInfoFuzzy(String eventInfoFuzzy){
		this.eventInfoFuzzy = eventInfoFuzzy;
	}

	public String getEventInfoFuzzy(){
		return this.eventInfoFuzzy;
	}

	public void setOkTime(String okTime){
		this.okTime = okTime;
	}

	public String getOkTime(){
		return this.okTime;
	}

	public void setOkTimeStart(String okTimeStart){
		this.okTimeStart = okTimeStart;
	}

	public String getOkTimeStart(){
		return this.okTimeStart;
	}
	public void setOkTimeEnd(String okTimeEnd){
		this.okTimeEnd = okTimeEnd;
	}

	public String getOkTimeEnd(){
		return this.okTimeEnd;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeStart(String createTimeStart){
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeStart(){
		return this.createTimeStart;
	}
	public void setCreateTimeEnd(String createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeEnd(){
		return this.createTimeEnd;
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

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

}
