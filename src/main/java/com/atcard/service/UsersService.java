package com.atcard.service;

import com.atcard.entity.po.Users;
import com.atcard.entity.query.UsersQuery;
import com.atcard.entity.vo.PaginationResultVO;

import javax.mail.MessagingException;
import java.util.List;


/**
 *  业务接口
 */
public interface UsersService {

	/**
	 * 根据条件查询列表
	 */
	List<Users> findListByParam(UsersQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UsersQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Users> findListByPage(UsersQuery param);

	/**
	 * 新增
	 */
	Integer add(Users bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Users> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Users> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Users bean,UsersQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UsersQuery param);

	/**
	 * 根据Id查询对象
	 */
	Users getUsersById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateUsersById(Users bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteUsersById(Integer id);


	/**
	 * 根据UserName查询对象
	 */
	Users getUsersByUserName(String userName) throws MessagingException;


	/**
	 * 根据UserName修改
	 */
	Integer updateUsersByUserName(Users bean,String userName);


	/**
	 * 根据UserName删除
	 */
	Integer deleteUsersByUserName(String userName);


	/**
	 * 根据Email查询对象
	 */
	Users getUsersByEmail(String email);


	/**
	 * 根据Email修改
	 */
	Integer updateUsersByEmail(Users bean,String email);


	/**
	 * 根据Email删除
	 */
	Integer deleteUsersByEmail(String email);

}