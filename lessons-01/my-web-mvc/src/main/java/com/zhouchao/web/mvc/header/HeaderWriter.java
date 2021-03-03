package com.zhouchao.web.mvc.header;

import java.util.List;
import java.util.Map;

/**
 * @Author zhouchao
 * @Date 2021/3/2 15:48
 * @Description
 **/

public interface HeaderWriter {
    void write(Map<String, List<String>> headers, String... headerValues);
}
