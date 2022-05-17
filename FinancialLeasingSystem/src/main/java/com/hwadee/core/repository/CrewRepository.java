package com.hwadee.core.repository;

import com.hwadee.entity.Crew;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepository {
    /**
     * 查询所有业务员
     */
    List<Crew> queryAllSales();

    /**
     * 查询所有指定类型的后台职员
     */
    List<Crew> queryCrewByAuthorityId(int authorityId);

    /**
     * 查询和指定用户合作过的业务员
     */
    public List<Crew> querySalesByUserId(int userId);

    /**
     * 查询其他(没有和指定用户合作过的业务员)
     */
    public List<Crew> queryOtherSalesByUserId(int userId);

    /**
     * 查询职员密码
     */
    String queryPassword(@Param("staffId") int staffId);

}
