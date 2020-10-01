/* dao层 */
package xyz.zerotower.learn.pages.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zerotower.learn.pages.entity.CartEntity;


/**
 * (Cart)表数据库访问层
 *
 * @author ZeroTower
 * @since 2020-09-30 11:20:04
 */
//@Repository
@Mapper
public interface CartDao extends BaseMapper<CartEntity> {

}