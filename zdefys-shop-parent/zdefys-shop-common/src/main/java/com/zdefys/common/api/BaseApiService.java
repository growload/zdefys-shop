package com.zdefys.common.api;

import java.util.HashMap;
import java.util.Map;

import com.zdefys.constants.BaseApiConstants;

/**
 * @classDesc: 通用Baseapi 父类
 * @author:xinyin
 *
 */
public class BaseApiService {
	
	
	/**
	 * @methodDesc: 功能描述（返回成功）
	 * 
	 * @param msg
	 * @return
	 */
	public Map<String, Object> setResultSuccess() {
		return setResult(BaseApiConstants.HTTP_RES_CODE_200, BaseApiConstants.HTTP_RES_CODE_200_VALUE, null);
	}
	
	public Map<String, Object> setResultSuccessData(Object data) {
		return setResult(BaseApiConstants.HTTP_RES_CODE_200, BaseApiConstants.HTTP_RES_CODE_200_VALUE, data);
	}

	/**
	 * @methodDesc: 功能描述（返回失败）
	 * 
	 * @param msg
	 * @return
	 */
	public Map<String, Object> setResultError() {
		
		return setResult(BaseApiConstants.HTTP_RES_CODE_500, BaseApiConstants.HTTP_RES_CODE_500_VALUE, null);
	}
	
public Map<String, Object> setResultError(String msg) {
		
		return setResult(BaseApiConstants.HTTP_RES_CODE_500, BaseApiConstants.HTTP_RES_CODE_500_VALUE, msg);
	}
	
public Map<String, Object> setResultParameterError(String msg) {
		
		return setResult(BaseApiConstants.HTTP_RES_CODE_400, BaseApiConstants.HTTP_RES_CODE_500_VALUE, msg);
	}

	/**
	 * 
	 * @methodDesc: 功能描述（自定义返回）
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public Map<String, Object> setResult(Integer code, String msg, Object data) {
		Map<String, Object> result = new HashMap<>(1);
		result.put(BaseApiConstants.HTTP_RES_CODE_NAME, code);
		result.put(BaseApiConstants.HTTP_RES_CODE_MSG, msg);
		if (data != null) {
			result.put(BaseApiConstants.HTTP_RES_CODE_DATA, data);
		}
		return result;
	}

}
