package com.atcard.controller;

import com.atcard.entity.po.Users;
import com.atcard.entity.vo.ResponseVO;
import com.atcard.exception.BusinessException;
import com.atcard.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController("LoginController")
@RequestMapping("/users")
public class LoginController extends ABaseController{
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SEED = 123456789;
    private static final Random RANDOM = new Random(SEED);
    @Resource
    private UsersService usersService;

    @PostMapping("login")
    public ResponseVO login(@RequestBody Users loginUser){
        Users user = usersService.getUsersByUserName(loginUser.getUserName());
        if (!user.getPassword().equals(loginUser.getPassword())){
            throw new BusinessException(500,"账号或密码 错误");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        map.put("userName",user.getUserName());
        map.put("token",generateRandomString(64));
        return getSuccessResponseVO(map);
    }
    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (RANDOM.nextFloat() * CHARACTERS.length());
            builder.append(CHARACTERS.charAt(index));
        }
        return builder.toString();
    }
}
