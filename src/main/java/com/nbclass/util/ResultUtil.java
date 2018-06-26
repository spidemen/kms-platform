/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.nbclass.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class ResultUtil{

	public static Map<String,Object> success(){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.SUCCESS);
		return map;
	}

	public static Map<String,Object> success(String msg){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.SUCCESS);
		map.put("msg",msg);
		return map;
	}

	public static Map<String,Object> success(String status,String msg){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.SUCCESS);
		map.put("msg",msg);
		return map;
	}

	public static Map<String,Object> error(){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.FAIL);
		map.put("msg","系统异常，请联系管理员");
		return map;
	}

	public static Map<String,Object> error(String msg){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.FAIL);
		map.put("msg",msg);
		return map;
	}

	public static Map<String,Object> error(String status,String msg){
		Map<String,Object> map=new HashMap<>();
		map.put("status",CoreConst.FAIL);
		map.put("msg",msg);
		return map;
	}

	public static Map<String,Object> table(Object data, long total){
		Map<String,Object> map=new HashMap<>();
		map.put("rows", data);
		map.put("total", total);
		return map;
	}



}
