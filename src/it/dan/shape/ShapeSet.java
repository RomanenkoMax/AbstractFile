package it.dan.shape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class ShapeSet<T extends AbstractShape> {

    private Set<T> set;

    public ShapeSet(Set<T> set) {

        this.set = set;

    }

    public void writeSetToXML(String path){
        for (T shape : set) {

            File file = new File(path);
            String context = getContent(shape);

            try (PrintWriter outputStream = new PrintWriter(new FileWriter(file, true))){

                outputStream.println(context);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getContent(T shape) {

        Class cl = shape.getClass();
        String modifiers = Modifier.toString(cl.getModifiers());

        StringBuilder context = new StringBuilder("<?xml version=\"1.0\"?>\n<!DOCTYPE body [\n<!ELEMENT " + modifiers + " " + cl.getName() + " (constructor*, method*, field*)>\n");
        String s = "<!ELEMENT constructor (#PCDATA)>\n<!ELEMENT method (#PCDATA)>\n<!ELEMENT field (#PCDATA)>\n<!ATTLIST constructor";
        context.append(s);

        Constructor[] constructors = cl.getConstructors();
        for (Constructor c : constructors) {
            context.append("\n      ");
            String name = c.getName();
            String modifiersConstructor = Modifier.toString(c.getModifiers());
            if (modifiersConstructor.length() > 0){
                context.append(modifiersConstructor + " ");
            }
            context.append(name + "(");
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0){
                    context.append(", ");
                }
                context.append(paramTypes[i].getName());
            }
            context.append(");");
        }

        context.append(">\n<!ATTLIST method");
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            context.append("\n      ");
            Class retType = m.getReturnType();
            String name = m.getName();
            String modifiersMethod = Modifier.toString(m.getModifiers());
            if (modifiersMethod.length() > 0){
                context.append(modifiersMethod + " ");
            }
            context.append(retType.getName() + " " + name + "(");
            Class[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0){
                    context.append(", ");
                }
                context.append(paramTypes[i].getName());
            }
            context.append(");");
        }

        context.append(">\n<!ATTLIST field");
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            context.append("\n      ");
            Class type = f.getType();
            String name = f.getName();
            String modifiersField = Modifier.toString(f.getModifiers());
            if (modifiersField.length() > 0){
                context.append(modifiersField + " ");
            }
            context.append(type.getName() + " " + name + ";");

        }

        context.append("\n]>\n");

        context.append("<body>\n<" + cl.getName() + ">\n<method> result = " + shape.getArea() + " </method>\n");
        for (Field f : fields){
            try {
                context.append("<field>" + f.getName() + " = " + f.get(shape) + " </field>\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        context.append("</" + cl.getName() + ">\n</body>\n\n");


        return context.toString();
    }


}
