package com.atcard.controller;

import java.util.List;

import com.atcard.entity.query.PomodoroQuery;
import com.atcard.entity.po.Pomodoro;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.PomodoroService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("pomodoroController")
@RequestMapping("/pomodoro")
public class PomodoroController extends ABaseController{

	@Resource
	private PomodoroService pomodoroService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(PomodoroQuery query){
		return getSuccessResponseVO(pomodoroService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody Pomodoro bean) {
		pomodoroService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Pomodoro> listBean) {
		pomodoroService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Pomodoro> listBean) {
		pomodoroService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getPomodoroById")
	public ResponseVO getPomodoroById(Integer id) {
		return getSuccessResponseVO(pomodoroService.getPomodoroById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updatePomodoroById")
	public ResponseVO updatePomodoroById(Pomodoro bean,Integer id) {
		pomodoroService.updatePomodoroById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deletePomodoroById")
	public ResponseVO deletePomodoroById(Integer id) {
		pomodoroService.deletePomodoroById(id);
		return getSuccessResponseVO(null);
	}
}