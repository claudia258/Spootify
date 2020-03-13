package it.spootify.Spootify.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import it.spootify.Spootify.model.Sessione;
import it.spootify.Spootify.model.Utente;
import it.spootify.Spootify.service.SessioneService;
import it.spootify.Spootify.service.UtenteService;
import it.spootify.Spootify.utility.DataUtils;


@WebFilter(filterName = "FiltroAdmin", urlPatterns = { "/*" })
@Component
@Order(1)
public class FiltroAdmin implements Filter{
	
	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = {"/login"};
	private static final String[] PROTECTED_URLS = {"/admin/"};

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private SessioneService sessioneService;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String codice = httpRequest.getHeader("codiceSessione");
		//String codice = http.getHeader("codiceSessione");
		System.out.println(codice);
		String urlAttuale = httpRequest.getServletPath();
		System.out.println(urlAttuale);
		
		boolean isInWhiteList = isPathInWhiteList(urlAttuale);
		
		Utente utenteInSession = null;
		
		if (!isInWhiteList) {
			
			utenteInSession = utenteService.caricaUtenteInSessione(codice);

			if (utenteInSession == null) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accessono non Autorizzato"); 
			}
			System.out.println(utenteInSession.getSessione().getDataScandeza());
			if(utenteInSession.getSessione().getDataScandeza().before(new Date())) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accessono non Autorizzato"); 
			}
			if(isPathForOnlyAdministrators(urlAttuale) && !utenteInSession.isAdmin()) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Accessono non Autorizzato"); 
			}
		}
		if(utenteInSession != null) {
			Sessione sessione = sessioneService.findByCodiceSessione(codice);
			sessione.setDataScandeza(DataUtils.addMinutesToDate(5, utenteInSession.getSessione().getDataScandeza()));
			sessioneService.aggiorna(sessione);
		}
			chain.doFilter(request, response);
		}
	
	
	private boolean isPathInWhiteList(String requestPath) {
		
		if(requestPath.equals(HOME_PATH))
			return true;
		
		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}
	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile da admin");
				return true;
			}
		}
		return false;
	}

	public void destroy() {
	}
}
