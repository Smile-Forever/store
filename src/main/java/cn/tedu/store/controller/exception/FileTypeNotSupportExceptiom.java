package cn.tedu.store.controller.exception;

/**
 * 文件类型不支持 
 * @author Smile
 *
 */
public class FileTypeNotSupportExceptiom extends FileUploadException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTypeNotSupportExceptiom() {
		super();
	}

	public FileTypeNotSupportExceptiom(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileTypeNotSupportExceptiom(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTypeNotSupportExceptiom(String message) {
		super(message);
	}

	public FileTypeNotSupportExceptiom(Throwable cause) {
		super(cause);
	}
	
	
}
