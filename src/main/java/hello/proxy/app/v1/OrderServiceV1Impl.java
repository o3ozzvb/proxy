package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRespositoryV1 orderRespository;

    public OrderServiceV1Impl(OrderRespositoryV1 orderRespository) {
        this.orderRespository = orderRespository;
    }

    @Override
    public void orderItem(String itemId) {
        orderRespository.save(itemId);
    }
}
