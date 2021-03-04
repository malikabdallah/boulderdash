package src;

/**
 * 
 * @author malik
 *cette classe correspond aux erreur de validite de deplacement , si le personnage cherche a se deplacer sur une case non accessible alors
 *cette exception sera declanche .
 */

public class DeplacementInvalideException extends Exception {

	public DeplacementInvalideException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeplacementInvalideException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DeplacementInvalideException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DeplacementInvalideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DeplacementInvalideException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
