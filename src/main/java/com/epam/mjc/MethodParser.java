package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> list = new ArrayList<>();


        StringTokenizer st1 = new StringTokenizer(signatureString, "()");

        list.add(st1.nextToken());
        list.add(st1.nextToken());


        StringTokenizer st2 = new StringTokenizer(list.get(0), " ");

        String returnType = st2.nextToken();

        String nameMethod = st2.nextToken();

        StringTokenizer st3 = new StringTokenizer(list.get(1), " ,");

        List<String> list2 = new ArrayList<>();

        while (st3.hasMoreTokens()){
            list2.add(st3.nextToken());
        }
        List<MethodSignature.Argument> list3 = new ArrayList<>();
        for(int i = 0;i<list2.size();i+=2){
            list3.add(new MethodSignature.Argument(list2.get(i),list2.get(i+1)));
        }

        MethodSignature methodSignature = new MethodSignature(nameMethod,list3);
        methodSignature.setAccessModifier(returnType);

        return methodSignature;
    }
}
