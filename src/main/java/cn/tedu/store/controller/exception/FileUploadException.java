package cn.tedu.store.controller.exception;

/**
 * 文件上传异常
 * @author Smile
 *
 */
public class FileUploadException extends RequestException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadException() {
		super();
	}

	public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(Throwable cause) {
		super(cause);
	}
	
	

}
