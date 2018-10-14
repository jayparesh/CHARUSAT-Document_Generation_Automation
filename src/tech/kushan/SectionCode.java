package tech.kushan;
/* Author: Kushan Mehta
 * Date: 19-07-2018
 */

import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

class SectionCode extends Section implements ISection {

    // private String codeFilePath;

    private ArrayList<String> codeFilePath;

    SectionCode(ArrayList<String> codeFilePath) {
        this.codeFilePath = codeFilePath;
    }

    @Override
    public void generateSection(XWPFDocument document) {

        // Create a new paragraph for Code:
        XWPFParagraph codeTitle = document.createParagraph();

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

            for (String strFilePath : codeFilePath) {

                File codeFile = new File(strFilePath);

                BufferedReader brCodeFile = new BufferedReader(new FileReader(codeFile));

                String codeLine;
                while ((codeLine = brCodeFile.readLine()) != null) {
                    codeText.setText(codeLine);
                    codeText.addCarriageReturn();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
