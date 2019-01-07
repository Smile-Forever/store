package cn.tedu.store.controller.exception;

/**
 * 上传文件大小超出限制
 * @author Smile
 *
 */
public class FileSizeOutOfLimitException extends FileUploadException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileSizeOutOfLimitException() {
		super();
	}

	public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeOutOfLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeOutOfLimitException(String message) {
		super(message);
	}

	public FileSizeOutOfLimitException(Throwable cause) {
		super(cause);
	}
	

}
