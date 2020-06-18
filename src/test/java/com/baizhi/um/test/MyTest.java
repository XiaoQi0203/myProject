package com.baizhi.um.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.um.Application;
import com.baizhi.um.dao.MenuDao;
import com.baizhi.um.dao.SexDao;
import com.baizhi.um.dao.UserDao;
import com.baizhi.um.entity.Menu;
import com.baizhi.um.entity.Sex;
import com.baizhi.um.entity.User;
import com.baizhi.um.service.MenuService;
import com.baizhi.um.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author SIMBA1949
 * @Date 2020/6/1 5:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MyTest {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SexDao sexDao;

    @Test
    public void test1(){
        //1.添加
//        User user = new User(1,"zhangsan1",1,"123456",new Date(),"13569928505","13569928505@163.com");
//        userService.saveUser(user);
        //2.修改
//        User user = new User(4,"",2,"",new Date(),"","13569928505@163.com");
//        userService.updateUser(user);
        //3.删除单个
//        userService.deleteByUserId(1);
        //4.删除多个
//        Integer[] ids ={2,3};
//        userService.deleteByUserIds(ids);
        //5.查询总条数
//        int i = userService.queryCount("name", "zhangsan");
//        System.out.println(i);
        //6.根据id查询
//        User user = userService.queryUserById(4);
//        System.out.println(user);
        //7.登录
//        User user = new User();
//        user.setName("zhangsan");
//        user.setPassword("123456");
//        User user1 = userService.queryUserByNameAndPassword(user);
//        System.out.println(user1);
        //8.分页+条件查询
        Map map = userService.queryUserByPage(1, 100, "", "");
        for (Object o : map.entrySet()) {
            System.out.println(o);
        }

    }
    @Test
    public void test5(){
        List<User> users = userDao.queryUserByPage(1, 100, "", "");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        RestTemplate restTemplate = new RestTemplate();
        //
        MultiValueMap<String, Object> requestFormat = new LinkedMultiValueMap<>();
        requestFormat.set("name","zhangsan");
        requestFormat.set("password","123456");

        String  user = restTemplate.postForObject("http://localhost:8088/user/userLogin", requestFormat, String.class);
        System.out.println(user);
    }
    @Test
    public void test3(){
//        Cache
//        PerpetualCache
    }

    /*public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i=0;i<10;i++){
            int andIncrement = atomicInteger.getAndIncrement();
            System.out.println(andIncrement);
        }
    }*/
    @Test
    public void test4(){
        String str = "asda.asda.3.jpg";
        String substring = str.substring(str.lastIndexOf("."));
        System.out.println(substring);
        String substring1 = substring.substring(1, substring.length());
        System.out.println(substring1);
    }
    @Test
    public void Test5(){
//        List<Menu> menus = menuService.queryMenuAll();
//        for (Menu menu : menus) {
//            System.out.println(menu);
//        }
        List<Menu> menus = menuDao.queryMenuAll();
        for (Menu menu : menus) {
            System.out.println(menu);
        }
    }
    @Test
    public void test6(){
//        List<User> users = userService.selectUserAll();
//        for (User user : users) {
//            System.out.println(user);
//        }
//        List<User> users = userService.selectUserByPage(1, 3);
//        for (User user : users) {
//            System.out.println(user);
//        }
    }
    @Test
    public void test7() throws IOException {

        List<User> users = userService.selectUserAll();
        for (User user : users) {
            System.out.println(user);
        }

        ExportParams exportParams = new ExportParams("用户信息", "用户信息表");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
        workbook.write(new FileOutputStream("d:/a.xls"));
    }
    @Test
    public void test8(){
        List<Sex> sexes = sexDao.selectCount();
        for (Sex sex : sexes) {
            if ("1".equals(sex.getSex())){
                sex.setSex("男");
            }else{
                sex.setSex("女");
            }
        }
        for (Sex sex : sexes) {
            System.out.println(sex);
        }
    }
}
