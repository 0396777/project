package com.atcard.service.impl;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.po.HabitCheckItems;
import com.atcard.entity.query.HabitCheckItemsQuery;
import com.atcard.entity.query.SimplePage;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.mappers.HabitCheckItemsMapper;
import com.atcard.service.HabitCheckItemsService;
import com.atcard.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *  业务接口实现
 */
@Service("habitCheckItemsService")
public class HabitCheckItemsServiceImpl implements HabitCheckItemsService {

	@Resource
	private HabitCheckItemsMapper<HabitCheckItems, HabitCheckItemsQuery> habitCheckItemsMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<HabitCheckItems> findListByParam(HabitCheckItemsQuery param) {
		return this.habitCheckItemsMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(HabitCheckItemsQuery param) {
		return this.habitCheckItemsMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<HabitCheckItems> findListByPage(HabitCheckItemsQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<HabitCheckItems> list = this.findListByParam(param);
		PaginationResultVO<HabitCheckItems> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(HabitCheckItems bean) {
		return this.habitCheckItemsMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<HabitCheckItems> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.habitCheckItemsMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<HabitCheckItems> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.habitCheckItemsMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(HabitCheckItems bean, HabitCheckItemsQuery param) {
		StringTools.checkParam(param);
		return this.habitCheckItemsMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(HabitCheckItemsQuery param) {
		StringTools.checkParam(param);
		return this.habitCheckItemsMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public HabitCheckItems getHabitCheckItemsById(Integer id) {
		return this.habitCheckItemsMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateHabitCheckItemsById(HabitCheckItems bean, Integer id) {
		return this.habitCheckItemsMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteHabitCheckItemsById(Integer id) {
		return this.habitCheckItemsMapper.deleteById(id);
	}
}