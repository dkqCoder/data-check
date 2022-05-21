package com.data.check.entity;

import lombok.Data;

@Data
public class RechargeDetailEn {
    Integer n_id;

    Long n_merchant_id;

    String s_recharge_id;

    Double d_recharge_amount;

    Integer n_recharge_times;

    Integer n_status;

    String s_comment;

    String d_create_time;

    String d_update_time;


}
