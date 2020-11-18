package com.github.yydzxz.open.api.v1.response.code;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.yydzxz.common.error.ByteDanceError;
import com.github.yydzxz.common.http.IByteDanceResponse;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/09/25
 **/
@Data
public class CodeAuditHostsResponse extends ByteDanceError implements IByteDanceResponse {

    private static final long serialVersionUID = -5630294923043007033L;

    private DataObj data;

    @Data
    public static class DataObj implements Serializable {

        private static final long serialVersionUID = 6679829880999868531L;

        /**
         * 本次审核可选宿主端英文简称
         */
        @SerializedName("hostNames")
        @JSONField(name = "hostNames")
        @JsonAlias("hostNames")
        @JsonProperty("hostNames")
        private List<String> hostNames;

        /**
         * 上次发布的宿主端英文简称
         */
        @SerializedName("releasedHostNames")
        @JSONField(name = "releasedHostNames")
        @JsonAlias("releasedHostNames")
        @JsonProperty("releasedHostNames")
        private List<String> releasedHostNames;
    }

}
