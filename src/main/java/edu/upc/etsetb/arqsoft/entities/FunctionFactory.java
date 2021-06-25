package edu.upc.etsetb.arqsoft.entities;

public class FunctionFactory {
    public static Function getInstance(String s) throws ContentException {

      try {
          if (s != null) {
              s = s.toUpperCase();
              Class functionClass = Function.functionClassMap.get(s);
              
              if (functionClass == null) {
                  throw new ContentException();
              }
              return (Function) functionClass.getConstructor().newInstance();
          }
      } catch (Exception e) {
          throw new ContentException("Function not valid");
      }
      return null;
  }
}
