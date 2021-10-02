package bpp;

import org.apache.ibatis.annotations.*;

public interface TariffMapper {

    @Insert("insert into tariff (id, descr) values (#{id}, #{descr})")
    void createTariff(@Param("id") Integer id, @Param("descr") String descr);

    @Select("select id, descr from tariff where id = #{id}")
    Tariff getTariffById(@Param("id") String id);
}
