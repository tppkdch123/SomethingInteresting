package org.somthing.yellow.service;

import org.somthing.yellow.util.UnifiedResponse;
import org.somthing.yellow.vo.FirstVO;

/**
 * Created by huangshizhe on 2018/8/18
 */

public interface RandomService {
    FirstVO getResult(String params, Double value);

    String getResultByString(String params, Double value);
}
