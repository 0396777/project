package com.atcard.service;

import java.util.List;

import com.atcard.entity.query.PomodoroQuery;
import com.atcard.entity.po.Pomodoro;
import com.atcard.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface PomodoroService {

	/**
	 * 根据条件查询列表
	 */
	List<Pomodoro> findListByParam(PomodoroQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(PomodoroQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Pomodoro> findListByPage(PomodoroQuery param);

	/**
	 * 新增
	 */
	Integer add(Pomodoro bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Pomodoro> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Pomodoro> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Pomodoro bean,PomodoroQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(PomodoroQuery param);

	/**
	 * 根据Id查询对象
	 */
	Pomodoro getPomodoroById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updatePomodoroById(Pomodoro bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deletePomodoroById(Integer id);

}