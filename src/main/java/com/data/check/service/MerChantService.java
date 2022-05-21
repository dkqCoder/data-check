package com.data.check.service;

import com.data.check.dto.MerChantInfoDto;
import com.data.check.entity.MerChantInfoEn;

public interface MerChantService {
    Integer register (String traceId, MerChantInfoDto merChantInfoDto);

    MerChantInfoEn login(String traceId,MerChantInfoDto merChantInfoDto);
}
