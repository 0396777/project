package com.atcard.controller;

import com.atcard.entity.po.Notes;
import com.atcard.entity.query.NotesQuery;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.NotesService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Controller
 */
@RestController("notesController")
@RequestMapping("/notes")
public class NotesController extends ABaseController{

	@Resource
	private NotesService notesService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(NotesQuery query){
		return getSuccessResponseVO(notesService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody Notes bean) {
		notesService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Notes> listBean) {
		notesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Notes> listBean) {
		notesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getNotesById")
	public ResponseVO getNotesById(Integer id) {
		return getSuccessResponseVO(notesService.getNotesById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateNotesById")
	public ResponseVO updateNotesById(Notes bean,Integer id) {
		notesService.updateNotesById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteNotesById")
	public ResponseVO deleteNotesById(Integer id) {
		notesService.deleteNotesById(id);
		return getSuccessResponseVO(null);
	}
}