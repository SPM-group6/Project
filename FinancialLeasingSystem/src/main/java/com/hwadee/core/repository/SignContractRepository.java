package com.hwadee.core.repository;

import com.hwadee.entity.SignContract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;

/**
 * @Author LiuHong
 * @CreateTime 2022/3/7 20:25
 * @Version 1.0.0
 */

@Repository
public interface SignContractRepository {
    public String queryContractByPId(@Param("pId") int pId);//查询指定项目的合同

    public List<SignContract> queryContract();

    public Integer uploadContract(@Param("contract") SignContract signContract);//上传指定项目的合同
}
