package com.atcard.controller;

import com.atcard.entity.po.HabitCheckItems;
import com.atcard.entity.query.HabitCheckItemsQuery;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.HabitCheckItemsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Controller
 */
@RestController("habitCheckItemsController")
@RequestMapping("/habitCheckItems")
public class HabitCheckItemsController extends ABaseController{

	@Resource
	private HabitCheckItemsService habitCheckItemsService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(HabitCheckItemsQuery query){
		return getSuccessResponseVO(habitCheckItemsService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody HabitCheckItems bean) {
		habitCheckItemsService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<HabitCheckItems> listBean) {
		habitCheckItemsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<HabitCheckItems> listBean) {
		habitCheckItemsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getHabitCheckItemsById")
	public ResponseVO getHabitCheckItemsById(Integer id) {
		return getSuccessResponseVO(habitCheckItemsService.getHabitCheckItemsById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateHabitCheckItemsById")
	public ResponseVO updateHabitCheckItemsById(HabitCheckItems bean,Integer id) {
		habitCheckItemsService.updateHabitCheckItemsById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteHabitCheckItemsById")
	public ResponseVO deleteHabitCheckItemsById(Integer id) {
		habitCheckItemsService.deleteHabitCheckItemsById(id);
		return getSuccessResponseVO(null);
	}
}