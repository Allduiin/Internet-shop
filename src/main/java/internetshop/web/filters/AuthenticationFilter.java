package internetshop.web.filters;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class AuthenticationFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
