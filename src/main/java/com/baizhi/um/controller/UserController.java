package com.baizhi.um.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.um.entity.User;
import com.baizhi.um.service.UserService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:39
 */
@RestController //相当于@controller+@responceMapping
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;


    /**
     * @param user
     * @param multipartFile
     * @return
     * @throws IOException
     * @RequestMapping=没有限制，@PostMapping=post请求，@GetMapping=get请求
     * @RequestMapping(value = "/userLogin",method = RequestMethod.GET)
     * @RequestMapping()
     */
    //注册
    @RequestMapping("/saveUser")
    public String saveUser(User user, MultipartFile multipartFile,HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(user);
        System.out.println(multipartFile);
        System.out.println("===========saveUser==============");
        //获取文件名
        String filename = multipartFile.getOriginalFilename();
        //文件名后缀
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String substring = suffixName.substring(1, suffixName.length());

        InputStream inputStream = multipartFile.getInputStream();
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(inputStream, inputStream.available(), substring, null);

        //上传图片的file_id
        String file_id = storePath.getFullPath();
        //拼接上原文件名，保存到数据库
        String fileId = file_id + "***" + multipartFile.getOriginalFilename();


        user.setPhoto(fileId);
        System.out.println(user);
        System.out.println("-------saveUser--------");

        userService.saveUser(user);
        Map map = new HashMap();
        map.put("message","success");
        return "";
    }

    //登录
    @RequestMapping("/userLogin")
    public Map userLogin(String name, String password,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(name+"   "+password);
        System.out.println("================userLogin==============");
        User us = new User();
        us.setName(name);
        us.setPassword(password);
        User user = userService.queryUserByNameAndPassword(us);
        System.out.println("model:  "+user);

        Map map = new HashMap();
        map.put("name",user.getName());
        map.put("sex",user.getSex());
        map.put("password",user.getPassword());
        map.put("email",user.getEmail());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateday = format.format(user.getBirthday());
        map.put("birthday",dateday);
        return map;
    }

    //根据id查询
    @RequestMapping("/queryUserById")
    public Map queryUserById(Integer id,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(id);
        System.out.println("===============queryUserById===============");
        User user = userService.queryUserById(id);
        Map map = new HashMap();
        map.put("user",user);
        return map;
    }

    //根据id删除
    @RequestMapping(value = "/deleteUserById",method = RequestMethod.DELETE)
    public void deleteUserById(Integer id,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(id);
        System.out.println("======================deleteUserById=======================");
        userService.deleteByUserId(id);
    }

    //根据id批量删除
    @RequestMapping("/deleteUserByIds")
    public Map deleteUserByIds(Integer[] ids,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(Arrays.asList(ids));
        System.out.println("==============deleteUserByIds=================");
        userService.deleteByUserIds(ids);
        Map<Object, Object> map = new HashMap<>();
        map.put("message","success");
        return map;
    }

    //分页查询，带条件
    @RequestMapping("/queryUserByPage")
    public Map<String,Object> queryUserByPage(@RequestParam(value = "rows",defaultValue = "10") int rows,
                                   @RequestParam(value = "page",defaultValue = "1") int page,
                                   @RequestParam(value = "column",required = false) String column,
                                   @RequestParam(value = "value",required = false) Object value,
                                              HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(rows+"   "+page+"  "+column+"   "+value);
        System.out.println("================queryUserByPage==================");

        Map<String,Object> map = userService.queryUserByPage(rows, page, column, value);
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            System.out.println(stringObjectEntry.getKey());
            System.out.println(stringObjectEntry.getValue());
        }
        return map;
    }

    //查询总条数
    @RequestMapping("/queryCount")
    public void queryCount(String column, Object value,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(column+"     "+value);
        System.out.println("===============queryCount==================");

        int count = userService.queryCount(column, value);

    }

    //修改
    @RequestMapping("/updateUser")
    public void updateUser(User user,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println(user);
        System.out.println("===============updateUser================");

        userService.updateUser(user);
    }

    //查询全部
    @RequestMapping("/selectUserAll")
    public List selectUserAll(HttpServletResponse response) throws IOException{
        response.setHeader("Access-Control-Allow-Origin","*");

        List<User> users = userService.selectUserAll();

        return users;
    }
    //查询当前页
    @RequestMapping("/selectUserPage")
    public List<User> selectUserPage(Integer pageIndex,Integer rows,HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");

        List<User> users = userService.selectUserByPage(pageIndex, rows);


        return users;
    }
}
