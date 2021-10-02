package bpp;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PaymentMapper {
    @Insert("insert into payments (id, ref_subscriber, summa) values (#{id}, #{ref_subscriber}, #{summa})")
    void createPayment(@Param("id") Integer id, @Param("ref_subscriber") Integer refSubscriber, @Param("summa") Integer summa);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "summa", column = "summa")
    })
    @Select("select id, ref_subscriber, summa from payments where ref_subscriber = #{ref_subscriber}")
    List<Payment> selectPaymentsForSubscriber(@Param("ref_subscriber") String subscriberId);
}
