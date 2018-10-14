package tech.kushan;
/* Author: Kushan Mehta
 * Date: 20-07-2018
 */

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

abstract class Section implements ISection {


    protected void setDefaultFormatting(XWPFParagraph paragraph, XWPFRun run) {

    }


    private void setParagraphFont(XWPFRun paragraphFont, int fontSize) {
        paragraphFont.setFontSize(fontSize);
    }

    protected void setParagraphFormatting(XWPFRun paragraph) {
        paragraph.setFontFamily("Times New Roman");
        setParagraphFont(paragraph, 12);
    }

    protected void setSectionTitleFormatting(XWPFRun paragraph) {
        paragraph.setBold(true);
        paragraph.setUnderline(UnderlinePatterns.SINGLE);
    }

    protected void setParagraphFormatting(XWPFRun paragraph, boolean isTitle) {
        paragraph.setFontFamily("Times New Roman");
        paragraph.setBold(true);
        paragraph.setFontSize(16);
        paragraph.setCapitalized(true);
        paragraph.setUnderline(UnderlinePatterns.SINGLE);
    }


    protected void setPrargraphStyle(XWPFParagraph paragraph, String paragraphStyle) {
        paragraph.setStyle(paragraphStyle);
    }

    protected void styleNormal(XWPFParagraph paragraph) {
        setPrargraphStyle(paragraph, "CustomAutomationStyleIndent");
    }

    protected void styleStayTogether(XWPFParagraph paragraph) {
        setPrargraphStyle(paragraph, "CustomAutomationStyle");
    }

    protected void alignCenter(XWPFParagraph paragraph) {
        paragraph.setAlignment(ParagraphAlignment.CENTER);
    }

    protected void alignJustify(XWPFParagraph paragraph) {
        paragraph.setAlignment(ParagraphAlignment.BOTH);
    }

}
