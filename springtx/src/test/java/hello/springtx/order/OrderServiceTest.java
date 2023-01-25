package hello.springtx.order;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService service;

    @Autowired
    OrderRepository repository;

    @Test
    void complete() throws NotEnoughMoneyException {
        //given
        Order order = new Order();
        order.setUsername("정상");
        // when
        service.order(order);
        //then
        Order findOrder = repository.findById(order.getId()).get();
        Assertions.assertThat(findOrder.getPayStatus()).isEqualTo("완료");
    }

    @Test
    void runtimeException() {
        //given
        Order order = new Order();
        order.setUsername("예외");
        // when
        Assertions.assertThatThrownBy(() -> service.order(order)).isInstanceOf(RuntimeException.class);
        //then: no data, because runtimeException is rollback policy
        Optional<Order> findOrder = repository.findById(order.getId());
        Assertions.assertThat(findOrder.isEmpty()).isTrue();
    }

    @Test
    void bizException() {
        //given
        Order order = new Order();
        order.setUsername("잔고부족");
        //when
        try {
            service.order(order);
            Assertions.fail(" occur NotEnoughMoneyException");
        } catch (NotEnoughMoneyException e) {
            log.info(" notice not enough money to customer and send new account for send money");
        }
        //then
        Order findOrder = repository.findById(order.getId()).get();
        Assertions.assertThat(findOrder.getPayStatus()).isEqualTo("대기");
    }
}