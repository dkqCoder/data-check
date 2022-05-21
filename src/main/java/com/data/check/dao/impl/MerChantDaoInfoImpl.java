package com.data.check.dao.impl;


import com.data.check.dao.MerChantInfoDao;
import com.data.check.dto.MerChantInfoDto;
import com.data.check.entity.MerChantInfoEn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MerChantDaoInfoImpl implements MerChantInfoDao {
    private static Logger logger = LoggerFactory.getLogger(MerChantDaoInfoImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public MerChantInfoEn getMerChantInfo(String traceId, String nickName, String passWord) {
        MerChantInfoEn merChantInfoEn = new MerChantInfoEn();
        try {
            merChantInfoEn = jdbcTemplate.queryForObject("SELECT * FROM merchant_info WHERE s_nick_name=? AND s_password=?",
                    new BeanPropertyRowMapper<>(MerChantInfoEn.class), nickName,passWord);
        } catch (Exception e) {
            logger.error("select merChantInfo by merChantId error! traceId:{},e:{}",traceId,e);
            return null;
        }
        return merChantInfoEn;
    }

    @Override
    public List<MerChantInfoEn> merChantInfoList(String traceId) {
        return null;
    }

    @Override
    public Integer saveMerChantInfo(String traceId, MerChantInfoDto merChantInfoDto) {
        Integer count = -1; // 注册失败
        try {
            Integer isExistence = checkMerChantName(traceId,merChantInfoDto.getS_nick_name());
            if (isExistence == 0) {
                String saveSql = "insert into merchant_info (n_merchant_id,s_nick_name,s_password,s_token,s_phone) " + "" +
                        "value (?,?,?,?,?)";
                count = jdbcTemplate.update(saveSql, merChantInfoDto.getN_merchant_id(), merChantInfoDto.getS_nick_name(), merChantInfoDto.getS_password(), merChantInfoDto.getS_token(), merChantInfoDto.getS_phone());
            } else if (isExistence == 1){
                count = -2; // 用户名已存在
            }

        } catch (Exception e) {
            count = -3;
            logger.error("merChantInfo save db error! traceId:{},e:{}",traceId,e);
        }

        return count;
    }

    @Override
    public Integer checkMerChantName(String traceId,String nickName) {
        Integer isExistence = 0;
        try {
            MerChantInfoEn merChantInfoEn = jdbcTemplate.queryForObject("SELECT * FROM merchant_info WHERE s_nick_name=?",
                    new BeanPropertyRowMapper<>(MerChantInfoEn.class), nickName);
            if (merChantInfoEn != null) {
                isExistence = 1;
            }
        } catch (Exception e) {
            logger.error("check merChantInfo by nickName error! traceId:{},e:{}",traceId,e);
            return isExistence;
        }
        return isExistence;
    }
}
