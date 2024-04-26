package com.atcard.entity.query;


/**
 * 参数
 */
public class HabitCheckItemsQuery extends BaseParam {


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

	private String itemNameFuzzy;

	/**
	 * 开始时间
	 */
	private String startDate;

	private String startDateStart;

	private String startDateEnd;

	/**
	 * 结束时间
	 */
	private String endDate;

	private String endDateStart;

	private String endDateEnd;

	/**
	 * 完成次数
	 */
	private Integer completedTimes;

	/**
	 * 最后打卡时间
	 */
	private String lastCheckedDate;

	private String lastCheckedDateStart;

	private String lastCheckedDateEnd;


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

	public void setItemNameFuzzy(String itemNameFuzzy){
		this.itemNameFuzzy = itemNameFuzzy;
	}

	public String getItemNameFuzzy(){
		return this.itemNameFuzzy;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return this.startDate;
	}

	public void setStartDateStart(String startDateStart){
		this.startDateStart = startDateStart;
	}

	public String getStartDateStart(){
		return this.startDateStart;
	}
	public void setStartDateEnd(String startDateEnd){
		this.startDateEnd = startDateEnd;
	}

	public String getStartDateEnd(){
		return this.startDateEnd;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return this.endDate;
	}

	public void setEndDateStart(String endDateStart){
		this.endDateStart = endDateStart;
	}

	public String getEndDateStart(){
		return this.endDateStart;
	}
	public void setEndDateEnd(String endDateEnd){
		this.endDateEnd = endDateEnd;
	}

	public String getEndDateEnd(){
		return this.endDateEnd;
	}

	public void setCompletedTimes(Integer completedTimes){
		this.completedTimes = completedTimes;
	}

	public Integer getCompletedTimes(){
		return this.completedTimes;
	}

	public void setLastCheckedDate(String lastCheckedDate){
		this.lastCheckedDate = lastCheckedDate;
	}

	public String getLastCheckedDate(){
		return this.lastCheckedDate;
	}

	public void setLastCheckedDateStart(String lastCheckedDateStart){
		this.lastCheckedDateStart = lastCheckedDateStart;
	}

	public String getLastCheckedDateStart(){
		return this.lastCheckedDateStart;
	}
	public void setLastCheckedDateEnd(String lastCheckedDateEnd){
		this.lastCheckedDateEnd = lastCheckedDateEnd;
	}

	public String getLastCheckedDateEnd(){
		return this.lastCheckedDateEnd;
	}

}
