package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @auther cen
 * @date 2019/1/16 16:22
 */
class Testpoi {

    public static void main(String[] args) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = "F:\\test.xlsx";
        String columns[] = {"name","age","score"};
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
      /*  DopEamsReZHServiceImpl dopEamsReZHServiceImpl=new DopEamsReZHServiceImpl();
//        DopEamsReZHServiceImpl dopEamsReZHServiceImpl = (DopEamsReZHServiceImpl) WebApplicationContextUtil.getBean("dopEamsReZHServiceImpl");
        //遍历解析出来的list
        StringBuilder stringBuilder=new StringBuilder();

        for (Map<String,String> map : list) {
//            for (Entry<String,String> entry : map.entrySet()) {
//                System.out.print(entry.getKey()+":"+entry.getValue()+",");
//            }
//            dopEamsReZHServiceImpl.updateTgr(map);
            stringBuilder.append("UPDATE DOP_EAMS_RE_UNION SET tgr_no = (SELECT tgr_no FROM DOP_EAMS_RE_TGMAN WHERE tgr_name LIKE '%" + map.get("tgr_name")+ "%') WHERE union_name = '"+map.get("union_name")+"';\n");
        }
        try
        {
            // 创建文件对象
            File fileText = new File("D:\\tgr.sql");
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            fileWriter.write(stringBuilder.toString());
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }*/
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            for (Entry<String,String> entry : map.entrySet()) {
                System.out.print(entry.getKey()+":"+entry.getValue()+",");
            }
            System.out.println();
        }

    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

}