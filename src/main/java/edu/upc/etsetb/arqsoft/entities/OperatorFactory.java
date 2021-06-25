package edu.upc.etsetb.arqsoft.entities;

public class OperatorFactory {

    public static Operator getInstance(String s) throws ContentException {

        try {
            if (s != null) {
                Class operatorClass = Operator.operatorsClassMap.get(Operator.operatorStringMap.get(s));

                if (operatorClass == null) {
                    throw new ContentException();
                }
                return (Operator) operatorClass.getConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new ContentException();
        }
        return null;
    }
}
