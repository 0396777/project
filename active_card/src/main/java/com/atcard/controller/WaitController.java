package com.atcard.controller;

import java.util.List;

import com.atcard.entity.query.WaitQuery;
import com.atcard.entity.po.Wait;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.WaitService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("waitController")
@RequestMapping("/wait")
public class WaitController extends ABaseController{

	@Resource
	private WaitService waitService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(WaitQuery query){
		return getSuccessResponseVO(waitService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody Wait bean) {
		waitService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Wait> listBean) {
		waitService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Wait> listBean) {
		waitService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getWaitById")
	public ResponseVO getWaitById(Integer id) {
		return getSuccessResponseVO(waitService.getWaitById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateWaitById")
	public ResponseVO updateWaitById(Wait bean,Integer id) {
		waitService.updateWaitById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteWaitById")
	public ResponseVO deleteWaitById(Integer id) {
		waitService.deleteWaitById(id);
		return getSuccessResponseVO(null);
	}
}