package cn.icesparrow.icesparrow.syntax;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: xiaobin.wu
 * @Date: 4/10/19 11:38 PM
 */
public class SyntaxTree {

    private String symbol;

    public SyntaxTree() {
        this("");
    }

    public SyntaxTree(String symbol) {
        this.symbol = symbol;
        this.children = Lists.newArrayList();
    }

    private List<SyntaxTree> children;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void addChild(SyntaxTree child) {
        this.children.add(child);
    }

    public List<SyntaxTree> getChildren() {
        return children;
    }

    public Boolean hasChildren() {
        return children != null && children.size() > 0;
    }

}
