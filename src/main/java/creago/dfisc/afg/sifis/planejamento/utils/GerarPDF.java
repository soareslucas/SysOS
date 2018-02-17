package creago.dfisc.afg.sifis.planejamento.utils;

import creago.dfisc.afg.sifis.planejamento.entities.OrdemServico;
import creago.dfisc.afg.sifis.planejamento.entities.Viagem;
import creago.dfisc.afg.sifis.procedimento.entities.Procedimento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Tiago Borges
 */
public class GerarPDF {

    public static void imprimirRelatorio(Procedimento procedimentos) {

        List<Procedimento> procList = new ArrayList<Procedimento>();
        procList.add(procedimentos);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(procList);
        HashMap parameters = new HashMap();

        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            response.setContentType("application/pdf");

            response.addHeader("Content-disposition", "inline; filename=\"Proc.-" + procedimentos.getIdentificador() + ".pdf\"");

            JasperPrint impressao = JasperFillManager.fillReport(servletContext.getRealPath("/WEB-INF/relatorios/procedimento.jasper"), parameters, ds);

            JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

            context.getApplication().getStateManager().saveView(context);

            context.responseComplete();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void imprimirRelatorioOS(OrdemServico ordem) {

        List<OrdemServico> ordemList = new ArrayList<>();
        ordemList.add(ordem);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(ordemList);
        HashMap parameters = new HashMap();

        System.out.println(ordem.getDataCadastro().toString());

        try {
            FacesContext context = FacesContext.getCurrentInstance();

            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            response.setContentType("application/pdf");

            response.addHeader("Content-disposition", "inline; filename=\"ordem.-" + ordem.getIdOrdemServico() + ".pdf\"");
           
            JasperPrint impressao;
            
            if (ordem.getMemorando()) {
                impressao = JasperFillManager.fillReport(servletContext.getRealPath("/WEB-INF/relatorios/ordemServico.jasper"), parameters, ds);
            } else {
                impressao = JasperFillManager.fillReport(servletContext.getRealPath("/WEB-INF/relatorios/ordemServico2.jasper"), parameters, ds);
            }

            JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

            context.getApplication().getStateManager().saveView(context);

            context.responseComplete();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

}
