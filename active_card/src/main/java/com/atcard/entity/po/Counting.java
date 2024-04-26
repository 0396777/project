package com.atcard.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 */
public class Counting implements Serializable {


	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String createTime;

	/**
	 * 
	 */
	private String time;

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

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return this.time;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，createTime:"+(createTime == null ? "空" : createTime)+"，time:"+(time == null ? "空" : time)+"，userId:"+(userId == null ? "空" : userId);
	}
}
