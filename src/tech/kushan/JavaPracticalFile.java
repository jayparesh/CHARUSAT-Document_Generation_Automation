package tech.kushan;
/* Author: Kushan Mehta
 * Date: 20-07-2018
 */

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

final public class JavaPracticalFile extends PracticalFile {

    public JavaPracticalFile(String folderPath) {
        super(folderPath, "TEMPLATE_DOC_JAVA.docx");
    }


    @Override
    public boolean generateDocument() {


        return true;
    }

    private ArrayList<ArrayList<String>> codeFilesArray;
    private ArrayList<ArrayList<String>> outputFilesArray;
    private ArrayList<String> conclusionFilesArray;

    private ArrayList<String> aimFilesArray;

    @Override
    public boolean validateInput() {

        if (!hasCorrectSubFolder())
            return false;

        System.out.println("HERE");

        codeFilesArray = fetchCodeFiles("java");
        outputFilesArray = fetchOutputFiles();
        conclusionFilesArray = fetchConclusionFiles();
        // In the Aim file we dont need a path, we need directly the aim

        if (isAimExternal) {
            aimFilesArray = fetchAimFiles();

        } else {
            aimFilesArray = fetchAimFiles("Java", 42);
        }

        System.out.println("======================");


        System.out.println(codeFilesArray.toString());
        System.out.println(outputFilesArray.toString());
        System.out.println(conclusionFilesArray.toString());

        System.out.println("======================");

        if (!validateInputFolder(codeFilesArray, outputFilesArray, conclusionFilesArray)) {
            System.out.println("INVALID INPUT FOLDER");
            return false;
        } else return !isAimExternal || validateAimInputFolder(aimFilesArray);

    }


    @Override
    public boolean generate() {
        Section sectionAim;
        Section sectionCode;
        Section sectionOutput;
        Section sectionConclusion;

        //TODO: Iterate over each file present in the directory for each of the practical

        /* Naming Conventions and folder stucture uploaded by user:
         * PathToUploadedDirectory
         *      - code
         *          prac1a.java
         *          prac1b.java
         *          prac2.java
         *      - output
         *          output1.png/jpg or any valid image extension
         *          output2a.jpg
         *          output2b.jpg
         *      - conclusion
         *          conclusion1.txt
         *          conclusion2.txt

         */


//        CODE FROM FILE VALIDATION AND SEGREGATION
        //TODO: Create a base class function for file segration based on file extension for code files and image files as seperate functions


        int practicalNumberCounter = 2;

        for (int i = 0; i < conclusionFilesArray.size(); ++i) {

//            sectionAim = new SectionAim(practicalNumberCounter, "Some dummy aimJava as of now");
            sectionAim = new SectionAim(practicalNumberCounter, aimFilesArray.get(i), false);

            sectionCode = new SectionCode(codeFilesArray.get(i));
            sectionOutput = new SectionOutput(outputFilesArray.get(i));
            sectionConclusion = new SectionConclusion(conclusionFilesArray.get(i));

            sectionAim.generateSection(document);
            sectionCode.generateSection(document);
            sectionOutput.generateSection(document);
            sectionConclusion.generateSection(document);

            if (i != conclusionFilesArray.size() - 1) {
                XWPFParagraph p = document.getLastParagraph();
                XWPFRun r = p.createRun();
                r.addBreak(BreakType.PAGE);
            }

            ++practicalNumberCounter;

        }

        // End of creating all paragraph and stuff, finally write all the changes to document and create the actual file.


        // Fix for the output dir getting created even if the structure is invalid
        createOutputDir();

        try {
            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Doc File written successfully");


        return true;

    }

    @Override
    public boolean generate(int practicalNumber) {

        return true;
    }

    @Override
    public String hasRequiredStructure(String folderPath) {
        return null;
    }

    @Override
    public String hasRequiredStructure(FileInputStream folderStream) {
        return null;
    }

    @Override
    protected boolean hasRequiredSubStructure(String folderPath) {
        return false;
    }
}
