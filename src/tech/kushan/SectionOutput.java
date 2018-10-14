package tech.kushan;
/* Author: Kushan Mehta
 * Date: 19-07-2018
 */

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class SectionOutput extends Section implements ISection {

    // pahts to all the screenshot images as a array
    // private String[] screenshotFileList;
    ArrayList<String> screenshotFileList;

    public SectionOutput(ArrayList<String> screenshotFileList) {
        this.screenshotFileList = screenshotFileList;
    }

    @Override
    public void generateSection(XWPFDocument document) {


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
        screenshot.setSpacingBetween(1.5);
        screenshotRun.setUnderline(UnderlinePatterns.SINGLE);
//        screenshotRun.setBold(true);
        screenshotRun.setFontFamily("Time New Roman");
        screenshotRun.setFontSize(12);
//        screenshotRun.setText("Output:");

        // Open this image file to insert
        //   String imgFile = "testScreenshot.png";
        // String imgFile;


        for (String imgFile : screenshotFileList) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(imgFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

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
            try {
                is = new FileInputStream(imgFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

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

            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Reopen the file for the paragraph insert to work properly
            try {
                is = new FileInputStream(imgFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//        screenshotRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(450), Units.toEMU(450 * HWRatio)); // 200x200 pixels

            try {
                screenshotRun.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(sizeWidth), Units.toEMU(sizeHeight)); // 200x200 pixels
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            screenshot.setSpacingBetween(2.0, LineSpacingRule.AUTO);

            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
