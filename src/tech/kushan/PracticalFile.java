package tech.kushan;
/* Author: Kushan Mehta
 * Date: 19-07-2018
 */

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract public class PracticalFile {

    protected String folderPath;

//    protected FileInputStream templateIN;

    protected InputStream templateIN;


    protected XWPFDocument document;

    protected FileOutputStream out;

    protected boolean isAimExternal = false;

   /* protected Section sectionAim;
    protected Section sectionCode;
    protected Section sectionOutput;
    protected Section sectionConclusion;*/

    protected boolean validateInputCommon(ArrayList<ArrayList<String>> codeArray, String desiredPrefix, char desiredPrefixChar) {

        int[] counter = new int[100];
        int Max = -1;

        System.out.println(desiredPrefix + ":");

        for (ArrayList<String> outerList : codeArray) {
            for (String fileName : outerList) {


                String fname = fileName.substring(fileName.lastIndexOf('\\') + 1).trim();

                System.out.println(fname);

//                String pracSuffix = fname.substring(0, fname.indexOf('c') + 1);

//                String pracSuffix = fname.substring(0, fname.indexOf(desiredPrefixChar) + 1);

                String pracSuffix = fname.substring(0, fname.lastIndexOf(desiredPrefixChar) + 1);


                System.out.println(pracSuffix);

//                if (pracSuffix.equalsIgnoreCase("prac")) {
                if (pracSuffix.equalsIgnoreCase(desiredPrefix)) {


//                    fname = fname.substring(fname.indexOf('c') + 1).trim();

                    fname = fname.substring(fname.lastIndexOf(desiredPrefixChar) + 1).trim();


                    fname = fname.substring(0, fname.indexOf('.'));

                    System.out.println(fname);

                    String number = "";

                    for (int i = 0, size = fname.length(); i < size; ++i) {

                        if (Character.isDigit(fname.charAt(i))) {
                            number += String.valueOf(fname.charAt(i));
                        } else
                            break;

                    }

                    Max = Math.max(Max, Integer.parseInt(number));

                    counter[Integer.parseInt(number)]++;
                }


            }
        }

        int i = 2;
        if (isAimExternal)
            i = 1;
        for (; i <= Max; ++i) {
            if (counter[i] == 0)
                return false;
        }


        return true;

    }

    abstract public boolean validateInput();


    protected boolean validateCodeInputFolder(ArrayList<ArrayList<String>> codeArray) {

        return validateInputCommon(codeArray, "prac", 'c');

    }

    protected boolean validateOutputInputFolder(ArrayList<ArrayList<String>> outputArray) {

        return validateInputCommon(outputArray, "output", 't');

    }

    protected boolean validateConclusionInputFolder(ArrayList<String> conclusionArray) {


        ArrayList<ArrayList<String>> ar = new ArrayList<>();

        for (String str : conclusionArray) {
            ArrayList<String> tmp = new ArrayList<>();

            tmp.add(str);

            ar.add(tmp);
        }

        return validateInputCommon(ar, "conclusion", 'n');

        //   return validateInputCommon(conclusionArray, "conclusion", 'n');

    }

    protected boolean validateAimInputFolder(ArrayList<String> aimArray) {


        ArrayList<ArrayList<String>> ar = new ArrayList<>();

        for (String str : aimArray) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(str);
            ar.add(tmp);
        }

        return validateInputCommon(ar, "aim", 'm');

        //   return validateInputCommon(conclusionArray, "conclusion", 'n');

    }


    protected boolean validateInputFolder(ArrayList<ArrayList<String>> codeArray, ArrayList<ArrayList<String>> outputArray,
                                          ArrayList<String> conclusionArray) {

        return validateCodeInputFolder(codeArray) && validateOutputInputFolder(outputArray) && validateConclusionInputFolder(conclusionArray);

    }


    protected String[] fetchCodeDir(String codeExtension) {
        File file = new File(folderPath + "\\code");


        System.out.println("DEBUG ALNUMSORTING:");


        String[] fileArray = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = name.substring(name.lastIndexOf(".") + 1);
                System.out.print(name + ", ");
                return extension.equalsIgnoreCase(codeExtension);
            }
        });

        // TODO: Apply sorting algo here to avoid major changes in code at other places


        System.out.println("SORTED LIST:");
        List<String> values = Arrays.asList(fileArray);
        List<String> sorted = values.stream().sorted(new AlphanumComparator()).collect(Collectors.toList());

        fileArray = sorted.toArray(new String[0]);

