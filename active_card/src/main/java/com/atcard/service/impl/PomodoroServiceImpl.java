package com.atcard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.query.PomodoroQuery;
import com.atcard.entity.po.Pomodoro;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.entity.query.SimplePage;
import com.atcard.mappers.PomodoroMapper;
import com.atcard.service.PomodoroService;
import com.atcard.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("pomodoroService")
public class PomodoroServiceImpl implements PomodoroService {

	@Resource
	private PomodoroMapper<Pomodoro, PomodoroQuery> pomodoroMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Pomodoro> findListByParam(PomodoroQuery param) {
		return this.pomodoroMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(PomodoroQuery param) {
		return this.pomodoroMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Pomodoro> findListByPage(PomodoroQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Pomodoro> list = this.findListByParam(param);
		PaginationResultVO<Pomodoro> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Pomodoro bean) {
		return this.pomodoroMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Pomodoro> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.pomodoroMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Pomodoro> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.pomodoroMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Pomodoro bean, PomodoroQuery param) {
		StringTools.checkParam(param);
		return this.pomodoroMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(PomodoroQuery param) {
		StringTools.checkParam(param);
		return this.pomodoroMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Pomodoro getPomodoroById(Integer id) {
		return this.pomodoroMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updatePomodoroById(Pomodoro bean, Integer id) {
		return this.pomodoroMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deletePomodoroById(Integer id) {
		return this.pomodoroMapper.deleteById(id);
	}
}