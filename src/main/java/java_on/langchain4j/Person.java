package java_on.langchain4j;

import java.util.List;

public class Person {
    String name;
    String surname;
    List<String> personalityAttributes;
    List<String> appearanceAttributes;

    @Override
    public String toString() {
        return "Person{" + "\n" +
                "\t name='" + name + '\'' + "\n" +
                "\t surname='" + surname + '\'' + "\n" +
                "\t personalityDescription=" + personalityAttributes + "\n" +
                "\t appearanceDescription=" + appearanceAttributes + "\n" +
                '}';
    }
}

