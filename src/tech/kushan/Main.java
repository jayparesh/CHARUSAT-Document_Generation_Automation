package tech.kushan;

import java.awt.image.BufferedImage;
import java.io.*;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;

/*
 * AIM line spacing = 1.5
 * Code line spacing = 1.15
 * Conclusion line spacing = 1.5
 * */

public class Main {

    //TODO: ERROR RESOLUTION FOR ARTIFICAT BUILDING

    public static void main(String[] args) throws Exception {

        PracticalFile javaFile = new GenericPracticalFile("C:\\Users\\Kushan Mehta\\Desktop\\UEPracticals");

        if (javaFile.validateInput())
            javaFile.generate();
        else {
            System.out.println("Freaking folder error");
        }

    }


    public static void mainOther(String[] args) throws Exception {

        //Blank Document
        FileInputStream templateIN = new FileInputStream("TEMPLATE_DOC_JAVA.docx");

        // Open Document with existing defined template to have header and footer set
        XWPFDocument document = new XWPFDocument(templateIN);

        templateIN.close();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File("output.docx"));

//        ----------- AIM PARAGRAPH SECTION BEGINS --------------


        // Create a paragraph for PRACTICAL-1 (getLastParagraph() was required to start the document from the first line keeping all the formatting)
        // XWPFParagraph practicalAim = document.createParagraph();
        XWPFParagraph practicalAim = document.getLastParagraph();
        XWPFRun practicalAimRun = practicalAim.createRun();
        practicalAim.setAlignment(ParagraphAlignment.CENTER);
        practicalAim.setSpacingBetween(1.5);
        practicalAimRun.setFontFamily("Times New Roman");
        practicalAimRun.setBold(true);
        practicalAimRun.setFontSize(16);
        practicalAimRun.setCapitalized(true);
        practicalAimRun.setUnderline(UnderlinePatterns.SINGLE);
        practicalAimRun.setText("practical-1");

        // Create a new paragraph for Aim:
        XWPFParagraph aimTitle = document.createParagraph();

        aimTitle.setStyle("CustomAutomationStyleIndent");

        // Get the indentation from previous para which is infact the indentation of the template document
//        aimTitle.setIndentationRight(document.getLastParagraph().getIndentationRight());
//        aimTitle.setIndentationLeft(document.getLastParagraph().getIndentationLeft());

        // Create first run instance of aimTitle Paragraph to store underlined text
        XWPFRun aimTitleRun = aimTitle.createRun();
        aimTitle.setSpacingBetween(1.5);
        aimTitleRun.setUnderline(UnderlinePatterns.SINGLE);
        aimTitleRun.setBold(true);
        aimTitleRun.setFontFamily("Time New Roman");
        aimTitleRun.setFontSize(12);
        aimTitleRun.setText("Aim:");
        aimTitleRun.addCarriageReturn();

        // Create 2nd instance of aimTitle to have different formatting, but continuing under the same paragraph
        XWPFRun aimText = aimTitle.createRun();
        aimText.setBold(true);
        aimText.setFontFamily("Time New Roman");
        aimText.setFontSize(12);
        aimText.setText("Introduction to Object Oriented Concepts, comparison of Java with other object\n" +
                "oriented programming languages. Introduction to JDK, JRE, JVM, javadoc,\n" +
                "Bytecode, Compiler, Interpreter, Scripting Language, Programming Language,\n" +
                "Hypertext Language, command line argument");
        aimText.addCarriageReturn();


//        ----------- CODE PARAGRAPH SECTION BEGINS --------------

        // Create a new paragraph for Code:
        XWPFParagraph codeTitle = document.createParagraph();

        // Get the indentation from previous para which is infact the indentation of the template document
//        codeTitle.setIndentationRight(practicalAim.getIndentationRight());
//        codeTitle.setIndentationLeft(practicalAim.getIndentationLeft());

        codeTitle.setStyle("CustomAutomationStyleIndent");


        // Create first run instance of CodeTitle Paragraph to store underlined text
        XWPFRun codeTitleRun = codeTitle.createRun();
        codeTitle.setSpacingBetween(1.5);
        codeTitleRun.setUnderline(UnderlinePatterns.SINGLE);
        codeTitleRun.setBold(true);
        codeTitleRun.setFontFamily("Time New Roman");
        codeTitleRun.setFontSize(12);
        codeTitleRun.setText("Code:");
        codeTitleRun.addCarriageReturn();

        // Create 2nd instance of CodeTitle to have different formatting, but continuing under the same paragraph
        XWPFRun codeText = codeTitle.createRun();
        codeText.setFontFamily("Time New Roman");
        codeText.setFontSize(12);
        codeTitle.setSpacingBetween(1.15);

        // Extract the text for Code from a text file
        try {
            File codeFile = new File("testCode.java");

            BufferedReader brCodeFile = new BufferedReader(new FileReader(codeFile));

            String codeLine;
            while ((codeLine = brCodeFile.readLine()) != null) {
                codeText.setText(codeLine);
                codeText.addCarriageReturn();
            }
        } catch (Exception e) {

        }

//        ----------- OUTPUT SCREENSHOT PARAGRAPH SECTION BEGINS --------------

        // Create a paragraph for reading and added centered image of specific width with height/width ratio maintained

