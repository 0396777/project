package com.atcard.service.impl;

import com.atcard.entity.enums.PageSize;
import com.atcard.entity.po.Users;
import com.atcard.entity.query.SimplePage;
import com.atcard.entity.query.UsersQuery;
import com.atcard.entity.vo.PaginationResultVO;
import com.atcard.mappers.UsersMapper;
import com.atcard.service.UsersService;
import com.atcard.utils.SendMsg;
import com.atcard.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;


/**
 *  业务接口实现
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersMapper<Users, UsersQuery> usersMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Users> findListByParam(UsersQuery param) {
		return this.usersMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UsersQuery param) {
		return this.usersMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Users> findListByPage(UsersQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Users> list = this.findListByParam(param);
		PaginationResultVO<Users> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Users bean) {
		return this.usersMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Users> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.usersMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Users> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.usersMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Users bean, UsersQuery param) {
		StringTools.checkParam(param);
		return this.usersMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UsersQuery param) {
		StringTools.checkParam(param);
		return this.usersMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Users getUsersById(Integer id) {
		return this.usersMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateUsersById(Users bean, Integer id) {
		return this.usersMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteUsersById(Integer id) {
		return this.usersMapper.deleteById(id);
	}

	/**
	 * 根据UserName获取对象
	 */
	@Override
	public Users getUsersByUserName(String userName) throws MessagingException {
//		new SendMsg("2936281150@qq.com","2936281150@qq.com").sendVerifyCode("123456");
		return this.usersMapper.selectByUserName(userName);
	}

	/**
	 * 根据UserName修改
	 */
	@Override
	public Integer updateUsersByUserName(Users bean, String userName) {
		return this.usersMapper.updateByUserName(bean, userName);
	}

	/**
	 * 根据UserName删除
	 */
	@Override
	public Integer deleteUsersByUserName(String userName) {
		return this.usersMapper.deleteByUserName(userName);
	}

	/**
	 * 根据Email获取对象
	 */
	@Override
	public Users getUsersByEmail(String email) {
		return this.usersMapper.selectByEmail(email);
	}

	/**
	 * 根据Email修改
	 */
	@Override
	public Integer updateUsersByEmail(Users bean, String email) {
		return this.usersMapper.updateByEmail(bean, email);
	}

	/**
	 * 根据Email删除
	 */
	@Override
	public Integer deleteUsersByEmail(String email) {
		return this.usersMapper.deleteByEmail(email);
	}
}