import fileIngester.FileThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@ExtendWith({MockitoExtension.class})
public class OptionalRegexTests { // here logs arguments are optional !




    @Test
    public void defaultCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO package.name.className - msg";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertEquals("01:22:42",result.group("time"));
        Assertions.assertNotNull(result);
    }

    @Test
    public void noArgumentCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noInfoCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noPackageCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO ";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noThreadCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noTimeCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 ,115 [ThreadName] INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noDateCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "01:22:42,115 [ThreadName] INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void noNumberCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void firstThreeCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = " [ThreadName] INFO package.name.className";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }

    @Test
    public void whiteSpaceCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "           2021-07-12       01:22:42      ,115     [ThreadName]      INFO    package.name.className                                -argument       " ;
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
}
