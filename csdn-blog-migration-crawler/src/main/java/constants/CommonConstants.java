package constants;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-27
 */
public class CommonConstants {
    public static final String INDEX = "http://blog.csdn.net/{0}";
    public static final String ARTICLE_LIST = INDEX + "/article/list/{1}";

    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";

    public static final String SAVE_PATH = "/Users/chenzhongzheng/Desktop/blog/";


    public static final String POST_INFO = "---\n" +
            "title: \"{0}\"\n" +
            "list_number: false\n" +
            "date: {1}\n" +
            "categories: {2}\n" +
            "tags: {3}\n" +
            "description: \"{4}\"\n" +
            "---\n";
    public static final String POST_ORIGIN_URL = "\n\n\n本文章迁移自[{0}]({0})";
}
