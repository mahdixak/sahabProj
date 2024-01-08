import fileIngester.FileThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@ExtendWith({MockitoExtension.class})
public class RegexTests {




    @Test
    public void defaultCase() {
//        FileThread fileThread = Mockito.mock(FileThread.class);
        FileThread fileThread = new FileThread(new File(""));
        Pattern pattern = Pattern.compile(fileThread.defaultRegex,Pattern.CASE_INSENSITIVE);
        String input = "2021-07-12 01:22:42,115 [ThreadName] INFO package.name.className - msg";
        Matcher result = fileThread.isRegexMatched(pattern,input);
        Assertions.assertNotNull(result);
    }
}
