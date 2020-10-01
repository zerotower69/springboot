/* ServiceImpl 服务实现类 */


package xyz.zerotower.learn.pages.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zerotower.learn.pages.dao.CartDao;
import xyz.zerotower.learn.pages.entity.CartEntity;
import xyz.zerotower.learn.pages.entity.CartVo;
import xyz.zerotower.learn.pages.service.CartService;

/**
 * (Cart)表服务实现类
 *
 * @author ZeroTower
 * @since 2020-09-30 11:20:05
 */
@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    public IPage<CartEntity> selectByPage(int currentPage, int pageSize) {
        //查询对象
        //QueryWrapper<CartEntity> wrapper=new QueryWrapper<>();
        //分页，第一参数是第几页，第二个是每页多少条数据
        Page<CartEntity> page=new Page<>(currentPage,pageSize);
        //
        IPage<CartEntity> cartIpage =cartDao.selectPage(page,null);
        return cartIpage;
    }
}