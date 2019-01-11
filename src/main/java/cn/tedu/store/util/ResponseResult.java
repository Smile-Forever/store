package cn.tedu.store.util;

import java.io.Serializable;

/**
 * 服务器端向客户器端相应的类型
 * 
 * @author Smile
 *
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer state;
	private String Message;
	private T data;

	public ResponseResult() {
		super();
	}

	/**
	 * 表示操作成功时
	 * 
	 * @param state
	 */
	public ResponseResult(int state) {
		super();
		setState(state);

	}

	/**
	 * 表示操作失败时
	 * 
	 * @param state
	 * @param message
	 */
	public ResponseResult(int state, String message) {
		this(state);
		setMessage(message);
	}

	/**
	 * 表示操作失败时
	 * 
	 * @param state
	 * @param e
	 */
	public ResponseResult(int state, Exception e) {
		this(state, e.getMessage());
	}

	/**
	 * 表示用户情求数据成功时
	 * 
	 * @param state
	 * @param data
	 */
	public ResponseResult(int state, T data) {
		this(state);
		setData(data);
	}
	

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseResult [state=" + state + ", Message=" + Message + ", data=" + data + "]";
	}

}
