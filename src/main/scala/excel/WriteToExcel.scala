package excel

import java.io.FileOutputStream

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
  * Created by zhaolei on 2018/4/12
  */
class WriteToExcel {}

object WriteToExcel extends App {

  createExcel()

  def createExcel(): Unit = {
    val workbook = new HSSFWorkbook()   ///"HSSFWorkbook.xls"   两种格式的表
    val workbook1 = new XSSFWorkbook()   ///"XSSFWorkbook.xlsx"   两种格式的表

    val sheet = workbook.createSheet("workbook_sheet_ ")
    val sheet1 = workbook1.createSheet("workbook_sheet_1")

    val row = sheet.createRow(0)
    val cell = row.createCell(0)
    cell.setCellValue("HSSFWorkbook")

    val row1 = sheet1.createRow(0)
    val cell1 = row1.createCell(0)
    cell1.setCellValue("XSSFWorkbook_1")

    val HSSFWorkbook = new FileOutputStream("HSSFWorkbook.xls")
    val XSSFWorkbook = new FileOutputStream("XSSFWorkbook.xlsx")
    workbook.write(HSSFWorkbook)
    workbook1.write(XSSFWorkbook)

    HSSFWorkbook.close()
    XSSFWorkbook.close()
  }
}
