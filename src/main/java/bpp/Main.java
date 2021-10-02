package bpp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "bpp")
public class Main {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);

        try {
            SqlSession session = sqlSessionFactory.openSession();
            SubscriberMapper subscriberMapper = session.getMapper(SubscriberMapper.class);
            TariffMapper tariffMapper = session.getMapper(TariffMapper.class);
            PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);

            tariffMapper.createTariff(1, "Tariff - 1");
            tariffMapper.createTariff(2, "Tariff - 2");
            int pId = 0;
            for (int i = 0 ; i < 10; i++) {
                subscriberMapper.createSubscriber(i, "Subscriber" + i, String.valueOf(1L));

                for(int j = 0; j < 3; j++) {
                    paymentMapper.createPayment(pId, i, j + 100);
                    pId++;
                }
            }

            List<Subscriber> subscribers = subscriberMapper.getSubscribers();
            System.out.println("dfd");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
