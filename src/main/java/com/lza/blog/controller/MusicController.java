package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.pojo.Music;
import com.lza.blog.service.MusicService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 音乐
 * @Author: liuzian
 * @Date: Create in 22:58 2020/3/31
 */
@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService MusicService;

    /**
     * 添加
     * @param music
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Music music){
        MusicService.save(music);
        return new Result<>("添加成功！");
    }

    /**
     * 修改
     * @param music
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Music music){
        MusicService.update(music);
        return new Result<>("修改成功！");
    }
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Result<Music> getById(@PathVariable Integer id){
         Music Music =MusicService.getById(id);
        return new Result<>(Music);
    }


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        MusicService.delete(id);
        return new Result<>("删除成功!");
    }

    /**
     * 启用
     * @param id
     * @return
     */
    @RequestMapping(value = "/enableById/{id}",method = RequestMethod.PUT)
    public Result<Object> enableById(@PathVariable Integer id){
        MusicService.enableById(id);
        return new Result<>("启用成功!");
    }

    /**
     * 禁用
     * @param id
     * @return
     */
    @RequestMapping(value = "/disable/{id}",method = RequestMethod.PUT)
    public Result<Object> disable(@PathVariable Integer id){
        MusicService.disable(id);
        return new Result<>("禁用成功!");
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            //排序列不为空
            String[] sortColumns = {"artist","created_time","enabled"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法!");
            }
        }
        page = this.MusicService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 查询所有音乐
     * @return
     */
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public Result<List<Music>> getList(){
        List<Music> musicList = MusicService.getList();
        return new Result<>(musicList);
    }

}
