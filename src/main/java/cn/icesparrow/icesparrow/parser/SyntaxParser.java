package cn.icesparrow.icesparrow.parser;

import cn.icesparrow.icesparrow.syntax.SyntaxTree;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: xiaobin.wu
 * @Date: 4/11/19 12:37 AM
 */
public class SyntaxParser {

    private static final char START_SYMBOL = '(';

    private static final char END_SYMBOL = ')';

    private static final char DECOLLATOR = ',';

    /**
     * 将字符串转为语法树
     *  xxx(1, 2)
     *  xxx(yyy(1, 2), zzz(1))
     * 示例: xxx(yyy1(123), yyy(zzz(), zzz2(), 333), 444)
     *
     * @param template
     * @return
     */
    public static SyntaxTree parse(String template) {
        SyntaxTree root = new SyntaxTree();
        parse(root, root, template);
        return root;
    }

    private static int parse(SyntaxTree root,SyntaxTree parent, String template) {
        Boolean exit = Boolean.FALSE;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (;i < template.length(); i++) {
            char ch = template.charAt(i);
            switch (ch) {
                case DECOLLATOR: {
                    String val = sb.toString().trim();
                    if(StringUtils.isNotBlank(val)) {
                        parent.addChild(new SyntaxTree(sb.toString().trim()));
                        sb = new StringBuilder();
                    }
                    break;
                }
                case END_SYMBOL: {
                    String val = sb.toString().trim();
                    if(StringUtils.isNotBlank(val)) {
                        parent.addChild(new SyntaxTree(sb.toString().trim()));
                        sb = new StringBuilder();
                    }
                    exit = Boolean.TRUE;
                    break;
                }
                case START_SYMBOL: {
                    SyntaxTree child = new SyntaxTree(sb.toString().trim());
                    parent.addChild(child);
                    i += parse(root, child, template.substring(i + 1)) + 1;
                    sb = new StringBuilder();
                    break;
                }
                default: {
                    sb.append(ch);
                }
            }
            if(exit) {
                break;
            }
        }
        return i;
    }

}
