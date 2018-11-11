/*
 * 广州丰石科技公司有限公司拥有本软件版权2017并保留所有权利。
 *  Copyright 2017, Guangzhou Rich Stone Data Technologies Company Limited,
 * All rights reserved.
 *
 */

package com.richstonedt.road.query.engine.cs.common;

import com.richstonedt.road.query.engine.model.common.RoadQueryEngineException;
import com.richstonedt.road.query.engine.model.excel.ExcelFile;
import com.richstonedt.road.query.engine.model.excel.ExcelSheet;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <b><code>CityRoadsInfoReader</code></b>
 * <p>
 * class_comment
 * </p>
 * <b>Create Time:</b> 2017/1/23 17:02
 *
 * @author Hetian Zhu
 * @version 0.1.0
 * @since road-query-engine-cs 0.1.0
 */
public class CityRoadsInfoReader {

    private final static Logger LOG = LoggerFactory.getLogger(CityRoadsInfoReader.class);

    /**
     * getRoadExcelInfo
     *
     * @return java.util.List<com.richstonedt.road.query.engine.model.excel.ExcelFile>
     * @see List<ExcelFile>
     * @since road-query-engine-cs 0.1.0
     */
    public static List<ExcelFile> getRoadExcelInfo(){

        /* Read city roads info from cityRoadInfo.json */
        String jsonString = readExcelFileInfoFromJson();

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray files = jsonObject.getJSONArray("files");

        List<ExcelFile> result = new ArrayList<>();
        /* Generate ExcelFile from JSON Object */
        for (int i = 0; i < files.size(); i++) {
            JSONObject object = files.getJSONObject(i);
            ExcelFile excel = new ExcelFile();
            excel.setFilePath(object.getString("filePath"));
            excel.setCityCode(object.getString("cityCode"));
            List<ExcelSheet> sheets = new ArrayList<>();
            JSONArray jsonSheets = object.getJSONArray("sheetNameList");
            for (int j = 0; j < jsonSheets.size(); j++) {
                JSONObject jsonSheet = jsonSheets.getJSONObject(j);
                ExcelSheet sheet = new ExcelSheet();
                sheet.setName(jsonSheet.getString("name"));
                sheet.setStartRow(jsonSheet.getInt("startRow"));
                sheet.setEndRow(jsonSheet.getInt("endRow"));
                sheet.setStartCol(jsonSheet.getInt("startCol"));
                sheet.setEndCol(jsonSheet.getInt("endCol"));
                sheets.add(sheet);
            }
            excel.setSheets(sheets);
            result.add(excel);
        }
        return result;
    }


    /**
     * readExcelFileInfoFromJson
     *
     * @return java.lang.String
     * @see String
     * @since road-query-engine-cs 0.1.0
     */
    private static String readExcelFileInfoFromJson(){
        InputStream is = CityRoadsInfoReader.class.getResourceAsStream("/cityRoadInfo.json");
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = null;
        try{
            scanner = new Scanner(is);
            while (scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine());
            }
            return stringBuilder.toString();
        }catch(Exception e){
            LOG.error("Fail to read cityRoadInfo.json");
            throw new RoadQueryEngineException(e);
        }finally {
            if (scanner!=null){
                scanner.close();
            }
        }
    }
}
