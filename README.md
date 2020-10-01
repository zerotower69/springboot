# 使用mybatis-plus 和element实现网页的分页功能

## 1.新建一个spring boot 项目，定义实体，建立好entity、dao、service(serviceImpl)和controller

[如何初始化一个springboot并进行单元测试]()

## 2.配置文件pom.xml需要导入的jar包

我的项目使用spring boot 2.3,请你自己测试前先去查看自己的spring boot版本。

```xml
<!--  仓库地址  https://mvnrepository.com/  -->

<!-- lombok 引入能自动生成get和set方法-->
<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
    <scope>provided</scope>
</dependency>

<!-- mybatis-plus  apply some api interface that can help you to aviod sql statement! -->
<!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-boot-starter -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.0</version>
</dependency>

<!-- mysql 8.0.21 mysql database jar-->
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.21</version>
</dependency>

<!--hikari jdbc -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

<!-- template enigne :thymeleaf -->
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
    <version>2.3.4.RELEASE</version>
</dependency>
```

## 3. 配置类

此类文件目录在application类的同级config目录下。

```java
/*此部分配置代码来自官网：https://baomidou.com/guide/page.html*/
@Configuration
@MapperScan("xyz.zerotower.learn.pages.dao")  //dao层目录
public class MybatisPlusConfig{
    /**
     * 分页配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
```

## 4.新建返回实体类

补充说明我数据库的实体类 这里请自己定义一个自己的数据库实体类，不需要和我的一样，我只是给出参考。

```java
//cart 实体
@Data
@TableName("cart")
public class CartEntity implements Serializable {
    private static final long serialVersionUID = -57491392627813879L;
    @TableId
    /**
     * 购物车自增id
     */
    private Long id;
    /**
     * 用户的名字
     */
    private String userName;
    /**
     * 用户的id
     */
    private String userId;
    /**
     * 商品的名字
     */
    private String productName;
    /**
     * 商品对应的独一无二的编码
     */
    private String productCode;
    /**
     * 商品的所属分类
     */
    private String productCategory;
    /**
     * 单件的价格
     */
    private Double eachPrice;
    /**
     * 购买的数量
     */
    private Integer productNum;
    /**
     * 总价
     */
    private Double amount;
    /**
     * 订单激活态，判断商品属于购物车还是订单，0  购物车；1 订单
     */
    private Integer active;
    /**
     * 订单代码
     */
    private String orderCode;
    /**
     * 所属订单的创建时间
     */
    private Date createTime;

}
```

下面的实体类最好与我的一样

注意：变量的名字最好能表达合适的中文意思：如 当前页用current,使用page，只知道页，不容易看出是当前页；此外，能用一个单词解决的事绝不用两个单词，例如：current 比currentPage好（虽然currentPage表达更完整，但实际开发中current在语义上已经表达完整）

```java
public class CartVo implements Serializable {
    private Integer current;  //当前页
    private Integer limit; //一页的数据条数
    private Long total;   //总数据数
    List<CartEntity> data; //返回的数据
    private Long pages ;  //页面数
}
```

## 5.服务接口以及实现

```java
//定义接口 CartService
public interface CartService extends IService<CartEntity> {
    /**
     * 分页操作
     * @param currentPage
     * @param limit
     * @return
     */
    IPage<CartEntity> selectByPage(int currentPage, int limit);  //定义分页功能
}

//定义服务接口实现类 CartServiceImpl

@Service("cartService")   //此注解不可缺少
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {
    @Autowired
    private CartDao cartDao;  //注入Dao层

    @Override
    public IPage<CartEntity> selectByPage(int currentPage, int pageSize) {
        //查询对象
        //QueryWrapper<CartEntity> wrapper=new QueryWrapper<>();
        //分页，第一个参数是第几页，第二个是每页多少条数据
        Page<CartEntity> page=new Page<>(currentPage,pageSize);
        //
        IPage<CartEntity> cartIpage =cartDao.selectPage(page,null);
        return cartIpage;
    }
}

```

## 6.控制层url接口

```java
//定义一个controller类，
 @RequestMapping("/api/list/carts")
    public CartVo getPage(@RequestParam Integer currentPage,
                          @RequestParam Integer pageSize,
                          HttpServletResponse response) {
        CartVo cartVo = new CartVo();
        IPage<CartEntity> cartPage = cartService.selectByPage(currentPage, pageSize);
        //System.out.println(cartPage+" "+cartPage.getRecords()+" "+cartPage.getTotal());
        cartVo.setPages(cartPage.getPages());
        cartVo.setLimit(pageSize);
        cartVo.setTotal(cartPage.getTotal());
        cartVo.setData(cartPage.getRecords());
        cartVo.setCurrent(currentPage);
        //System.out.println(cartVo);
        response.setStatus(200);
        return cartVo;

    }
```

