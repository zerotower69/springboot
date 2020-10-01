package xyz.zerotower.learn.pages;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PagesApplication.class)
@AutoConfigureMockMvc
class PagesApplicationTests {



    //注意：没有@Autowired注解会发生报错：  java.lang.NullPointerException
    //其它注意事项请参考：https://gitee.com/jinglun404/DLXD/issues/IGCM9
    @Autowired
    private MockMvc mvc;

//    @Test
//    public void getHello() throws Exception{
//        RequestBuilder request = MockMvcRequestBuilders.get("/hello").contentType(MediaType.APPLICATION_JSON);
//       mvc.perform(MockMvcRequestBuilders.get("/hello")
//               //.contentType(MediaType.TEXT_PLAIN_VALUE)
//       )
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("Hello World!")));
//    }
//
}
