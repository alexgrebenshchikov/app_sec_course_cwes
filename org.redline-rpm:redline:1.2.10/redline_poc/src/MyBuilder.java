import org.redline_rpm.Builder;

import java.util.List;

public class MyBuilder extends Builder {
    public List<String> getScripts() {
        return triggerscripts;
    }
}
