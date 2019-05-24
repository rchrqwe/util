package com.rc.util.util;

import java.util.*;

public class CheckJsonParams extends BasicUtilConfig {

	/**
	 * json参数检查器
	 * @author rc
	 * @param params
	 *            检查的json参数
	 * @param paramsCheackString
	 *            按，分割的参数字符串
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map checkJsonParams(Map params, String paramsCheackString) {
		HashMap<String, Object> param = (HashMap<String, Object>) params;
		Map result = new HashMap<>();
		String[] paramStringList = paramsCheackString.split(",");
		List<String> list = new LinkedList<>();
		// 没办法，移除的话稍微慢一点，但是数量不大，速度也不会受到太大影响
		Collections.addAll(list, paramStringList);
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String x = it.next();
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				if (x.equals(entry.getKey())) {
					it.remove();
				}
			}
		}

		if (list.size() != 0) {
			String notFoundParam = "";
			for (int i = 0; i < list.size(); i++) {
				if(i != list.size()){
					notFoundParam = notFoundParam +","+ list.get(i);
				}
			}
			result.put("notFoundParam", "参数不存在" + notFoundParam);
			result.put(STATUS, ERROR_CODE);
		} else {
			result.put(STATUS, SUCCESS_CODE);
		}
		return result;
	}
}
