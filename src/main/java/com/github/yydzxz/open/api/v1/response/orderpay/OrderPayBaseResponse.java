package com.github.yydzxz.open.api.v1.response.orderpay;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class OrderPayBaseResponse implements Serializable {

    /**
     * 状态码 0-业务处理成功
     */
    @JSONField(name = "err_no")
    @JsonAlias("err_no")
    @JsonProperty("err_no")
    @SerializedName("err_no")
    private Integer errNo;

    /**
     * 错误提示信息，常见错误处理可参考附录常见问题章节
     */
    @JSONField(name = "err_tips")
    @JsonAlias("err_tips")
    @JsonProperty("err_tips")
    @SerializedName("err_tips")
    private String errTips;

    /**
     * 封装返回判断
     * @return
     */
    public Boolean checkSuccess() {
        return Objects.equals(this.errNo, 0);
    }


}
