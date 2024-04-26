package com.atcard.service.impl;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.po.Notes;
import com.atcard.entity.query.NotesQuery;
import com.atcard.entity.query.SimplePage;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.mappers.NotesMapper;
import com.atcard.service.NotesService;
import com.atcard.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *  业务接口实现
 */
@Service("notesService")
public class NotesServiceImpl implements NotesService {

	@Resource
	private NotesMapper<Notes, NotesQuery> notesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Notes> findListByParam(NotesQuery param) {
		return this.notesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(NotesQuery param) {
		return this.notesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Notes> findListByPage(NotesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Notes> list = this.findListByParam(param);
		PaginationResultVO<Notes> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Notes bean) {
		return this.notesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Notes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.notesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Notes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.notesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Notes bean, NotesQuery param) {
		StringTools.checkParam(param);
		return this.notesMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(NotesQuery param) {
		StringTools.checkParam(param);
		return this.notesMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Notes getNotesById(Integer id) {
		return this.notesMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateNotesById(Notes bean, Integer id) {
		return this.notesMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteNotesById(Integer id) {
		return this.notesMapper.deleteById(id);
	}
}