/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.support.jconsole.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TableDataProcessor {
	private static final String COLUMN_KEY_NAME = "key";
	private static final String COLUMN_VALUE_NAME = "value";
	private TableDataProcessor() {
		
	}
	
	public static ColumnData row2col(
			ArrayList<LinkedHashMap<String, Object>> rowDatas, String keyword) {
		ColumnData datas = new ColumnData();
		ArrayList<LinkedHashMap<String, Object>> coldatas
			= new ArrayList<LinkedHashMap<String, Object>>();
		ArrayList<String> colNames = new ArrayList<String>();
		int rowCount = 0;
		int colCount = 0;
		for(LinkedHashMap<String, Object> row: rowDatas) {
			String keyNow = row.remove(keyword).toString();
			colNames.add(keyNow);
			rowCount ++;
			
			for(Map.Entry <String,   Object>  element   :   row.entrySet()) {
				LinkedHashMap<String, Object> colData
				= new LinkedHashMap<String, Object>();
				colData.put(COLUMN_KEY_NAME, element.getKey());
				colData.put(COLUMN_VALUE_NAME, element.getValue());
				coldatas.add(colData);
				if (rowCount == 1) {
					colCount++;
				}
			}			
		}
		datas.setCount(colCount);
		datas.setDatas(coldatas);
		datas.setNames(colNames);
		return datas;
	}
	
	public static class ColumnData {
		private ArrayList<String> names;
		private ArrayList<LinkedHashMap<String, Object>> datas;
		private int count;
		
		public ColumnData() {
			super();
		}
		
		public ArrayList<String> getNames() {
			return names;
		}
		public void setNames(ArrayList<String> names) {
			this.names = names;
		}
		public ArrayList<LinkedHashMap<String, Object>> getDatas() {
			return datas;
		}
		public void setDatas(ArrayList<LinkedHashMap<String, Object>> datas) {
			this.datas = datas;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}		
	}
}
