/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// CHANGE THE PACKAGE AT YOUR CONVENIENCE
package edu.upc.etsetb.arqsoft;
// CHANGE THESE IMPORTS AS PER YOUR OWN PACKAGES
//import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
//import edu.upc.etsetb.arqsoft.spreadsheet.usecases.formulas.evaluator.FormulaEvaluator;

// KEEP THESE IMPORTS (for JUnit 4.12)
import edu.upc.etsetb.arqsoft.entities.Controller;
import edu.upc.etsetb.arqsoft.entities.SpreadsheetController;
import edu.upc.etsetb.arqsoft.entities.UserInterface;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Juan Carlos Cruellas at Universidad Politécnica de Cataluña
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpreadsheetTest {

    // DECLARE THE INSTANCE AS A REFERENCE OF AN OBJECT TO YOUR SPREADSHEETCONTROLLER
    private SpreadsheetController instance;

    public SpreadsheetTest()
            throws Exception {
        // IMPORTANT: INSERT BELOW. A SET OF SENTENCES THAT GENERATE AN 
        // ENVIRONMENT READY FOR MAKING THAT THE SpreadsheetController SETS CONTENTS IN CELLS OF THE SPREADSHEET 
        // AND CO-ORDINATES THE COMPUTATION OF THEIR VALUES.
        // BASICALLY: this.instance has to be an instance of SpreadsheetController and everything must be ready 
        // for editing cells in the spreadsheet
        UserInterface ui = new UserInterface();
        this.instance = ui.getSpreadsheetController();
        this.instance.createSpreadsheet(1);

        //IMPORTANT: KEEP THE SENTENCES BELOW.
        instance.editSpreadsheet("A1", "1");
        instance.editSpreadsheet("A2", "2");
        instance.editSpreadsheet("A3", "3");
        instance.editSpreadsheet("A4", "4");
        instance.editSpreadsheet("A5", "5");
        instance.editSpreadsheet("A6", "6");
        instance.editSpreadsheet("A7", "7");
        instance.editSpreadsheet("A8", "8");
        instance.editSpreadsheet("A9", "9");
        instance.editSpreadsheet("A10", "10");
        instance.editSpreadsheet("A11", "11");
        instance.editSpreadsheet("A12", "12");
        instance.editSpreadsheet("A13", "13");
        instance.editSpreadsheet("A14", "14");
        instance.editSpreadsheet("A15", "15");
        instance.editSpreadsheet("A16", "16");
        instance.editSpreadsheet("A17", "17");
        instance.editSpreadsheet("A18", "18");
        instance.editSpreadsheet("A19", "19");
        instance.editSpreadsheet("A20", "20");
        instance.editSpreadsheet("A21", "21");
        instance.editSpreadsheet("A22", "22");
        instance.editSpreadsheet("A23", "23");
        instance.editSpreadsheet("A24", "24");
        instance.editSpreadsheet("C1", "This is a string");

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test01SetCellContent_TextContent() throws Exception {
        System.out.println("setting cell content to a text");
        String content = this.instance.getCellContentAsString("C1");
        Assert.assertEquals("This is a string", content);
    }
    /**
     * Test of setCellContent method, of class SpreadsheetImpl. //
     */
    @Test
    public void test02SetCellContent_NumContent() throws Exception {
        System.out.println("setting cell content to a number");
        double content = this.instance.getCellContentAsDouble("A24");
        Assert.assertEquals(24.0, content, 0);
    }

    @Test
    public void test03SetCellContent_Formula_Numbers() throws Exception {
        System.out.println("setting cell content to a formula with: numbers");
        this.instance.editSpreadsheet("C20", "=1/2");
        this.instance.editSpreadsheet("B1", "=C20+2*10");
        double content = this.instance.getCellContentAsDouble("B1");
        Assert.assertEquals(20.5, content, 0.0);
    }

    @Test
    public void test04SetCellContent_Formula_Numbers_1LevelRBs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "1 level of round brackets");
        this.instance.editSpreadsheet("B2", "=20/(5+5)");
        double content = this.instance.getCellContentAsDouble("B2");
        Assert.assertEquals(2.0, content, 0.0);
    }
    @Test
    public void test05SetCellContent_Formula_Numbers_2Level2RBs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "2 levels of round brackets");
        this.instance.editSpreadsheet("B3", "=100/(5+(25/5))");
        double content = this.instance.getCellContentAsDouble("B3");
        Assert.assertEquals(10.0, content, 0.0);
    }
    @Test
    public void test06SetCellContent_Formula_Numbers_CellRefs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references");
        this.instance.editSpreadsheet("B4", "=A1*10-5");
        double content = this.instance.getCellContentAsDouble("B4");
        Assert.assertEquals(5.0, content, 0.0);
    }
    @Test
    public void test07SetCellContent_Formula_Numbers_CellRefs_1LevelRBs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references, 1 level of round brackets");
        this.instance.editSpreadsheet("B5", "=(A5*4)/(A2+A2)");
        double content = this.instance.getCellContentAsDouble("B5");
        Assert.assertEquals(5.0, content, 0.0);
    }
    @Test
    public void test08SetCellContent_Formula_Numbers_CellRefs_2Level2RBs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references, 2 levels of round brackets");
        this.instance.editSpreadsheet("B6", "=100/(A5+(A5*A5/5))");
        double content = this.instance.getCellContentAsDouble("B6");
        Assert.assertEquals(10.0, content, 0.0);
    }

    @Test
    public void test09SetCellContent_Formula_Numbers_CellRefs_Func_NumArgs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references, function (arguments: numbers)");
        this.instance.editSpreadsheet("B7", "=(A5*4)/(A2+A2)+SUMA(1;2;3;4;5)");
        double content = this.instance.getCellContentAsDouble("B7");
        Assert.assertEquals(20.0, content, 0.0);
    }

    @Test
    public void test10SetCellContent_Formula_Numbers_CellRefs_Func_NumCellRefsArgs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references, function (arguments: numbers, cell "
                + "references)");
        this.instance.editSpreadsheet("B8", "=(A5*4)/(A2+A2)+SUMA(A1;A2;3;4;5)");
        double content = this.instance.getCellContentAsDouble("B8");
        Assert.assertEquals(20.0, content, 0.0);
    }

    @Test
    public void test11SetCellContent_Formula_Numbers_CellRefs_Func_NumCellRefsRangesArgs() throws Exception {
        System.out.println("setting cell content to a formula with: numbers, "
                + "cell references, function (arguments: numbers,cell "
                + "references,ranges)");
        this.instance.editSpreadsheet("B9", "=(A5*4)/(A2+A2)+SUMA(A1;A2;3;4;5;A6:A12)");
        double content = this.instance.getCellContentAsDouble("B9");
        Assert.assertEquals(83.0, content, 0.0);
    }
    @Test
    public void test12SetCellContent_AutomaticUpdateOfCellValues_DirectlyImpacted() throws Exception {
        System.out.println("setting cell content to a formula with cell references"
                + "then changing the content of one of the cells that are referenced (directly)"
                + "and checking if it the cell values automatically update");
        this.instance.editSpreadsheet("A4", "=A3+10");
        System.out.println("Original value of A4= " + this.instance.getCellContentAsDouble("A4"));
        this.instance.editSpreadsheet("A3", "=2");
        System.out.println("Manually updated value of A3= " + this.instance.getCellContentAsDouble("A3"));
        System.out.println("Automatically updated value of A4= " + this.instance.getCellContentAsDouble("A4"));
        double content = this.instance.getCellContentAsDouble("A4");
        Assert.assertEquals(12.0, content, 0.0);
    }

    @Test
    public void test13SetCellContent_AutomaticUpdateOfCellValues_IndirectlyImpacted() throws Exception {
        System.out.println("setting cell content to a formula with cell references"
                + "then changing the content of one of the cells that are referenced (indirectly)"
                + "and checking if it the cell values automatically update");
        this.instance.editSpreadsheet("A3", "=A1+A2*MAX(A8;A9)");
        System.out.println("Original value of A3= " + this.instance.getCellContentAsDouble("A3"));
        this.instance.editSpreadsheet("A4", "=A3+10");
        System.out.println("Original value of A4= " + this.instance.getCellContentAsDouble("A4"));
        this.instance.editSpreadsheet("A1", "=2+SUMA(1;8)+MIN(2;4;A7)");
        System.out.println("Manually updated value of A1= " + this.instance.getCellContentAsDouble("A1"));
        this.instance.editSpreadsheet("A8", "12");
        System.out.println("Manually updated value of A8= " + this.instance.getCellContentAsDouble("A1"));
        System.out.println("Automatically updated value of A4= " + this.instance.getCellContentAsDouble("A4"));
        double content = this.instance.getCellContentAsDouble("A4");
        Assert.assertEquals(47.0, content, 0.0);
    }
}


