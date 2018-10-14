package tech.kushan;/* Author: Kushan Mehta
 * Date: 19-07-2018
 */

import org.apache.poi.xwpf.usermodel.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class SectionConclusion extends Section implements ISection {

    private String conclusionFilePath;

    public SectionConclusion(String conclusionFilePath) {
        this.conclusionFilePath = conclusionFilePath;
    }

    @Override
    public void generateSection(XWPFDocument document) {

        // Create a new paragraph for conclusion:
        XWPFParagraph conclusionTitle = document.createParagraph();
        conclusionTitle.setStyle("CustomAutomationStyle");
        conclusionTitle.setAlignment(ParagraphAlignment.BOTH);
        conclusionTitle.setSpacingBetween(1.5);

        // Create first run instance of conclusionTitle Paragraph to store underlined text
        XWPFRun conclusionTitleRun = conclusionTitle.createRun();

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

//        String conclusionTest = "From the above program we learnt the use of cin and cout. We also learnt the and use of escape s";


        //  conclusionText.setText(conclusionTest + " " + conclusionTest + " " + conclusionTest);


        try {
            File codeFile = new File(conclusionFilePath);

            BufferedReader brCodeFile = new BufferedReader(new FileReader(codeFile));

            String codeLine;
            while ((codeLine = brCodeFile.readLine()) != null) {
                conclusionText.setText(codeLine);
                conclusionText.addCarriageReturn();
            }
        } catch (Exception e) {

        }

        //conclusionText.addBreak(BreakType.PAGE);


    }
}
