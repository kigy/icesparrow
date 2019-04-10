package cn.icesparrow.icesparrow.test;

import cn.icesparrow.icesparrow.parser.SyntaxParser;
import cn.icesparrow.icesparrow.render.IceSparrowExcutor;
import cn.icesparrow.icesparrow.syntax.SyntaxTree;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @Author: xiaobin.wu
 * @Date: 4/11/19 12:13 AM
 */
public class SyntaxTreeRenderTest {

    @Before
    public void setup() {
        IceSparrowExcutor.getInstance().grammar("add", new Function<List<String>, String>() {
            @Nullable
            public String apply(@Nullable List<String> strings) {
                int sum = 0;
                for(String arg : strings) {
                    if(NumberUtils.isDigits(arg)) {
                        sum += NumberUtils.toInt(arg);
                    }
                }
                return String.valueOf(sum);
            }
        }).grammar("minus", new Function<List<String>, String>() {
            @Nullable
            public String apply(@Nullable List<String> strings) {
                String first = strings.get(0);
                int val = NumberUtils.toInt(first, 0);
                for(int i = 1; i < strings.size(); i++) {
                    val -= NumberUtils.toInt(strings.get(i), 0);
                }
                return String.valueOf(val);
            }
        }).grammar("*", new Function<List<String>, String>() {
            @Nullable
            public String apply(@Nullable List<String> strings) {
                int sum = 1;
                for(String arg : strings) {
                    if(NumberUtils.isDigits(arg)) {
                        sum *= NumberUtils.toInt(arg);
                    }
                }
                return String.valueOf(sum);
            }
        });

    }
    @Test
    public void test() {
        String template = "add(1, minus(3, 1), *(5, add(1, 3)))";
        SyntaxTree root = SyntaxParser.parse(template).getChildren().get(0);
        String ret = IceSparrowExcutor.getInstance().excute(root, null);
        System.out.println(ret);
    }

    @Test
    public void test3() {
        SyntaxTree tree = new SyntaxTree("*");
        tree.addChild(new SyntaxTree("1"));
        tree.addChild(new SyntaxTree("2"));
        tree.addChild(new SyntaxTree("5"));

        System.out.println(IceSparrowExcutor.getInstance().excute(tree, null));
    }

    @Test
    public void test2() {
        SyntaxTree tree = new SyntaxTree("*");
        SyntaxTree addTree = new SyntaxTree("+");
        addTree.addChild(new SyntaxTree("6"));
        addTree.addChild(new SyntaxTree("1"));
        SyntaxTree minusTree = new SyntaxTree("-");
        minusTree.addChild(new SyntaxTree("5"));
        minusTree.addChild(new SyntaxTree("3"));
        List<SyntaxTree> treeChildren = Lists.newArrayList();
        treeChildren.add(addTree);
        treeChildren.add(minusTree);
        System.out.println(IceSparrowExcutor.getInstance().excute(tree, null));
    }



}
