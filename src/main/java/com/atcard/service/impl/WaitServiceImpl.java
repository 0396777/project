package com.atcard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.query.WaitQuery;
import com.atcard.entity.po.Wait;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.entity.query.SimplePage;
import com.atcard.mappers.WaitMapper;
import com.atcard.service.WaitService;
import com.atcard.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("waitService")
public class WaitServiceImpl implements WaitService {

	@Resource
	private WaitMapper<Wait, WaitQuery> waitMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Wait> findListByParam(WaitQuery param) {
		return this.waitMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(WaitQuery param) {
		return this.waitMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Wait> findListByPage(WaitQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Wait> list = this.findListByParam(param);
		PaginationResultVO<Wait> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Wait bean) {
		return this.waitMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Wait> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.waitMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Wait> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.waitMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Wait bean, WaitQuery param) {
		StringTools.checkParam(param);
		return this.waitMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(WaitQuery param) {
		StringTools.checkParam(param);
		return this.waitMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Wait getWaitById(Integer id) {
		return this.waitMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateWaitById(Wait bean, Integer id) {
		return this.waitMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteWaitById(Integer id) {
		return this.waitMapper.deleteById(id);
	}
}