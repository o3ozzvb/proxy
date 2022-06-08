package hello.proxy.app.v2;


public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRespository;

    public OrderServiceV2(OrderRepositoryV2 orderRespository) {
        this.orderRespository = orderRespository;
    }

    public void orderItem(String itemId) {
        orderRespository.save(itemId);
    }
}