//        System.out.println(values.stream().sorted(new AlphanumComparator()).collect(Collectors.joining(" ")));


        // returns list of code files (eg. prac1.java etc)
        return fileArray;
    }

    protected String[] fetchOutputDir() {
        File file = new File(folderPath + "\\output");

        String[] fileArray = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = name.substring(name.lastIndexOf(".") + 1);
                //  System.out.print(name + ", ");
                return extension.equalsIgnoreCase("png") ||
                        extension.equalsIgnoreCase("jpg") ||
                        extension.equalsIgnoreCase("jpeg");
            }
        });

        System.out.println("SORTED OUTPUT LIST:");
        List<String> values = Arrays.asList(fileArray);
        List<String> sorted = values.stream().sorted(new AlphanumComparator()).collect(Collectors.toList());
        fileArray = sorted.toArray(new String[0]);

        // returns list of output image files
        return fileArray;
    }

    protected String[] fetchConclusionDir() {
        File file = new File(folderPath + "\\conclusion");

        String[] fileArray = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = name.substring(name.lastIndexOf(".") + 1);
                //  System.out.print(name + ", ");
                return extension.equalsIgnoreCase("txt");
            }
        });

        System.out.println("SORTED CONCLUSION LIST:");
        List<String> values = Arrays.asList(fileArray);
        List<String> sorted = values.stream().sorted(new AlphanumComparator()).collect(Collectors.toList());
        fileArray = sorted.toArray(new String[0]);

        // returns list of conclusion files
        return fileArray;
    }


    protected String[] fetchAimDir() {
        File file = new File(folderPath + "\\aim");

        String[] fileArray = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = name.substring(name.lastIndexOf(".") + 1);
                //  System.out.print(name + ", ");
                return extension.equalsIgnoreCase("txt");
            }
        });

        System.out.println("SORTED AIM LIST:");
        List<String> values = Arrays.asList(fileArray);
        List<String> sorted = values.stream().sorted(new AlphanumComparator()).collect(Collectors.toList());
        fileArray = sorted.toArray(new String[0]);

        // returns list of aim files
        return fileArray;
    }


    protected ArrayList<String> fetchAimDir(String language, int numberOfPracticals) {

        ArrayList<String> aimContents = new ArrayList<>();


        for (int i = 2; i <= numberOfPracticals; ++i) {

//            InputStream in = getClass().getResourceAsStream("/res/aim" + language + "/aim" + i + ".txt");
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            aimContents.add("/res/aim" + language + "/aim" + i + ".txt");

            // reader.lines().forEach(System.out::println);

            //System.out.println();

//            String aimText = reader.lines().collect(Collectors.joining());

//            aimContents.add(aimText);

        }


//        InputStream in = getClass().getResourceAsStream("/res/aim" + language + "/aim2.txt");

        System.out.println("************************");

        System.out.println(aimContents.toString());

//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

//        reader.lines().forEach(System.out::println);

        System.out.println("*************************");


        return aimContents;

    }


    protected ArrayList<ArrayList<String>> fetchCodeFiles(String codeExtension) {
        return fetchFiles("code");
    }

    protected ArrayList<ArrayList<String>> fetchOutputFiles() {
        return fetchFiles("output");
    }

    protected ArrayList<String> fetchConclusionFiles() {
        ArrayList<String> conclusion = new ArrayList<>();

        String[] itr = fetchConclusionDir();

        for (String fname : itr)
            conclusion.add(folderPath + "\\conclusion\\" + fname);

        return conclusion;
    }

    protected ArrayList<String> fetchAimFiles(String language, int numberOfPracticals) {


        ArrayList<String> aim = fetchAimDir(language, numberOfPracticals);

        return aim;
    }

    // Generic Fetch method where the aims are side loaded manually instead of using inbuilt system
    protected ArrayList<String> fetchAimFiles() {

        ArrayList<String> aims = new ArrayList<>();

        String[] itr = fetchAimDir();

        for (String fname : itr)
            aims.add(folderPath + "\\aim\\" + fname);

        return aims;
    }


    protected boolean hasCorrectSubFolder() {


        File file = new File(folderPath);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        System.out.println(Arrays.toString(directories));


       /* if(directories[0].equalsIgnoreCase("code") )
            if(directories[1].equalsIgnoreCase("output"))
            return true;
        return false;*/

      /*  return directories[0].equalsIgnoreCase("code") &&
                directories[2].equalsIgnoreCase("output") &&
                directories[1].equalsIgnoreCase("conclusion");
*/

        boolean hasCodeDir = false;
        boolean hasOutputDir = false;
        boolean hasConclusionDir = false;

        for (String str : directories) {
            if (str.equalsIgnoreCase("code"))
                hasCodeDir = true;
            else if (str.equalsIgnoreCase("output"))
                hasOutputDir = true;
            else if (str.equalsIgnoreCase("conclusion"))
                hasConclusionDir = true;
            else if (str.equalsIgnoreCase("aim"))
                isAimExternal = true;
        }

        return hasCodeDir && hasOutputDir && hasConclusionDir;


    }

    private ArrayList<ArrayList<String>> fetchFiles(String type) {

        int STARTING_LOOP_INDEX = 0;

        String[] fileArray = null;

        String pathSuffix = "";

        if (type.equalsIgnoreCase("code")) {
            STARTING_LOOP_INDEX = 4;
            pathSuffix = "\\code\\";
            fileArray = fetchCodeDir("java");
        } else if (type.equalsIgnoreCase("output")) {
            fileArray = fetchOutputDir();
            STARTING_LOOP_INDEX = 6;
            pathSuffix = "\\output\\";
        }

        ArrayList<ArrayList<String>> fileGrid = new ArrayList<>();
        ArrayList<String> tmpFile = new ArrayList<>();

        int k = 0;
        int currPrac = 0;
        int counter = 0;

        int practicalStartNumber = Integer.MAX_VALUE;
        int practicalEndNumber = Integer.MIN_VALUE;

        //  System.out.println("\n" + Arrays.toString(fileArray));

        int practicalNumber = 0;

        for (String filename : fileArray) {

            String pracNumber = "";
            String subNumber = "";

            for (int i = STARTING_LOOP_INDEX, size = filename.length(); i < size; ++i) {
                char ch = filename.charAt(i);
                if (Character.isDigit(ch)) {
                    pracNumber += ch;
                } else if (ch == '.') {
                    break;
                } else if (Character.isLetter(ch)) {
                    subNumber += ch;
                    break;
                }
            }

            // System.out.println("file: " + filename + ", " + pracNumber + ", " + subNumber);

            practicalNumber = Integer.parseInt(pracNumber);

            practicalStartNumber = Math.min(practicalStartNumber, practicalNumber);
            practicalEndNumber = Math.max(practicalEndNumber, practicalNumber);

            char subPractical = '.';
            if (!subNumber.equalsIgnoreCase(""))
                subPractical = subNumber.charAt(0);

            if (currPrac != practicalNumber && !tmpFile.isEmpty()) {
                // fileGrid.add(currPrac, tmpFile);

                //   System.out.println(Arrays.toString(tmpFile.toArray()));

                fileGrid.add(new ArrayList<String>(tmpFile));
                tmpFile.clear();
                ++counter;

            }

            if (tmpFile.isEmpty()) {
                currPrac = practicalNumber;
            }

            tmpFile.add(folderPath + pathSuffix + filename);

            // fileGrid[practicalNumber][k++] = filename;


        }

        //   System.out.println(Arrays.toString(tmpFile.toArray()));
        fileGrid.add(new ArrayList<String>(tmpFile));
        tmpFile.clear();
        ++counter;

        // We now have ArrayList of Arraylist of String
        // Starting and the ending index of the practical files


        return fileGrid;

    }


    protected PracticalFile(String folderPath, String templateName) {

        this.folderPath = folderPath;

        //Blank Document
        try {

            // TODO: Fix the presence of template file in the jar file itself as a FileInputStream instead of getStream output of current solution

            //  templateIN = new FileInputStream(templateName);

            URL url = (PracticalFile.class.getResource("/res/" + templateName));

            templateIN = url.openStream();

            //  templateIN = (FileInputStream) this.getClass().getClassLoader().getResourceAsStream(templateName);

        } //catch (FileNotFoundException e) {
        catch (Exception e) {
            e.printStackTrace();
        }

        // Open Document with existing defined template to have header and footer set
        try {
            document = new XWPFDocument(templateIN);
            templateIN.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


       /* try {


            //TODO: CREATE THE GENERATE_FOLDER ONLY IF THERE IS PROPER FOLDER STRCUTRE

            new File(folderPath + "\\GENERATED_FILE").mkdirs();

            out = new FileOutputStream(new File(folderPath + "\\GENERATED_FILE\\" + "PRACTICAL_FILE.docx"));

            //  out = new FileOutputStream(new File(    "OUTPUT_NEW.docx"));


            // out = new FileOutputStream(new File("OUTPUTTEST.docx"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    // Method which will be used to carry out the actual document generation once structure is validated
    public abstract boolean generate();

    protected abstract boolean generate(int practicalNumber);

    public abstract boolean generateDocument();

    // validate whether the folder substructure etc is proper and valid
    public abstract String hasRequiredStructure(String folderPath);

    public abstract String hasRequiredStructure(FileInputStream folderStream);

    protected abstract boolean hasRequiredSubStructure(String folderPath);

    //TODO: Add a sorting algo to add correct sequence of files to the array list (i,e 2 should come before 11)


    protected void createOutputDir() {
        try {
            new File(folderPath + "\\GENERATED_FILE").mkdirs();
            out = new FileOutputStream(new File(folderPath + "\\GENERATED_FILE\\" + "PRACTICAL_FILE.docx"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
