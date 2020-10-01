/* controller 控制层 */
package xyz.zerotower.learn.pages.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.zerotower.learn.pages.entity.CartEntity;
import xyz.zerotower.learn.pages.entity.CartVo;
import xyz.zerotower.learn.pages.service.CartService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * (Cart)表控制层
 *
 * @author ZeroTower
 * @since 2020-09-30 11:20:06
 */

@RestController
@RequestMapping("cart")
@Api(value="cart",description = " test cart")  //接口描述
public class CartController {
    /**
     * 服务对象
     */
    @Autowired
    private CartService cartService;

    /**
     * 列出所有的数据
     *
     * @return 表中所有的结果
     */

    @RequestMapping("/list")
        @ApiOperation(value = "列出所有的carts",notes = "直接列出，无输入参数")  //接口描述
    public List<CartEntity> listAll() {
        return cartService.list();
    }

    /**
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    //@CrossOrigin
    @RequestMapping("/api/list/carts")
        @ApiOperation(value="购物商品分页列表",notes = "列出所有的carts,但是根据页面选中来的")
        //@ApiImplicitParam()
    public CartVo getPage(@ApiParam(value="当前页码",required = true) @RequestParam Integer currentPage,
                          @ApiParam(value="每页记录数",required = true) @RequestParam Integer pageSize,
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
    /**
     * 更新或者插入一条数据
     *
     * @return void
     */
    @RequestMapping("/update/one")
    @ResponseBody
    public void updateOne(CartEntity entity) {
        cartService.saveOrUpdate(entity);
        return;
    }
}

/* 能力有限，只给出了 list 方法 其余自己加入*/