package com.github.yydzxz.open.api.response.appinfo;

import com.github.yydzxz.common.error.ByteDanceError;
import java.util.List;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/23
 **/
@Data
public class AppModifyWebviewDomainResponse extends ByteDanceError {

    private static final long serialVersionUID = 5482713935876030732L;

    private List<String> data;
}
