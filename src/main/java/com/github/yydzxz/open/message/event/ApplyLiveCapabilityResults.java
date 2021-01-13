package com.github.yydzxz.open.message.event;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/12/21
 **/
@Data
public class ApplyLiveCapabilityResults implements Serializable {

    private static final long serialVersionUID = 629054441616054404L;

    /**
     * 0、1或2。0代表不通过，1代表通过，2代表能力关闭
     */
    private Integer status;

    /**
     * 如果被拒，被拒原因
     */
    private String reason;
}
