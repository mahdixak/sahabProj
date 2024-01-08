import fileIngester.FileThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermutationRegexTests {


    @Test
    public void defaultCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO package.name.className -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
//        Assertions.assertEquals("2021-07-12",result.group("date"));
        Assertions.assertNotNull(result);
    }

    @Test
    public void case1() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42 2021-07-12 ,115 [ThreadName] INFO package.name.className -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void case2() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42 2021-07-12 ,115  INFO package.name.className [ThreadName] -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case3() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42 2021-07-12 ,115 [ThreadName]  -argument INFO package.name.className" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case4() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "INFO 01:22:42 2021-07-12 ,115 [ThreadName] package.name.className -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case5() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42  ,115 [ThreadName] INFO package.name.className 2021-07-12 -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case6() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = ",115 01:22:42 2021-07-12  [ThreadName] INFO package.name.className -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case7() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = " 2021-07-12 ,115 [ThreadName] INFO package.name.className 01:22:42 -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case8() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42 2021-07-12 package.name.className ,115 [ThreadName] INFO -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case9() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42 [ThreadName] INFO package.name.className -argument 2021-07-12 ,115 " ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case10() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = ",115 [ThreadName] INFO package.name.className -argument 01:22:42 2021-07-12 " ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case11() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = ",115 [ThreadName] INFO 01:22:42 2021-07-12 package.name.className -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
    @Test
    public void case12() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = " [ThreadName] 2021-07-12 INFO ,115 package.name.className 01:22:42  -argument" ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

}
