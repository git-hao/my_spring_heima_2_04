package test;

import com.hao.domain.Account;
import com.hao.service.AccountService;
import config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Describe com.hao.test
 * @Auther wenhao chen
 * @CreateDate 2019/8/2
 * @Version 1.0
 *
 * spring整合junit测试
 *      1，导入spring整合junit jar包
 *      2，使用Junit提供的一个注解将默认junit的main方法替换为spring提供的
 *          @RunWith
 *      3，告知spring运行器，spring和Ioc的创建时基于xml，还是注解，并说明位置
 *          @ContextConfigruation:
 *              属性：
 *                  Location：指定xml文件的位置。加上classpath关键字，表示在类路径下
 *                  classes：指定注解类的所在地位置
 *
 *       使用spring5.x以上版本，需要4.12及以上版本的junit jar包
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
//@ContextConfiguration(Locations = "classpath:bean.xml")
public class AccountTest {


    @Autowired
    private AccountService as;

    @Test
    public void testFindAll(){

        //使用注解配置，获取容器，传入的参数，字节码配置类，是平级关系
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //得到对象
//        AccountService as = ac.getBean("accountService", AccountService.class);
        //执行方法
        List<Account> allAccount = as.findAllAccount();
        for (Account account:allAccount) {
            System.out.println(account);
        }

    }

    @Test
    public void testFindOne(){

        //执行方法
        Account account = as.findAccountById(3);
        System.out.println(account);
    }

    @Test
    public void testSave(){

        //执行方法
        Account acc = new Account();
        acc.setName("eee");
        acc.setMoney((float) 5000.0);
        as.saveAccount(acc);
        Account account = as.findAccountById(5);
        System.out.println(account);
    }

    @Test
    public void testUpdate(){

        //执行方法
        Account account = as.findAccountById(5);
        account.setMoney(1234f);
        as.updateAccount(account);
        Account account2 = as.findAccountById(5);
        System.out.println(account2);
    }

    @Test
    public void testDelete(){

        //执行方法
        as.delateAccount(5);
        List<Account> allAccount = as.findAllAccount();
        for (Account account:allAccount) {
            System.out.println(account);
        }
    }

    /**
     * 测试QueryRunner对象是否单例
     */
    @Test
    public void testQueryRunner(){
        //获取容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //得到对象
        QueryRunner runner = ac.getBean("runner", QueryRunner.class);
        QueryRunner runner2 = ac.getBean("runner", QueryRunner.class);
        System.out.println(runner == runner2);
    }

}
