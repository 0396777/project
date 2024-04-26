package com.atcard.service;

import java.util.List;

import com.atcard.entity.query.CountingQuery;
import com.atcard.entity.po.Counting;
import com.atcard.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface CountingService {

	/**
	 * 根据条件查询列表
	 */
	List<Counting> findListByParam(CountingQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(CountingQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Counting> findListByPage(CountingQuery param);

	/**
	 * 新增
	 */
	Integer add(Counting bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Counting> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Counting> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Counting bean,CountingQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(CountingQuery param);

	/**
	 * 根据Id查询对象
	 */
	Counting getCountingById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateCountingById(Counting bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteCountingById(Integer id);

}