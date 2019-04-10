package cn.icesparrow.icesparrow.render;

import cn.icesparrow.icesparrow.syntax.SyntaxTree;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * @Author: xiaobin.wu
 * @Date: 4/10/19 11:47 PM
 *
 */
public class IceSparrowExcutor {

    private static IceSparrowExcutor instance = new IceSparrowExcutor();

    public static IceSparrowExcutor getInstance() {
        return instance;
    }

    public IceSparrowExcutor grammar(String funcName, Function<List<String>, String> func) {
        funcs.put(funcName, func);
        return this;
    }

    private Map<String, Function<List<String>, String>> funcs = Maps.newHashMap();

    public String excute(SyntaxTree tree, JSONObject ctx) {
        //如果没有子语法，则可以直接返回当前语法树的结果作为最终结果
        if(!tree.hasChildren()) {
            //如果语法树的值是数字，则直接返回数字
            if(NumberUtils.isDigits(tree.getSymbol())) {
                return tree.getSymbol();
            }
            //否则则从上下文中获取该语法树的值
            return getContextValue(ctx, tree.getSymbol());
        }
        List<String> list = Lists.newArrayList();
        //分别计算所有子语法树的值
        for(SyntaxTree child : tree.getChildren()) {
            list.add(excute(child, ctx));
        }
        //计算根节点的语法树的值
        Function<List<String>, String> func = funcs.get(tree.getSymbol());
        return func.apply(list);
    }

    private String getContextValue(JSONObject ctx, String key) {
        int idx = key.indexOf('.');
        if(idx <= 0) {
            String val = ctx.getString(key);
            return StringUtils.isNotBlank(val) ? val : StringUtils.EMPTY;
        }
        String subCtx = ctx.getString(key.substring(0, idx));
        if(StringUtils.isBlank(subCtx)) {
            return StringUtils.EMPTY;
        }
        return getContextValue(JSONObject.parseObject(subCtx), key.substring(idx + 1));
    }


}
