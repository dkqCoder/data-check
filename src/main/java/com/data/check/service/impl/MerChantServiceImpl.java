package com.data.check.service.impl;

import com.data.check.dao.MerChantInfoDao;
import com.data.check.dto.MerChantInfoDto;
import com.data.check.entity.MerChantInfoEn;
import com.data.check.service.MerChantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerChantServiceImpl implements MerChantService {
    private static Logger logger = LoggerFactory.getLogger(MerChantServiceImpl.class);


    @Autowired
    MerChantInfoDao merChantInfoDao;

    @Override
    public Integer register(String traceId, MerChantInfoDto merChantInfoDto) {
        Integer isSaveSucess = 0;
        try {
            isSaveSucess = merChantInfoDao.saveMerChantInfo(traceId, merChantInfoDto);
        } catch (Exception e) {
            logger.error("merChantInfo save error! traceId:{},e:{}",traceId,e);
        }

        return isSaveSucess;
    }

    @Override
    public MerChantInfoEn login(String traceId, MerChantInfoDto merChantInfoDto) {
        MerChantInfoEn merChantInfoEn = new MerChantInfoEn();
        try {
            merChantInfoEn = merChantInfoDao.getMerChantInfo(traceId,merChantInfoDto.getS_nick_name(),merChantInfoDto.getS_password());
        } catch (Exception e) {
            logger.error("login service error! traceId:{},e:{}",traceId,e);
        }
        return merChantInfoEn;
    }
}
