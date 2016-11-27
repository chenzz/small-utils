package util;

/**
 * @author: chenzhongzheng
 * @desciption:
 * @date: 2016-11-27
 */
public class StringUtils {

    //去除str中的特殊yaml字符
    public static String eliminateSpecialStr4Yaml(String input) {
        input = input.replace("\"", " ");
        input = input.replace("'", " ");
        input = input.replace("\\", " ");

        return input;
    }

    //去除str中的特殊yaml字符
    public static String eliminateSpecialStr4Filename(String input) {
        input = input.replace("\"", " ");
        input = input.replace("'", " ");
        input = input.replace("\\", " ");
        input = input.replace(":", " ");

        return input;
    }

    //提取一段文字的第一句话作为简介
    public static String extractFirstSentence(String input) {
        int indexOfPeriod = input.indexOf("。");
        int indexOfLineFeed = input.indexOf("\n");
        if (-1 == indexOfLineFeed) {
            indexOfLineFeed = 0;
        }
        if (-1 == indexOfPeriod) {
            indexOfPeriod = indexOfLineFeed;
        }
        indexOfPeriod += 1;         //带上句号
        int indexOfDescriptionEnd = (indexOfPeriod < indexOfLineFeed) ? indexOfPeriod : indexOfLineFeed;
        String description = input.substring(0, indexOfDescriptionEnd);
        //为避免出错，出现某些转移符时直接将内容置为空
        description = eliminateSpecialStr4Yaml(description);

        return description;
    }
}
