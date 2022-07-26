package com.huige.Institution.util;

import cn.hutool.core.util.StrUtil;
import com.vdurmont.emoji.EmojiParser;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文章处理工具类
 */
public class ContentUtil {

    /**
     * 提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if (StrUtil.isNotBlank(html)) {
            // (?s)表示所在位置右侧的表达式开启单行模式
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        }
        return "";
    }

    /**
     * 替换HTML脚本
     *
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * Markdown转换为Html
     *
     * @param markdown
     * @return
     */
    public static String mdToHtml(String markdown) {
        if (StrUtil.isBlank(markdown)) {
            return "";
        }
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new CustomAttributeProvider())
                .extensions(extensions).build();
        String content = renderer.render(document);
        return content;
    }


    private static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }

    /**
     * 截取文章摘要
     *
     * @param content 文章内容
     * @param len     要截取文字的个数
     * @return
     */
    public static String extractDigest(String content, int len) {
        if (StrUtil.isBlank(content))
            return "";
        String text = htmlToText(code2emoji(mdToHtml(content)));
        if (text.length() > len) {
            return text.substring(0, len) + "... ...";
        }
        return text;
    }

    /**
     * 将表情代码转换为emoji表情
     *
     * @param code
     * @return
     */
    public static String code2emoji(String code) {
        return EmojiParser.parseToUnicode(code);
    }

    /**
     * 将emoji表情转换为表情代码
     *
     * @param value
     * @return
     */
    public static String emoji2code(String value) {
        return EmojiParser.parseToAliases(value);
    }
}
