/* 实体类 */


package xyz.zerotower.learn.pages.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Cart)实体类
 *
 * @author zerotower
 * @since 2020-09-30 11:20:03
 */


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