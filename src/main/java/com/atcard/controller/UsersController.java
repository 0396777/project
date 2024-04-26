package com.atcard.controller;

import com.atcard.entity.po.Users;
import com.atcard.entity.query.UsersQuery;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.service.UsersService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;

/**
 *  Controller
 */
@RestController("usersController")
@RequestMapping("/users")
public class UsersController extends ABaseController{

	@Resource
	private UsersService usersService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(UsersQuery query){
		return getSuccessResponseVO(usersService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(@RequestBody Users bean) {
		usersService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Users> listBean) {
		usersService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Users> listBean) {
		usersService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getUsersById")
	public ResponseVO getUsersById(Integer id) {
		return getSuccessResponseVO(usersService.getUsersById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateUsersById")
	public ResponseVO updateUsersById(Users bean,Integer id) {
		usersService.updateUsersById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteUsersById")
	public ResponseVO deleteUsersById(Integer id) {
		usersService.deleteUsersById(id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserName查询对象
	 */
	@RequestMapping("/getUsersByUserName")
	public ResponseVO getUsersByUserName(String userName) throws MessagingException {
		return getSuccessResponseVO(usersService.getUsersByUserName(userName));
	}

	/**
	 * 根据UserName修改对象
	 */
	@RequestMapping("/updateUsersByUserName")
	public ResponseVO updateUsersByUserName(Users bean,String userName) {
		usersService.updateUsersByUserName(bean,userName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserName删除
	 */
	@RequestMapping("/deleteUsersByUserName")
	public ResponseVO deleteUsersByUserName(String userName) {
		usersService.deleteUsersByUserName(userName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Email查询对象
	 */
	@RequestMapping("/getUsersByEmail")
	public ResponseVO getUsersByEmail(String email) {
		return getSuccessResponseVO(usersService.getUsersByEmail(email));
	}

	/**
	 * 根据Email修改对象
	 */
	@RequestMapping("/updateUsersByEmail")
	public ResponseVO updateUsersByEmail(Users bean,String email) {
		usersService.updateUsersByEmail(bean,email);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Email删除
	 */
	@RequestMapping("/deleteUsersByEmail")
	public ResponseVO deleteUsersByEmail(String email) {
		usersService.deleteUsersByEmail(email);
		return getSuccessResponseVO(null);
	}
}