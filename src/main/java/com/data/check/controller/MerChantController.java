package com.data.check.controller;

import com.data.check.dto.MerChantInfoDto;
import com.data.check.entity.MerChantInfoEn;
import com.data.check.model.ResultModel;
import com.data.check.service.MerChantService;
import com.data.check.utils.UidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/merChant")
public class MerChantController {
    private static Logger logger = LoggerFactory.getLogger(MerChantController.class);

    @Autowired
    private MerChantService merChantService;

    @PostMapping(value = "/login")
    public ResultModel login(@RequestBody MerChantInfoDto merChantInfoDto) {
        String traceId = UUID.randomUUID().toString();
        ResultModel resultModel = new ResultModel();
        try {
            MerChantInfoEn merChantInfoEn = merChantService.login(traceId,merChantInfoDto);
            if (merChantInfoEn != null) {
                resultModel.setCode(ResultModel.SUCCESS);
                resultModel.setData(merChantInfoEn);
                resultModel.setMsg("登录成功");
            } else {
                resultModel.setCode(ResultModel.ERROR);
                resultModel.setData(merChantInfoEn);
                resultModel.setMsg("用户名或密码错误");
            }

        } catch (Exception e) {
            logger.error("login controller error! traceId:{},e:{}",traceId,e);
            resultModel.setCode(ResultModel.ERROR);
            resultModel.setData("");
            resultModel.setMsg("用户名或密码错误");
        }
        return resultModel;
    }

    @PostMapping(value = "/register")
    public ResultModel register (@RequestBody MerChantInfoDto merChantInfoDto) {
        String traceId = UUID.randomUUID().toString();
        ResultModel resultModel = new ResultModel();
        try {
            Long merChantId = UidUtil.generateId();
            merChantInfoDto.setN_merchant_id(merChantId);
            Integer isSaveSucess = merChantService.register(traceId,merChantInfoDto);
            if (isSaveSucess > 0) {
                resultModel.setCode(ResultModel.SUCCESS);
                resultModel.setData(isSaveSucess);
                resultModel.setMsg("用户注册成功");
            } else if (isSaveSucess == -1){
                resultModel.setCode(ResultModel.ERROR);
                resultModel.setData(isSaveSucess);
                resultModel.setMsg("用户注册失败");
            } else if (isSaveSucess == -2) {
                resultModel.setCode(ResultModel.ERROR);
                resultModel.setData(isSaveSucess);
                resultModel.setMsg("用户名已存在");
            }

        } catch (Exception e) {
            logger.error("save merChant error ! traceId:{},e:{}",traceId,e);
            resultModel.setCode(ResultModel.ERROR);
            resultModel.setData(false);
            resultModel.setMsg(e.getMessage());
        }
        return resultModel;
    }
}
