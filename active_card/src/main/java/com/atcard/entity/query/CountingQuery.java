package com.atcard.entity.query;



/**
 * 参数
 */
public class CountingQuery extends BaseParam {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String createTime;

	private String createTimeFuzzy;

	/**
	 * 
	 */
	private String time;

	private String timeFuzzy;

	/**
	 * 
	 */
	private Integer userId;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateTimeFuzzy(String createTimeFuzzy){
		this.createTimeFuzzy = createTimeFuzzy;
	}

	public String getCreateTimeFuzzy(){
		return this.createTimeFuzzy;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return this.time;
	}

	public void setTimeFuzzy(String timeFuzzy){
		this.timeFuzzy = timeFuzzy;
	}

	public String getTimeFuzzy(){
		return this.timeFuzzy;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

}
