package com.atcard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.query.CountingQuery;
import com.atcard.entity.po.Counting;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.entity.query.SimplePage;
import com.atcard.mappers.CountingMapper;
import com.atcard.service.CountingService;
import com.atcard.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("countingService")
public class CountingServiceImpl implements CountingService {

	@Resource
	private CountingMapper<Counting, CountingQuery> countingMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Counting> findListByParam(CountingQuery param) {
		return this.countingMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CountingQuery param) {
		return this.countingMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Counting> findListByPage(CountingQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Counting> list = this.findListByParam(param);
		PaginationResultVO<Counting> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Counting bean) {
		return this.countingMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Counting> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.countingMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Counting> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.countingMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Counting bean, CountingQuery param) {
		StringTools.checkParam(param);
		return this.countingMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(CountingQuery param) {
		StringTools.checkParam(param);
		return this.countingMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Counting getCountingById(Integer id) {
		return this.countingMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateCountingById(Counting bean, Integer id) {
		return this.countingMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteCountingById(Integer id) {
		return this.countingMapper.deleteById(id);
	}
}