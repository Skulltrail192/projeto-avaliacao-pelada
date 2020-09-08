package com.avaliacao.util;

import com.avaliacao.db.UsuarioDAO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ReportUtil {
    public void gerarRelatorio(String fileName, List listaUs) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();;
            FacesContext context = FacesContext.getCurrentInstance();
            JasperReport pathjrxml = JasperCompileManager.compileReport(context.getExternalContext().getRealPath("WEB-INF/reports/"+ fileName+ ".jrxml"));
            JasperPrint printReport = null;
            ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
            printReport = JasperFillManager.fillReport(pathjrxml, null, new JRBeanCollectionDataSource(listaUs));
            JasperExportManager.exportReportToPdfStream(printReport, pdfReportStream);

            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");
            response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));

            OutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(pdfReportStream.toByteArray());
            responseOutputStream.close();
            //JasperExportManager.exportReportToPdfFile(printReport, "WEB-INF/reports/usuarios.pdf");


        } catch (JRException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
