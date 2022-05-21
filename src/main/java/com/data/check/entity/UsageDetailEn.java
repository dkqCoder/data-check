package com.data.check.entity;

import lombok.Data;

@Data
public class UsageDetailEn {

    Integer n_id;

    Long n_merchant_id;

    Integer n_usage_times;

    Integer n_self_times;

    Integer n_other_times;

    String s_p_day;

    String d_create_time;

    String d_update_time;
}
