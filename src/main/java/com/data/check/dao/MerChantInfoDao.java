package com.data.check.dao;

import com.data.check.dto.MerChantInfoDto;
import com.data.check.entity.MerChantInfoEn;

import java.util.List;

public interface MerChantInfoDao {

    /**
     * 根据用户名，密码查询
     * @param traceId
     * @param nickName
     * @param passWord
     * @return
     */
    MerChantInfoEn getMerChantInfo (String traceId, String nickName, String passWord);

    /**
     * 查询所以用户
     * @param traceId
     * @return
     */
    List<MerChantInfoEn> merChantInfoList (String traceId);

    /**
     * 保存用户信息
     * @param traceId
     * @param merChantInfoDto
     * @return
     */
    Integer saveMerChantInfo (String traceId, MerChantInfoDto merChantInfoDto);

    /**
     * 根据用户名检查是否存在
     * @param traceId
     * @param nickName
     * @return
     */
    Integer checkMerChantName(String traceId, String nickName);


}
