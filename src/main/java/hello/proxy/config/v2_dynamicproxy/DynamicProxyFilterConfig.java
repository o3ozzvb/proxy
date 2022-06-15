package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Proxy;

public class DynamicProxyFilterConfig {

    private static final String[] PATTERNS = {"request", "order*", "save*"};


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1 orderControllerV1 = new OrderControllerV1Impl(orderServiceV1(logTrace));

        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderControllerV1, logTrace, PATTERNS));

        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1 orderServiceV1 = new OrderServiceV1Impl(orderRespositoryV1(logTrace));

        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderServiceV1, logTrace, PATTERNS));

        return proxy;
    }

    @Bean
    public OrderRespositoryV1 orderRespositoryV1(LogTrace logTrace) {
        OrderRespositoryV1 orderRespository = new OrderRepositoryV1Impl();

        OrderRespositoryV1 proxy = (OrderRespositoryV1) Proxy.newProxyInstance(OrderRespositoryV1.class.getClassLoader(),
                new Class[]{OrderRespositoryV1.class},
                new LogTraceFilterHandler(orderRespository, logTrace, PATTERNS));

        return proxy;
    }
}
