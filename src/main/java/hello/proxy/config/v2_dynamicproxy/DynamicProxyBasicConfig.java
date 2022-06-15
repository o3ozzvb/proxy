package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Proxy;

public class DynamicProxyBasicConfig {

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceBasicHandler(orderControllerV1, logTrace));

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRespositoryV1(logTrace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceBasicHandler(orderServiceV1, logTrace));

        return proxy;
    }

    @Bean
    public OrderRespositoryV1 orderRespositoryV1(LogTrace logTrace) {
        OrderRespositoryV1 orderRespository = new OrderRepositoryV1Impl();

        OrderRespositoryV1 proxy = (OrderRespositoryV1) Proxy.newProxyInstance(OrderRespositoryV1.class.getClassLoader(),
                new Class[]{OrderRespositoryV1.class},
                new LogTraceBasicHandler(orderRespository, logTrace));

        return proxy;
    }
}
