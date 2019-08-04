package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @Describe config
 * @Auther wenhao chen
 * @CreateDate 2019/8/4
 * @Version 1.0
 *
 * 总结：jar包的类使用xml配置，自己的类使用注解配置，相对更加方便
 * 配置类，替代xml文件
 * 新注解：
 * Configuration:
 *      作用：指定当前类是一个配置类
 *      配合ComponentScan使用
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，可以省略
 *          ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
 *          被@Import导入的配置类，可以省略
 * ComponentScan:
 *      作用：指定spring创建容器要扫描的包
 *      属性：
 *          value和basePackages在类ComponentScan中互为别名，作用一致，指定创建容易要扫描的包
 *          此注解等于xml中的
 *              <context:component-scan base-package="com.hao"></context:component-scan>
 *
 * Bean：
 *      作用：将当前方法的返回值作用bean对象存入spring的Ioc容器
 *      属性：
 *          name：指定bean的id，默认值是方法名
 *      细节;
 *          如果方法有参数，sring框架会去容器中查找有没有可用bean对象
 *          查找方式和Autowired注入方式一样
 * Import：
 *      作用：导入其他配置类
 *      属性：
 *          value：指定其他配置类的字节码文件
 *              使用Import的是父配置类，被导入的是子配置类
 *
 * PropertySource:
 *      作用：指定properties文件的位置
 *      属性：
 *          value：指定文件名称和路径
 *              classpath：表示在类路径下
 */
//@Configuration
@ComponentScan("com.hao")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {


}
