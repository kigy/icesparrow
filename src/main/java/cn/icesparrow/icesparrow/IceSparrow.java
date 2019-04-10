package cn.icesparrow.icesparrow;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author: xiaobin.wu
 * @Date: 4/10/19 10:23 PM
 */
public class IceSparrow {

    private static IceSparrow instance = new IceSparrow();

    public static IceSparrow getInstance() {
        return instance;
    }

    private Map<String, Function<String, String>> funcs = Maps.newHashMap();

    public void registerFunction(String name, Function<String, String> func) {
        funcs.put(name, func);
    }

    public String render(String template, JSONObject ctx) {
        return StringUtils.EMPTY;
    }

    /**
     * 渲染一个el表达式的值
     * @param el
     * @param ctx
     * @return
     */
    private String renderOneELExpression(String el, JSONObject ctx) {

        return StringUtils.EMPTY;
    }

}
