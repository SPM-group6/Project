package com.hwadee.core.service;

import com.hwadee.entity.Crew;

public interface CommonService {

    Crew assignCrew(int authorityId);//选择指定类型中当前工作量最少的员工，工作量相同时，优先选择工号小的

}