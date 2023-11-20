package com.mithra.apsf.security;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.mithra.apsf.common.Constants;
import com.mithra.apsf.common.Constants.EnumUserStatus;
import com.mithra.apsf.security.exception.AccountActivationPendingException;
import com.mithra.apsf.user.model.User;
import com.mithra.apsf.user.repository.UserRepository;


public class APSFUserDetailsServiceImpl implements APSFUserDetailsService {

	private static final Logger logger = Logger.getLogger(APSFUserDetailsServiceImpl.class);

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserFromSession() {
		logger.debug("Entering into getUserFromSession()");

		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null && authentication.getPrincipal() != null) {
				final Object principal = authentication.getPrincipal();
				if (principal instanceof APSFUserDetails) {
					final APSFUserDetails userDetails = (APSFUserDetails) principal;
					if(userDetails != null) {
						User user = userDetails.getVicinityUser();
						User loggedUser = new User();
						String ignoreProps[] = {"dob","referedUser","password","subCaste","modifiedDate","createdDate"};
						BeanUtils.copyProperties(user, loggedUser,ignoreProps);
						return userDetails != null ? loggedUser : null;
					}
					
				}
			}
		}
		logger.debug("Leaving");

		return null;
	}
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    	logger.info("Entering into loadUserByUsername()");

		User user = null;
		try {
	        String trimmedUserName = StringUtils.trimWhitespace(username);
	        if (StringUtils.hasLength(trimmedUserName)) {
	        	 user = userRepository.findByPhnoAndNameOrRegid(trimmedUserName);
	        } else {
	        	throw new UsernameNotFoundException(messageSource.getMessage("msg.security.user.notfound", null, Locale.ENGLISH) + username);
	        }
	
	        if (user == null) { // User is null or doesn't exist
	            throw new UsernameNotFoundException(messageSource.getMessage("msg.security.user.notfound", null, Locale.ENGLISH) + username);
	        } else { // User existed
	            final EnumUserStatus currentUserStatus = user.getStatus();
	            if (currentUserStatus == Constants.EnumUserStatus.PENDING) {
	                throw new AccountActivationPendingException(messageSource.getMessage("msg.security.user.pendingactivation", null, Locale.ENGLISH) + messageSource.getMessage("msg.security.user.support1", null, Locale.ENGLISH));
	            } else if (currentUserStatus == Constants.EnumUserStatus.SUSPENDED) {
	                throw new AccountExpiredException(messageSource.getMessage("msg.secuirty.user.deactivated", null, Locale.ENGLISH) + messageSource.getMessage("msg.security.user.support2", null, Locale.ENGLISH));
	            } else if (currentUserStatus == Constants.EnumUserStatus.DELETED) {
	                // The user does not exist. Throw an exception.
	                throw new AccountExpiredException(messageSource.getMessage("msg.security.user.notfound", null, Locale.ENGLISH) + username);
	            }
	        }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		logger.info("Leaving");

    	return new APSFUserDetails(user);
    }

    /**
	 *Checking Any previous Sessions having Active status for current User,Then update to Expired status     
	 * @throws Exception
	 */
    /*@Override
	public void killPerviousSessions() throws Exception {
    	logger.info("Entered into killPerviousSessions()");

		try {
			VicinityUser currentUser = getUserFromSession();

			Set<LoginDetails> oldSessions = userRepository.findSessionTrackingByUserId(currentUser.getId());

			DateTime currentTime = new DateTime();

			for (LoginDetails sessionTrack : oldSessions) {
				// sessionTrack.setStatus(EnumSessionStatus.SESSION_EXPIRED);
				sessionTrack.setHowLogout(EnumHowLogout.CLICKED_LOGOUT_BUTTON);
				if (currentTime.compareTo(sessionTrack.getLoginTime()) != 0
						&& sessionTrack.getLogoutTime() == null) {
					sessionTrack.setLogoutTime(new DateTime(sessionTrack.getLoginTime().getMillis() + 10000));
				}
				sessionTrack.setModifiedBy(currentUser.getEmail());
				sessionTrack.setLastModifiedDate(new DateTime());
				sessionTrack = userRepository.updateSessionTracking(sessionTrack);
			}
		} catch (Exception e) {
			logger.error(messageSource.getMessage("msg.security.user.sessions", null, Locale.ENGLISH), e);
			// e.printStackTrace();
		}
		logger.info("Leaving");
	}*/
}
