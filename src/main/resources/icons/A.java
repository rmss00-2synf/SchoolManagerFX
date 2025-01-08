package icons;

public static void main(String[] args) {
        String dest = "AdvancedExample.pdf";

        try {
            // Initialiser le PDFWriter et le document
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Ajouter un titre
            document.add(new Paragraph("Rapport d'Activité")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER));

            // Ajouter une image
            String imagePath = "logo.png"; // Chemin de votre image
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData).scaleToFit(100, 100);
            document.add(image);

            // Ajouter un tableau
            Table table = new Table(UnitValue.createPercentArray(new float[]{2, 4, 2}))
                    .setWidth(UnitValue.createPercentValue(100));

            // En-tête du tableau
            table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Nom").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Quantité").setBold()));

            // Contenu du tableau
            table.addCell("1");
            table.addCell("Article A");
            table.addCell("10");
            table.addCell("2");
            table.addCell("Article B");
            table.addCell("20");

            document.add(table);

            // Fermer le document
            document.close();
            System.out.println("PDF généré avec succès : " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }