package com.jiay.article.service;

import com.jiay.common.result.Code;
import com.jiay.common.result.SingleResult;
import org.springframework.stereotype.Component;

/**
 * @author jiay
 * @date 2020/3/20
 */
@Component
public class ApiServiceError implements ApiService {
    @Override
    public SingleResult internalAuthorize() {
        return SingleResult.buildFailure(Code.ERROR,"authorize service error");
    }
}
