package xyz.zerotower.learn.pages.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回前端的分页
 * @param
 */
@Data
public class CartVo implements Serializable {

    private Integer current;  //当前页
    private Integer limit; //一页的数据条数
    private Long total;   //总数据数
    List<CartEntity> data; //返回的数据
    private Long pages ;  //页面数
}
