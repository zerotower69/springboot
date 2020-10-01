/* Service 服务层 */


package xyz.zerotower.learn.pages.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.zerotower.learn.pages.entity.CartEntity;
import xyz.zerotower.learn.pages.entity.CartVo;

/**
 * (Cart)表服务接口
 *
 * @author ZeroTower
 * @since 2020-09-30 11:20:05
 */
public interface CartService extends IService<CartEntity> {
    /**
     * 分页操作
     * @param currentPage
     * @param limit
     * @return
     */
    IPage<CartEntity> selectByPage(int currentPage, int limit);
}