package com.atcard.service;

import java.util.List;

import com.atcard.entity.query.WaitQuery;
import com.atcard.entity.po.Wait;
import com.atcard.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface WaitService {

	/**
	 * 根据条件查询列表
	 */
	List<Wait> findListByParam(WaitQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(WaitQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Wait> findListByPage(WaitQuery param);

	/**
	 * 新增
	 */
	Integer add(Wait bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Wait> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Wait> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Wait bean,WaitQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(WaitQuery param);

	/**
	 * 根据Id查询对象
	 */
	Wait getWaitById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateWaitById(Wait bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteWaitById(Integer id);

}