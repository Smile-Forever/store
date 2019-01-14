package cn.tedu.store.service.exception;


/**
 * 
 * @author Smile
 *
 */
public class AddressDeniedException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressDeniedException() {
		super();
	}

	public AddressDeniedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressDeniedException(String message) {
		super(message);
	}

	public AddressDeniedException(Throwable cause) {
		super(cause);
	}
	

}
