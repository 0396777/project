package com.atcard.controller;

import java.util.List;

import com.atcard.entity.query.CountingQuery;
import com.atcard.entity.po.Counting;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.CountingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("countingController")
@RequestMapping("/counting")
public class CountingController extends ABaseController{

	@Resource
	private CountingService countingService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(CountingQuery query){
		return getSuccessResponseVO(countingService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody Counting bean) {
		countingService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Counting> listBean) {
		countingService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Counting> listBean) {
		countingService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getCountingById")
	public ResponseVO getCountingById(Integer id) {
		return getSuccessResponseVO(countingService.getCountingById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateCountingById")
	public ResponseVO updateCountingById(Counting bean,Integer id) {
		countingService.updateCountingById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteCountingById")
	public ResponseVO deleteCountingById(Integer id) {
		countingService.deleteCountingById(id);
		return getSuccessResponseVO(null);
	}
}