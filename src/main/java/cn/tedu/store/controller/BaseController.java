package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.controller.exception.FileEmptyException;
import cn.tedu.store.controller.exception.FileSizeOutOfLimitException;
import cn.tedu.store.controller.exception.FileTypeNotSupportExceptiom;
import cn.tedu.store.controller.exception.FileUploadException;
import cn.tedu.store.controller.exception.RequestException;
import cn.tedu.store.service.exception.AccessDeniedException;
import cn.tedu.store.service.exception.AddressNotFoundException;
import cn.tedu.store.service.exception.DeleteException;
import cn.tedu.store.service.exception.DuplicateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.service.exception.UpdateException;
import cn.tedu.store.service.exception.UserNotFoundException;
import cn.tedu.store.util.ResponseResult;

/**
 * 当前项目中所有控制器类的基类
 * @author Smile
 *
 */
public abstract class BaseController {
	public static final Integer SUCCESS = 200;
	@ExceptionHandler({ServiceException.class , RequestException.class})
	@ResponseBody
	public ResponseResult<Void> HandleException(Exception e){
		if(e instanceof DuplicateKeyException) {
			//400 - DuplicateKeyException 违反了Unique约束的异常
			return new ResponseResult<>(400 , e.getMessage());
		}else if(e instanceof UserNotFoundException) {
			//401 - 用户数据不存在
			return new ResponseResult<>(401 , e.getMessage());
		}else if(e instanceof PasswordNotMatchException) {
			//402 - 密码错误
			return new ResponseResult<>(402 , e.getMessage());
		}else if(e instanceof AccessDeniedException) {
			//403 - 访问的地址不存在异常
			return new ResponseResult<>(403 , e.getMessage());
		}else if(e instanceof AddressNotFoundException) {
			//404 - 
			return new ResponseResult<>(404 , e.getMessage());
		
		
		
		}else if(e instanceof InsertException) {
			//500 - InsertException  插入异常
			return new ResponseResult<>(500 , e.getMessage());
		}else if(e instanceof UpdateException) {
			//501 - 更新数据异常
			return new ResponseResult<>(501 , e.getMessage());
		}else if(e instanceof DeleteException){
			 return new ResponseResult<>(502 , e.getMessage());
		}
		
		
		
		else if(e instanceof FileEmptyException) {
			//600  - 请求异常
			return new ResponseResult<>(600 , e.getMessage());
		}else if(e instanceof FileUploadException) {
			//601 - 
			return new ResponseResult<>(601 , e.getMessage());
		}else if(e instanceof FileSizeOutOfLimitException) {
			return new ResponseResult<>(602 , e.getMessage());
		}else if(e instanceof FileTypeNotSupportExceptiom) {
			return new ResponseResult<>(610 , e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @param session
	 * @return 当前用户的id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
}
