package com.lza.blog.controller;

import com.lza.blog.enums.ResultEnum;
import com.lza.blog.service.LogService;
import com.lza.blog.utils.Page;
import com.lza.blog.utils.Result;
import com.lza.blog.utils.StringUtils;
import com.lza.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: liuzian
 * @Date: Create in 14:11 2020/4/5
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;
    /**
     * 分页查询
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage",method = RequestMethod.POST)
    public Result<Page<BlogVo>> getByPage(@RequestBody Page<BlogVo> page){
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)){
            //排序列不为空
            String[] sortColumns = {"log_url","log_method","log_status","log_time","created_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())){
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"排序参数不合法!");
            }
        }
        page = this.logService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id){
        logService.delete(id);
        return new Result<>("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteByIds",method = RequestMethod.PUT)
    public Result<Object> deleteByIds(@RequestBody Integer[] ids){
        logService.deleteByIds(ids);
        return new Result<>("批量删除成功!");

    }

}
