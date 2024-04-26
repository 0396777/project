package com.atcard.service;

import com.atcard.entity.po.HabitCheckItems;
import com.atcard.entity.query.HabitCheckItemsQuery;
import com.atcard.entity.vo.PaginationResultVO;

import java.util.List;


/**
 *  业务接口
 */
public interface HabitCheckItemsService {

	/**
	 * 根据条件查询列表
	 */
	List<HabitCheckItems> findListByParam(HabitCheckItemsQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(HabitCheckItemsQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<HabitCheckItems> findListByPage(HabitCheckItemsQuery param);

	/**
	 * 新增
	 */
	Integer add(HabitCheckItems bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<HabitCheckItems> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<HabitCheckItems> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(HabitCheckItems bean,HabitCheckItemsQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(HabitCheckItemsQuery param);

	/**
	 * 根据Id查询对象
	 */
	HabitCheckItems getHabitCheckItemsById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateHabitCheckItemsById(HabitCheckItems bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteHabitCheckItemsById(Integer id);

}