package bpp;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubscriberMapper {
    @Select("select * from subscriber where id = #{id}")
    Subscriber getSubscriberById(@Param("id") Integer id);


    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "tariff", column = "ref_tariff", one = @One(select = "bpp.TariffMapper.getTariffById")),
            @Result(property = "payments", javaType = List.class, column = "id", many = @Many(select = "bpp.PaymentMapper.selectPaymentsForSubscriber"))
    })
    @Select("select * from subscriber")
    List<Subscriber> getSubscribers();

    @Insert("insert into subscriber (id, name, ref_tariff) values (#{id}, #{name}, #{ref_tariff})")
    void createSubscriber(@Param("id") Integer id, @Param("name") String name, @Param("ref_tariff") String refTariff);

}
