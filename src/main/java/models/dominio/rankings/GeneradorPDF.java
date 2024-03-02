package models.dominio.rankings;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;
public class GeneradorPDF {
    public void pdfRakingMasIncidentes(List<Tupla> ranking, String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                float y = 700; // Coordenada vertical inicial

                // Encabezados de la tabla
                contentStream.newLineAtOffset(100, y);
                contentStream.showText("Posición");
                contentStream.newLine();
                contentStream.showText("Entidad");
                contentStream.newLine();
                contentStream.showText("Cantidad de Incidentes");
                contentStream.newLine();
                y -= 30; // Aumento del espacio vertical entre líneas

                // Contenido de la tabla
                int posicion = 1;
                for (Tupla tupla : ranking) {
                    contentStream.newLineAtOffset(100, y);
                    contentStream.showText(Integer.toString(posicion));
                    contentStream.newLine();
                    contentStream.showText(tupla.entidad.getNombre()); // Suponiendo que Entidad tiene un método getNombre()
                    contentStream.newLine();
                    contentStream.showText(Integer.toString(tupla.cantidadIncidentes()));
                    contentStream.newLine();
                    y -= 20; // Aumento del espacio vertical entre líneas
                    posicion++;
                }
                contentStream.endText();
            }
            document.save(filePath);
        }
    }

    public void pdfRakingMayorPromedioCierre(List<Tupla> ranking , String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                float y = 700; // Coordenada vertical inicial

                // Encabezados de la tabla
                contentStream.newLineAtOffset(100, y);
                contentStream.showText("Posición");
                contentStream.newLine();
                contentStream.showText("Entidad");
                contentStream.newLine();
                contentStream.showText("Promedio minutos");
                contentStream.newLine();
                y -= 30; // Aumento del espacio vertical entre líneas

                // Contenido de la tabla
                int posicion = 1;
                for (Tupla tupla : ranking) {
                    contentStream.newLineAtOffset(100, y);
                    contentStream.showText(Integer.toString(posicion));
                    contentStream.newLine();
                    contentStream.showText(tupla.entidad.getNombre()); // Suponiendo que Entidad tiene un método getNombre()
                    contentStream.newLine();
                    contentStream.showText(Integer.toString(tupla.calcularPromedioMinutos()));
                    contentStream.newLine();
                    y -= 20; // Aumento del espacio vertical entre líneas
                    posicion++;
                }

                contentStream.endText();
            }

            document.save(filePath);
        }
    }

}
