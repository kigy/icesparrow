package cn.icesparrow.icesparrow.test;

import cn.icesparrow.icesparrow.parser.SyntaxParser;
import cn.icesparrow.icesparrow.syntax.SyntaxTree;
import org.junit.Test;

/**
 * @Author: xiaobin.wu
 * @Date: 4/11/19 1:39 AM
 */
public class SyntaxParserTest {

    @Test
    public void test() {

        SyntaxTree syntaxTree = SyntaxParser.parse("base64(123,1234, zzz(123), yyy(123, hhh(456)))");

        System.out.println(1);

    }

}
