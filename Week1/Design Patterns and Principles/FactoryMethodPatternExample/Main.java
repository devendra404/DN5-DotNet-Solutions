// Interface for all document types
interface Document {
    void open();
}

// Word document implementation
class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word Document");
    }
}

// PDF document implementation
class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF Document");
    }
}

// Excel document implementation
class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel Document");
    }
}

// Abstract factory class
abstract class DocumentFactory {

    // Factory Method
    public abstract Document createDocument();
}

// Factory for Word documents
class WordFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

// Factory for PDF documents
class PdfFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

// Factory for Excel documents
class ExcelFactory extends DocumentFactory {

    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Main class to test the Factory Method Pattern
public class Main {

    public static void main(String[] args) {

        // Creating a Word document using its factory
        DocumentFactory wordFactory = new WordFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();

        // Creating a PDF document using its factory
        DocumentFactory pdfFactory = new PdfFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();

        // Creating an Excel document using its factory
        DocumentFactory excelFactory = new ExcelFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
    }
}