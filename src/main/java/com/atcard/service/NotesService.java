package com.atcard.service;

import com.atcard.entity.po.Notes;
import com.atcard.entity.query.NotesQuery;
import com.atcard.entity.vo.PaginationResultVO;

import java.util.List;


/**
 *  业务接口
 */
public interface NotesService {

	/**
	 * 根据条件查询列表
	 */
	List<Notes> findListByParam(NotesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(NotesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Notes> findListByPage(NotesQuery param);

	/**
	 * 新增
	 */
	Integer add(Notes bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Notes> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Notes> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Notes bean,NotesQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(NotesQuery param);

	/**
	 * 根据Id查询对象
	 */
	Notes getNotesById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateNotesById(Notes bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteNotesById(Integer id);

}