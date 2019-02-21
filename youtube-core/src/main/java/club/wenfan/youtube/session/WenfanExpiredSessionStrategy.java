package club.wenfan.youtube.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *
 * @author wenfan
 * @date
 * @param
 * @return
 */
public class WenfanExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public WenfanExpiredSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}
	
	@Override
	protected boolean isConcurrency() {
		return true;
	}

}
