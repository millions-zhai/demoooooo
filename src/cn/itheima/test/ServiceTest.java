package cn.itheima.test;

import cn.itheima.domain.Order;
import cn.itheima.service.IOrderService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by millions on 2017/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ServiceTest {

    @Autowired
    private IOrderService orderService;

    @Test
    @Transactional
    public void test(){
        List<Order> orderByCustomer = orderService.findOrderByCustomer(2);
        PropertyFilter filter = new PropertyFilter() {

            @Override
            public boolean apply(Object arg0, String fieldName, Object arg2) {
                if ("cusPhone".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                if ("id".equalsIgnoreCase(fieldName)) {
                    return false;
                }
                if ("orders".equalsIgnoreCase(fieldName)) {
                    return false;
                }

                return true;
            }
        };
        String json = JSONArray.toJSONString(orderByCustomer,filter,SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(json);

    }
}
