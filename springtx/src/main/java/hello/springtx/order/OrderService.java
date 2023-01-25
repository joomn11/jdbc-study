package hello.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    // JPA는 트랜잭션 커밋 시점에 Order 데이터를 DB에 반영한다.
    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {
        log.info(" call order");
        orderRepository.save(order);

        log.info("dive into pay process");
        if (order.getUsername().equals("예외")) {
            log.info(" occur system exception");
            throw new RuntimeException("occur system exception");
        } else if (order.getUsername().equals("잔고부족")) {
            log.info(" occur biz exception");
            order.setPayStatus("대기");
            throw new NotEnoughMoneyException("not enough money on your account");
        } else {
            // success case
            log.info("accepted");
            order.setPayStatus("완료");
        }
        log.info("end of pay process");
    }
}
