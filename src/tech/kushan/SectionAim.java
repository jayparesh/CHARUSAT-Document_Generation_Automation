package tech.kushan;
/* Author: Kushan Mehta
 * Date: 19-07-2018
 */

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

class SectionAim extends Section implements ISection {

    private static int AIM_COUNTER = 0;

    private int practicalNumber;
    private String aimTextString;
    private boolean isAimExternal;

    SectionAim(int practicalNumber, String aimTextString, boolean isAimExternal) {
        this.practicalNumber = practicalNumber;
        this.aimTextString = aimTextString;
        this.isAimExternal = isAimExternal;
        System.out.println("AIMSECTION:\n" + aimTextString + "\n&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }

    @Override
    public void generateSection(XWPFDocument document) {

        //XWPFParagraph practicalAim = document.getLastParagraph();
        XWPFParagraph practicalAim = null;
        if (AIM_COUNTER == 0)
            practicalAim = document.getLastParagraph();
        else
            practicalAim = document.createParagraph();

        ++AIM_COUNTER;

        XWPFRun practicalAimRun = practicalAim.createRun();
        practicalAim.setAlignment(ParagraphAlignment.CENTER);
        practicalAim.setSpacingBetween(1.5);
        practicalAimRun.setFontFamily("Times New Roman");
        practicalAimRun.setBold(true);
        practicalAimRun.setFontSize(16);
        practicalAimRun.setCapitalized(true);
        practicalAimRun.setUnderline(UnderlinePatterns.SINGLE);
        // practicalAimRun.setText("practical-1");
        practicalAimRun.setText("practical-" + practicalNumber);

        // Create a new paragraph for Aim:
        XWPFParagraph aimTitle = document.createParagraph();

        aimTitle.setStyle("CustomAutomationStyleIndent");

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
       /* aimText.setText("Introduction to Object Oriented Concepts, comparison of Java with other object\n" +
                "oriented programming languages. Introduction to JDK, JRE, JVM, javadoc,\n" +
                "Bytecode, Compiler, Interpreter, Scripting Language, Programming Language,\n" +
                "Hypertext Language, command line argument");*/


        // TODO: Add support for multiline aim

        //  aimText.setText(aimTextString);

        if (isAimExternal) {
            String[] multiLineAim = aimTextString.split("\n");
            for (String aimLine : multiLineAim) {
                aimText.setText(aimLine);
                aimText.addCarriageReturn();
            }
        } else {
            InputStream in = getClass().getResourceAsStream(aimTextString);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String aimLine;
                while ((aimLine = reader.readLine()) != null) {

                    System.out.println("AIMLINESECTION: " + aimLine);

                    aimText.setText(aimLine);
                    aimText.addCarriageReturn();
                }
            } catch (Exception e) {
                System.err.println("AIMSECTION ERROR");
            }
        }

//        aimText.addCarriageReturn();
    }
}