       /* XWPFParagraph screenshotTitlePre = document.createParagraph();
        screenshotTitlePre.setIndentationLeft(codeTitle.getIndentationLeft());
        screenshotTitlePre.setIndentationRight(codeTitle.getIndentationRight());
        XWPFRun screenshotTitle = screenshotTitlePre.createRun();
        screenshotTitle.setText("Output:");
        screenshotTitlePre.setAlignment(ParagraphAlignment.BOTH);
        screenshotTitlePre.setSpacingBetween(1.5);
        screenshotTitle.setUnderline(UnderlinePatterns.SINGLE);
        screenshotTitle.setBold(true);
        screenshotTitle.setFontFamily("Time New Roman");
        screenshotTitle.setFontSize(12);*/

        XWPFParagraph screenshot = document.createParagraph();

        screenshot.setStyle("CustomAutomationStyle");


        XWPFRun screenshotTitle = screenshot.createRun();
        screenshotTitle.setText("Output:");
        screenshot.setAlignment(ParagraphAlignment.BOTH);
        screenshot.setSpacingBetween(1.5);
        screenshotTitle.setUnderline(UnderlinePatterns.SINGLE);
        screenshotTitle.setBold(true);
        screenshotTitle.setFontFamily("Time New Roman");
        screenshotTitle.setFontSize(12);
        screenshotTitle.addCarriageReturn();

        XWPFRun screenshotRun = screenshot.createRun();
        // Caption text above the screenshot
//        screenshot.setIndentationLeft(codeTitle.getIndentationLeft());
//        screenshot.setIndentationRight(codeTitle.getIndentationRight());
//        screenshot.setAlignment(ParagraphAlignment.CENTER);
        screenshot.setSpacingBetween(1.5);
        screenshotRun.setUnderline(UnderlinePatterns.SINGLE);
//        screenshotRun.setBold(true);
        screenshotRun.setFontFamily("Time New Roman");
        screenshotRun.setFontSize(12);
//        screenshotRun.setText("Output:");

        // Open this image file to insert
        String imgFile = "testScreenshot.png";
        FileInputStream is = new FileInputStream(imgFile);

        // Get the image height and width to get and maintain the ration in document

        int ImageHeight = 0;
        int ImageWidth = 0;
        try

        {
            BufferedImage image = ImageIO.read(is);

            ImageHeight = image.getHeight();
            ImageWidth = image.getWidth();
            // Closing the inputStream was required as it was not allowing paragraph to show inserted image
            is.close();
        } catch (
                Exception e)

        {

        }

        // Reopen the file for the paragraph insert to work properly
        is = new FileInputStream(imgFile);

        int sizeWidth = 450;
        int sizeHeight = 450;

        if (ImageHeight > ImageWidth) { // Special case when image is very tall

            // Calculate and store the image ratio for further usage
            double HWRatio = (double) ImageWidth / (double) ImageHeight;

            sizeWidth = (int) (sizeWidth * HWRatio);


        } else {

            // Calculate and store the image ratio for further usage
            double HWRatio = (double) ImageHeight / (double) ImageWidth;

            sizeHeight = (int) (sizeWidth * HWRatio);
        }
        //TODO: [FIXED FOR NOW - TEST MORE EXTENSIVELY] Fix a situation where the image is very tall (i.e. height>width)
        // In that case inverse the ratio compression and change the height to some constant value

        // The width of the image is fixed to 450px but the height will vary according to the ratio to keep it symmetrical looking
        //    screenshotRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(420), Units.toEMU(420 * HWRatio)); // 200x200 pixels

        //  screenshotRun.addCarriageReturn();

        is.close();
        // Reopen the file for the paragraph insert to work properly
        is = new FileInputStream(imgFile);

//        screenshotRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(450), Units.toEMU(450 * HWRatio)); // 200x200 pixels

        screenshotRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(sizeWidth), Units.toEMU(sizeHeight)); // 200x200 pixels


        screenshot.setSpacingBetween(2.0, LineSpacingRule.AUTO);

        is.close();


//        ----------- CONCLUSION PARAGRAPH SECTION BEGINS --------------

//        XWPFParagraph practicalConclusion = document.createParagraph();

        //  practicalConclusion.setPageBreak(true);

        // Create a new paragraph for conclusion:
        XWPFParagraph conclusionTitle = document.createParagraph();

        conclusionTitle.setStyle("CustomAutomationStyle");

        // Get the indentation from previous para which is infact the indentation of the template document
//        conclusionTitle.setIndentationRight(aimTitle.getIndentationRight());
//        conclusionTitle.setIndentationLeft(aimTitle.getIndentationLeft());

        // Create first run instance of conclusionTitle Paragraph to store underlined text
        XWPFRun conclusionTitleRun = conclusionTitle.createRun();
        conclusionTitle.setSpacingBetween(1.5);
        conclusionTitleRun.setUnderline(UnderlinePatterns.SINGLE);
        conclusionTitleRun.setBold(true);
        conclusionTitleRun.setFontFamily("Time New Roman");
        conclusionTitleRun.setFontSize(12);
        conclusionTitleRun.setText("Conclusion:");
        conclusionTitleRun.addCarriageReturn();

        // Create 2nd instance of conclusionTitle to have different formatting, but continuing under the same paragraph
        XWPFRun conclusionText = conclusionTitle.createRun();
        conclusionText.setFontFamily("Time New Roman");
        conclusionText.setFontSize(12);

        String conclusionTest = "From the above program we learnt the use of cin and cout. We also learnt the and use of escape s";


        conclusionText.setText(conclusionTest + " " + conclusionTest + " " + conclusionTest);


//        -------------- ALL REQUIRED SECTIONS END --------------------


        // End of creating all paragraph and stuff, finally write all the changes to document and create the actual file.
        document.write(out);
        out.close();
        System.out.println("Doc File written successfully");
    }
}
