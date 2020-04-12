package com.lza.blog.service.impl;


import com.lza.blog.dao.CollectionDao;
import com.lza.blog.dao.CommentDao;
import com.lza.blog.enums.ResultEnum;
import com.lza.blog.exception.BlogException;
import com.lza.blog.mapper.UserMapper;
import com.lza.blog.pojo.Music;
import com.lza.blog.pojo.User;
import com.lza.blog.service.UserService;
import com.lza.blog.utils.Md5Utils;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表服务实现类
 * </p>
 *
 * @author liuzian
 * @date 2020-3-29 14:04:12
 * @Version 1.0
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CollectionDao collectionDao;


    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public Page<User> getByPage(Page<User> page) {
        //查询数据
        List<User> userList = userMapper.getByPage(page);
        page.setList(userList);
        //查询总数
        int totalCount = userMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void resetPwd(List<Integer> userIds) {
        //根据ids查询所有用户
        List<User> userList = userMapper.getUserByIds(userIds);
        userList.forEach(e->{
            e.setPassword(Md5Utils.toMD5("123456"));
            userMapper.update(e);
        });
    }

    @Override
    public void register(User user) {
        //首先查看是否存在相同的用户名
        User u = userMapper.isNotExist(user.getUsername());
        if (u!=null){
            throw new BlogException(ResultEnum.PARAMS_ERROR.getCode(),"用户名已存在");
        }
        userMapper.save(user);
    }

    @Override
    public User getUserByName(String username) {

        return userMapper.isNotExist(username);
    }
    @Override
    public Map<String, Object> getCommentAndCollectionCount() {
        User user = (User) ShiroUtils.getLoginUser();
        int commentCount = commentDao.countByCommentUser(user.getUserId());
        int collectionCount = collectionDao.countByUserId(user.getUserId());
        Map<String, Object> map = new HashMap<>(4);
        map.put("commentCount", commentCount);
        map.put("collectionCount", collectionCount);
        return map;
    }
    @Override
    public void updateInfo(User user) {
        userMapper.updateInfo(user);
    }

}
