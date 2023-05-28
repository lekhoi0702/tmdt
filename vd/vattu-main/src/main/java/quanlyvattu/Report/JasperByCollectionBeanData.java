package quanlyvattu.Report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import quanlyvattu.entity.NhanVienEntity;
import quanlyvattu.repositoryCN1.NhanVienRepositoryCN1;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class JasperByCollectionBeanData {
	@Autowired
	static
	NhanVienRepositoryCN1 nhanVienRepo;
	public static void main(String[] args) throws JRException, FileNotFoundException {		
		
        /* Output file location to create report in pdf form */
        String outputFile = "C:\\Users\\phantan\\Desktop\\Jasper" +"JasperReportExample.html";
//        List<NhanVienEntity> listItems=null;
        /* List to hold Items */
        List<NhanVienEntity> listItems = new ArrayList<NhanVienEntity>();
//        try {
//         listItems = nhanVienRepo.findAll();
//        }catch(Exception e) {
//        	e.printStackTrace();
//        }
        /* Create Employee objects */
        NhanVienEntity emp1 = new NhanVienEntity();
        NhanVienEntity emp2 = new NhanVienEntity();
        NhanVienEntity emp3 = new NhanVienEntity();
        
        
       
        
        
        /* Add Items to List */
        listItems.add(emp1);
        listItems.add(emp2);
        listItems.add(emp3);

        /* Convert List to JRBeanCollectionDataSource */
      
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        
        //read jrxml file and creating jasperdesign object
        InputStream input = new FileInputStream(new File("C:\\Users\\phantan\\Desktop\\Jasper\\JasperReport_A4.jrxml"));
                            
        JasperDesign jasperDesign = JRXmlLoader.load(input);
        
        /*compiling jrxml with help of JasperReport class*/
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        /* Using jasperReport object to generate PDF */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        /*call jasper engine to display report in jasperviewer window*/
        JasperViewer.viewReport(jasperPrint);
        
        
        /* outputStream to create PDF */
        //OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        
        /* Write content to PDF file */
        //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        System.out.println("File Generated");	
	
	}
	
}




