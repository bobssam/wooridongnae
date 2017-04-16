package com.suba.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suba.admin.dao.AdminMetaDao;
import com.suba.vo.MetaVO;

@Service
public class ExcelTemplate {

	Pattern pattern = Pattern.compile("(:?[A-Z]{1,2}[0-9]{1,4})");
	
	@Autowired
	private AdminMetaDao metaDao;

	public <E> void makeExcelFile( String tableName, List<E> list, OutputStream outputStream) {
		
		List<MetaVO> meta = metaDao.selectList(tableName);
		HashMap<String, String> metaMap = new HashMap<String, String>();
		for( MetaVO m : meta ) {
			metaMap.put( m.getField() , m.getComment());
		}

		XSSFCell cell = null;
		XSSFCell titleCell = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet newSheet = (XSSFSheet) workbook.createSheet("sheet1");
			XSSFRow titleRow = newSheet.createRow(0);
			for (int i = 0, len = list.size(); i < len; i++) {
				LinkedHashMap<String, Object> maps = (LinkedHashMap<String, Object>) list.get(i);
				XSSFRow newRow = newSheet.createRow(i+1);

				int j = 0;
				Iterator iteratorKey = maps.keySet().iterator();
				while (iteratorKey.hasNext()) {
					String key = (String) iteratorKey.next();
					if(i==0) {
						titleCell = titleRow.createCell(j);
						titleCell.setCellValue(metaMap.get(key));
					}		
					cell = newRow.createCell(j);
					cell.setCellValue("" + maps.get(key));
					j++;
				}

			} // for(c) 문
			try {
				workbook.write(outputStream);
				outputStream.close();
				workbook.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> findExcelCellName(String data) {

		// 내용 중에서 이미지 태그를 찾아라!
		Matcher match = pattern.matcher(data);
		ArrayList<String> matchs = new ArrayList<String>();

		while (match.find()) { // 이미지 태그를 찾았다면,,
			matchs.add(match.group(0));
		}
		return matchs;
	}

	private String moveFomula(String value, int startLoopR, int newR, int r) {

		String target = new String(value);
		List<String> matchs = findExcelCellName(value);
		boolean hasRange = value.indexOf(':') > 0;

		for (String w : matchs)
			target = target.replaceFirst(w, "<match>");
		// 정규식 분석
		for (String cellName : matchs) {

			int addRow = newR - r;
			int rowNum = 0;
			int startNum = 0;
			Pattern pattern = Pattern.compile("[0-9]");

			// 시작점을 어케 알지?
			if (addRow > 0) {
				Matcher match = pattern.matcher(cellName);
				if (match.find()) {
					String w = match.group();
					rowNum = Integer.parseInt(w);
					rowNum--;// java는 0부터 따지고, 엑셀은 1부터 따지니 계산하기 편하게 -1 시킴
					startNum = match.start();
				}

				// 엔드 범위
				if (cellName.charAt(0) == ':') {

					// 시작점이 루프 보다 아래면
					if (rowNum >= startLoopR)
						rowNum += addRow - 1;

				} else {

					// 시작점이 루프 보다 아래면
					if (hasRange) {
						if (rowNum > startLoopR)
							rowNum += addRow - 1;
					} else {
						if (rowNum >= startLoopR)
							rowNum += addRow - 1;
					}

				}
				rowNum++;
				target = target.replaceFirst("<match>", cellName.substring(0, startNum) + rowNum);
			}

		}
		return target;
	}

}
