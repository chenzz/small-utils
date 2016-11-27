package crawler;

import com.overzealous.remark.Remark;
import constants.CommonConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-13
 */
public class Crawler {
    public static void main(String[] args) throws IOException {

        String username = "timberwolf_2012";
        String blogUrl = MessageFormat.format(CommonConstants.INDEX, username);
        Document doc = null;
        Remark remark = new Remark();
        Elements elements = null;

        //获取到所有的分页的链接
        doc = Jsoup
                .connect(blogUrl)
                .userAgent(CommonConstants.USER_AGENT)
                .get();
        String lastPageHref = doc.select("a:containsOwn(尾页)").attr("href");
        String lastPageString = lastPageHref.substring(lastPageHref.lastIndexOf("/") + 1);
        int lastPageNumber = Integer.parseInt(lastPageString);

        List<String> pageList = new ArrayList<String>();
        for (int i = 0; i < lastPageNumber; i++) {
            pageList.add(MessageFormat.format(CommonConstants.ARTICLE_LIST, username, i+1));
        }
        System.out.println(pageList);

        //获取所有文章的链接
        List<String> articleList = new ArrayList<String>();
        for (String s : pageList) {
            doc = Jsoup
                    .connect(s)
                    .userAgent(CommonConstants.USER_AGENT)
                    .get();

            Elements articleListOnThisPage = doc.select("span.link_title a");
            for (Element articleElement : articleListOnThisPage) {
                articleList.add(articleElement.attr("abs:href"));
            }
        }

        System.out.println(articleList);
        System.out.println("共获取到" + articleList.size() + "篇文章的链接");

        //分别爬取每一篇文章
        for (int i = 0; i < articleList.size(); i++) {
            String articleUrl = articleList.get(i);
            doc = Jsoup
                    .connect(articleUrl)
                    .userAgent(CommonConstants.USER_AGENT)
                    .get();

            //是否是转载
            elements = doc.select("span.ico_type_Repost");
            boolean repost = (0 != elements.size()) ? true : false;

            //获取文章标题
            elements = doc.select("span.link_title a");
            String title = elements.text();
            if (repost) {
                title = "『转』" + title;
            }

            //获取文章分类
            elements = doc.select("div.category_r span");
            String category = elements.text();
            int categoryNumLeftBracketIndex = category.indexOf("（");
            if (-1 != categoryNumLeftBracketIndex) {
                category = category.substring(0, categoryNumLeftBracketIndex);
            }

            //获取文章tag
            List<String> tagList = new ArrayList<>();
            elements = doc.select("span.link_categories a");
            for (Element element : elements) {
                tagList.add(element.text());
            }

            //获取文章发表时间
            elements = doc.select("span.link_postdate");
            String postDate = elements.text();

            //获取文章正文内容
            //分为markdown渲染的文章 和 非markdown渲染的文章
            elements = doc.select("div.markdown_views");
            if (0 == elements.size()) {
                elements = doc.select("div.article_content");
            }

            String articleContentHtml = elements.html();
            String articleContentMarkDown = remark.convert(articleContentHtml);

            //获取文章的第一句话作为文章描述
            String description = StringUtils.extractFirstSentence(articleContentMarkDown);

            //在文章头添加文章相关信息
            String postInfo = MessageFormat.format(CommonConstants.POST_INFO,
                    StringUtils.eliminateSpecialStr4Yaml(title),
                    postDate, category, tagList, description);
            articleContentMarkDown = postInfo + articleContentMarkDown;

            //在文章尾部添加原文章链接
            articleContentMarkDown = articleContentMarkDown + MessageFormat.format(CommonConstants.POST_ORIGIN_URL, articleUrl);

            //把文章存储到本地
            String filepath = CommonConstants.SAVE_PATH + StringUtils.eliminateSpecialStr4Filename(title) + ".md";
            File file = new File(filepath);
            try (
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    ) {
                bufferedWriter.write(articleContentMarkDown);
            }

            System.out.println(i + ", " + title + ", " + repost + ", " + category + ", " + tagList + ", " + postDate);
        }
    }
}
