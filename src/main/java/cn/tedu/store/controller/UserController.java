package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.exception.FileEmptyException;
import cn.tedu.store.controller.exception.FileSizeOutOfLimitException;
import cn.tedu.store.controller.exception.FileTypeNotSupportExceptiom;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	private static final String UPLOAD_DIR_NAME = "upload";
	/**
	 * 允许上传的文件大小
	 */
	private static final long FILE_MAX_SIZE = 5*1024*4024;
	/**
	 * 允许上传的文件类型
	 */
	private static final List<String> FILE_CONTENT_TYPE = new ArrayList<String>();
	/**
	 * 初始化允许上传的文件类型的集合
	 */
	static {
		FILE_CONTENT_TYPE.add("image/jpeg");
		FILE_CONTENT_TYPE.add("image/png");
	}
	@Autowired
	private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(@RequestParam("username") String username ,
			@RequestParam("password") String password , HttpSession session){
		User user = userService.login(username, password);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>(SUCCESS , user);
	}
	
	@PostMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password")String oldPassword,
						@RequestParam("new_password")String newPassword , HttpSession session){
		//获取当前登录用户的id
		Integer uid = getUidFromSession(session);
		//执行修改 密码
		userService.changePassword(uid , oldPassword, newPassword);
		//返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/info.do")
	public ResponseResult<User>  getInfo(HttpSession session){
		Integer id = getUidFromSession(session);
		User user = userService.getById(id);
		return new ResponseResult<User>(SUCCESS , user);
	}
	
	@PostMapping("/change_info.do")
	public ResponseResult<Void>  changeInfo(User user , HttpSession session){
		Integer id = getUidFromSession(session);
		user.setId(id);
		userService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(
		HttpSession session,
		@RequestParam("file") MultipartFile file) throws FileUploadException {
		// TODO 检查是否存在上传文件 > file.isEmpty()
		if(file.isEmpty()) {
			//抛出异常
			throw new FileEmptyException("没有选择上传的文件,或选择的文件为空!");
		}
		// TODO 检查文件大小 > file.getSize()
		if(file.getSize() > FILE_MAX_SIZE) {
			//抛出异常 ，文件大小超出限制
			throw new FileSizeOutOfLimitException("选择上传的文件超出限制!");
		}
		// TODO 检查文件类型 > file.getContentType()
		if(!FILE_CONTENT_TYPE.contains(file.getContentType())) {
			//抛出异常 ，文件类型限制
			throw new FileTypeNotSupportExceptiom("文件格式不支持!");
		}

		// 确定上传文件夹 > session.getServletContext.getRealPath(UPLOAD_DIR_NAME) > exists() > mkdirs()
		String parentPath = session
			.getServletContext().getRealPath(UPLOAD_DIR_NAME);
		File parent = new File(parentPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}

		// 确定文件名 > getOriginalFileName()
		String originalFileName = file.getOriginalFilename();
		int beginIndex = originalFileName.lastIndexOf(".");
		String suffix = originalFileName.substring(beginIndex);
		String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000) + 10000000) + suffix;
		
		// 确定文件
		File dest = new File(parent, fileName);

		// 执行保存文件
		try {
			file.transferTo(dest);
			System.err.println("上传完成！");
		} catch (IllegalStateException e) {
			//抛出异常:上传失败
			throw new FileUploadException("上传失败!");
		} catch (IOException e) {
			//抛出异常:上传失败
			throw new FileUploadException("上传失败!");
		}

		// 获取当前用户的id
		Integer uid = getUidFromSession(session);
		// 更新头像数据
		String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
		userService.changeAvatar(uid, avatar);

		// 返回
		ResponseResult<String> rr
			= new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
	}
}
