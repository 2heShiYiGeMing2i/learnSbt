package excel

import java.io.{File, FileInputStream, FileOutputStream}

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
  * Created by zhaolei on 2018/4/12
  */
class WriteToExcel {}

object WriteToExcel extends App {

  getDir()

  def createExcel(): Unit = {
    val workbook  = new HSSFWorkbook() ///"HSSFWorkbook.xls"   两种格式的表
    val workbook1 = new XSSFWorkbook() ///"XSSFWorkbook.xlsx"   两种格式的表

    val sheet  = workbook.createSheet("workbook_sheet_ ")
    val sheet1 = workbook1.createSheet("workbook_sheet_1")

    val row  = sheet.createRow(0)
    val cell = row.createCell(0)
    cell.setCellValue("HSSFWorkbook")

    val row1  = sheet1.createRow(0)
    val cell1 = row1.createCell(0)
    cell1.setCellValue("XSSFWorkbook_1")

    val HSSFWorkbook = new FileOutputStream("HSSFWorkbook.xls")
    val XSSFWorkbook = new FileOutputStream("XSSFWorkbook.xlsx")
    workbook.write(HSSFWorkbook)
    workbook1.write(XSSFWorkbook)

    HSSFWorkbook.close()
    XSSFWorkbook.close()
  }

  /**
    * 根据excel文件名获取文件名
    *
    * @param fileName excel文件名 获取 第一行 第二列的单元格内容
    * @return txt 文件名
    */
  def readFromExcel(fileName: String): Seq[String] = {
    val inputstream = new FileInputStream(fileName)
    val wb          = WorkbookFactory.create(inputstream)
    (0 until wb.getNumberOfSheets).map { id: Int =>
      val sheet = wb.getSheetAt(id)
      if (sheet.getRow(0) != null && sheet.getRow(0).getCell(0) != null && sheet
            .getRow(0)
            .getCell(0) != null && sheet
            .getRow(0)
            .getCell(0)
            .toString == "FileName") {
        sheet.getRow(0).getCell(1).toString
      } else {
        ""
      }
    }
  }

  def getDir(): Unit = {
    val dirName    = "G:\\tmp\\excels"
    val txtDirName = "G:\\tmp\\txts"
    val dir        = new File(dirName)
    if (dir.isDirectory) {
      dir
        .listFiles()
        .foreach(file =>
          readFromExcel(s"$dir\\${file.getName}")
            .filter(t => t.nonEmpty)
            .foreach(txt => new File(s"$txtDirName\\$txt.txt").createNewFile()))
    } else {
      System.err.println(s" $dir is not dir")
    }
  }

}
