package com.wang.hr.server.utils;


import com.wang.hr.server.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.bcel.classfile.ElementValue.STRING;

/**
 * 文件下载工具类
 */
public class POIUtils {
    public static ResponseEntity<byte[]> employee2Excel(List<Employee> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("员工信息");
        //文档管理员
        docInfo.setManager("王增专");
        //设置公司信息
        docInfo.setCompany("");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("员工信息表");
        //文档作者
        summInfo.setAuthor("王增专");
        // 文档备注
        summInfo.setComments("本文档由 激光先进加工系统与前沿应用团队 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern((short)1);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 16 * 256);
        sheet.setColumnWidth(13, 14 * 256);
        sheet.setColumnWidth(14, 14 * 256);
        sheet.setColumnWidth(15, 12 * 256);
        sheet.setColumnWidth(16, 8 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 15 * 256);
        sheet.setColumnWidth(21, 8 * 256);
        sheet.setColumnWidth(22, 25 * 256);
        sheet.setColumnWidth(23, 14 * 256);
        sheet.setColumnWidth(24, 15 * 256);
        sheet.setColumnWidth(25, 15 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);

        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");

        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("工号");

        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("性别");

        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("出生日期");

        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("身份证号码");

        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("婚姻状况");

        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("民族");

        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("籍贯");

        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("政治面貌");

        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("电话号码");

        HSSFCell c11 = r0.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("联系地址");

        HSSFCell c12 = r0.createCell(12);
        c12.setCellStyle(headerStyle);
        c12.setCellValue("所属部门");

        HSSFCell c13 = r0.createCell(13);
        c13.setCellStyle(headerStyle);
        c13.setCellValue("职称");

        HSSFCell c14 = r0.createCell(14);
        c14.setCellStyle(headerStyle);
        c14.setCellValue("职位");

        HSSFCell c15 = r0.createCell(15);
        c15.setCellStyle(headerStyle);
        c15.setCellValue("入学/聘用形式");

        HSSFCell c16 = r0.createCell(16);
        c16.setCellStyle(headerStyle);
        c16.setCellValue("最高学历");

        HSSFCell c17 = r0.createCell(17);
        c17.setCellStyle(headerStyle);
        c17.setCellValue("专业");

        HSSFCell c18 = r0.createCell(18);
        c18.setCellStyle(headerStyle);
        c18.setCellValue("毕业院校");

        HSSFCell c19 = r0.createCell(19);
        c19.setCellStyle(headerStyle);
        c19.setCellValue("入学时间");

        HSSFCell c20 = r0.createCell(20);
        c20.setCellStyle(headerStyle);
        c20.setCellValue("预计毕业时间");

        HSSFCell c21 = r0.createCell(21);
        c21.setCellStyle(headerStyle);
        c21.setCellValue("在职状态");

        HSSFCell c22 = r0.createCell(22);
        c22.setCellStyle(headerStyle);
        c22.setCellValue("邮箱");

        HSSFCell c23 = r0.createCell(23);
        c23.setCellStyle(headerStyle);
        c23.setCellValue("学制/合同期限(年)");

        HSSFCell c24 = r0.createCell(24);
        c24.setCellStyle(headerStyle);
        c24.setCellValue("合同起始日期");

        HSSFCell c25 = r0.createCell(25);
        c25.setCellStyle(headerStyle);
        c25.setCellValue("合同终止日期");

        for (int i = 0; i < list.size(); i++) {
            Employee emp = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
            HSSFCell cell4 = row.createCell(4);
            if(emp.getBirthday() == null){
                cell4.setCellValue("<null>");
            }else{
                cell4.setCellStyle(dateCellStyle);
                cell4.setCellValue(emp.getBirthday());
            }
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNation().getName());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticsstatus().getName());
            row.createCell(10).setCellValue(emp.getPhone());
            row.createCell(11).setCellValue(emp.getAddress());
            row.createCell(12).setCellValue(emp.getDepartment().getName());
            row.createCell(13).setCellValue(emp.getJobLevel().getName());
            row.createCell(14).setCellValue(emp.getPosition().getName());
            row.createCell(15).setCellValue(emp.getEngageForm());
            row.createCell(16).setCellValue(emp.getTiptopDegree());
            row.createCell(17).setCellValue(emp.getSpecialty());
            row.createCell(18).setCellValue(emp.getSchool());

            HSSFCell cell19 = row.createCell(19);
            if(emp.getBeginDate() == null){
                cell19.setCellValue("<null>");
            }else{
                cell19.setCellStyle(dateCellStyle);
                cell19.setCellValue(emp.getBeginDate());
            }
            HSSFCell cell20 = row.createCell(20);
            if(emp.getConversionTime() == null){
                cell20.setCellValue("<null>");
            }else{
                cell20.setCellStyle(dateCellStyle);
                cell20.setCellValue(emp.getConversionTime());
            }
            row.createCell(21).setCellValue(emp.getWorkState());
            row.createCell(22).setCellValue(emp.getEmail());
            row.createCell(23).setCellValue(emp.getContractTerm());
            HSSFCell cell24 = row.createCell(24);
            if(emp.getBeginContract() == null){
                cell24.setCellValue("<null>");
            }else{
                cell24.setCellStyle(dateCellStyle);
                cell24.setCellValue(emp.getBeginContract());
            }
            HSSFCell cell25 = row.createCell(25);
            if(emp.getEndContract() == null){
                cell25.setCellValue("<null>");
            }else{
                cell25.setCellStyle(dateCellStyle);
                cell25.setCellValue(emp.getEndContract());
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("激光先进加工系统与前沿应用团队成员信息表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }
    /**
     * Excel 解析成 员工数据集合
     *
     * @param file
     * @param allNations
     * @param allPoliticsstatus
     * @param allDepartments
     * @param allPositions
     * @param allJobLevels
     * @return
     */

    public static List<Employee> excel2Employee(MultipartFile file, List<Nation> allNations, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartments, List<Position> allPositions, List<JobLevel> allJobLevels) {
        List<Employee> list = new ArrayList<>();
        Employee employee = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    employee = new Employee();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case 1:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        employee.setName(cellValue);//姓名
                                        break;
                                    case 2:
                                        employee.setWorkID(cellValue);//工号
                                        break;
                                    case 3:
                                        employee.setGender(cellValue);//性别
                                        break;
                                    case 5:
                                        employee.setIdCard(cellValue);//身份证号码
                                        break;
                                    case 6:
                                        employee.setWedlock(cellValue);//在职状态
                                        break;
                                    case 7:
                                        int nationIndex = allNations.indexOf(new Nation(cellValue));
                                        employee.setNationId(allNations.get(nationIndex).getId());//民族
                                        break;
                                    case 8:
                                        employee.setNativePlace(cellValue);//籍贯
                                        break;
                                    case 9:
                                        int politicstatusIndex = allPoliticsstatus.indexOf(new Politicsstatus(cellValue));
                                        employee.setPoliticId(allPoliticsstatus.get(politicstatusIndex).getId());//政治面貌id
                                        break;
                                    case 10:
                                        employee.setPhone(cellValue);//联系方式
                                        break;
                                    case 11:
                                        employee.setAddress(cellValue);//联系地址
                                        break;
                                    case 12:
                                        int departmentIndex = allDepartments.indexOf(new Department(cellValue));
                                        employee.setDepartmentId(allDepartments.get(departmentIndex).getId());//部门id
                                        break;
                                    case 13:
                                        int jobLevelIndex = allJobLevels.indexOf(new JobLevel(cellValue));
                                        employee.setJobLevelId(allJobLevels.get(jobLevelIndex).getId());//职称id
                                        break;
                                    case 14:
                                        int positionIndex = allPositions.indexOf(new Position(cellValue));
                                        employee.setPosId(allPositions.get(positionIndex).getId());//职位id
                                        break;
                                    case 15:
                                        employee.setEngageForm(cellValue);//入学/聘用形式
                                        break;
                                    case 16:
                                        employee.setTiptopDegree(cellValue);//最高学历
                                        break;
                                    case 17:
                                        employee.setSpecialty(cellValue);//专业
                                        break;
                                    case 18:
                                        employee.setSchool(cellValue);//毕业院校
                                        break;
                                    case 21:
                                        employee.setWorkState(cellValue);//在职状态
                                        break;
                                    case 22:
                                        employee.setEmail(cellValue);//邮箱
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 4:
                                        employee.setBirthday(cell.getDateCellValue());//出生日期
                                        break;
                                    case 19:
                                        if(cell.getCellType() == STRING){
                                            employee.setBeginDate(null);
                                        }
                                        employee.setBeginDate(cell.getDateCellValue());//入学时间
                                        break;
                                    case 20:
                                        if(cell.getCellType() == STRING){
                                            employee.setContractTerm(null);
                                        }
                                        employee.setConversionTime(cell.getDateCellValue());//预计毕业时间
                                        break;
                                    case 24:
                                        if(cell.getCellType() == STRING){
                                            employee.setBeginContract(null);
                                        }
                                        employee.setBeginContract(cell.getDateCellValue());//合同起始日期
                                        break;
                                    case 25:
                                        if(cell.getCellType() == STRING){
                                            employee.setEndContract(null);
                                        }
                                        employee.setEndContract(cell.getDateCellValue());//合同终止日期
                                        break;
                                    case 23:
                                        employee.setContractTerm(cell.getNumericCellValue());//学制/合同期限
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(employee);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}