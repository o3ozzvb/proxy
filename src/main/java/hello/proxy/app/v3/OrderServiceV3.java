package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRespository;

    public OrderServiceV3(OrderRepositoryV3 orderRespository) {
        this.orderRespository = orderRespository;
    }

    public void orderItem(String itemId) {
        orderRespository.save(itemId);
    }
}
