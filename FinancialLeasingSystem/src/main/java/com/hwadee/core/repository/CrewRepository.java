package com.hwadee.core.repository;

import com.hwadee.entity.Crew;
import com.hwadee.entity.LoginCrew;
import com.hwadee.entity.TemCrew;
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
     * 查询和指定ID的后台工作人员
     */
    public List<Crew> queryCrewById(int Id);

    /**
     * 查询其他(没有和指定用户合作过的业务员)
     */
    public List<Crew> queryOtherSalesByUserId(int userId);

    /**
     * 查询职员密码
     */
    String queryPassword(@Param("staffId") int staffId);

    /**
     * 新建职员记录（仅后端建立数据使用，无前端）
     */
    int crewSignUp(@Param("crew") TemCrew crew);


}
