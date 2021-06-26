package edu.upc.etsetb.arqsoft.entities;

import java.util.List;

public class Formula extends Content{
    private List<ComponentFormula> formulaComponentList;
    private MyNumber result;
    private String text;

    public Formula(String text, List<ComponentFormula> formulaComponentList, Double result){
        this.text = text;
        this.formulaComponentList = formulaComponentList;
        this.result = new MyNumber(0.0);
    }

    public MyNumber getResult(){
        return this.result;
    }

    public void setResult(MyNumber number){
        this.result = number;
    }

    public List<ComponentFormula> getFormulaComponents() {
        return this.formulaComponentList;
    }

}
