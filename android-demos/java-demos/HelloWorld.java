import java.util.*;

public class HelloWorld {
    public static void main(String[] args) {
        for (String string : args) {

        }

        // int[] age = { (int) (Math.random() * 25), (int) (Math.random() * 25) };

        // if (age[0] == age[1])
        // System.out.println("equal");
        // else if (age[0] < age[1])
        // System.out.println("first smaller the then second");
        // else
        // System.out.println("first bigger the then second");

        // Map map = new HashMap();

        // map.put("father", "rob");
        // map.put("mother", "kristen");

        // map.remove("father");

        // System.out.println(map.size());
        // List list = new ArrayList();
        // list.add("Norway");
        // list.add("Israel");
        // list.add("Canada");

        // System.out.println(list.toString());

        // list.remove(1);

        // list.add("Israel");

        // System.out.println(list.size());

        // int x = 1;

        // while (x <= 10) {
        // if (x % 2 == 0)
        // System.out.println(x);
        // x++;
        // }

        // for (int i = 0; i <= 5; i++)
        // System.out.println(i * 2);

        // int x = 5;
        // while (x != 0) {
        // System.out.println(x * 2);
        // x--;
        // }

        // int x = 1;

        // for (int i = 0; i < familyMembers.length; i++) {
        // System.out.println(familyMembers[i]);
        // }

        // String[] familyMembers = { "father", "mother", "son", "brother " };

        // List<String> arrayList = new ArrayList<String>();
        // for (String familyMember : familyMembers)
        // arrayList.add(familyMember);

        // for (String name : arrayList)
        // System.out.println(name);

        // System.out.println(arrayList.toString());

        class Number {
            int number;

            public boolean isTriangular() {
                int x = 1;

                int triangularNumber = 1;

                while (triangularNumber < number) {
                    x++;
                    triangularNumber += x;
                }

                if (triangularNumber == number)
                    return true;
                else
                    return false;
            }

            public boolean isSquare() {
                double squareRoot = Math.sqrt(this.number);
                return squareRoot == Math.floor(squareRoot);
            }
        }
        // Number myNumber = new Number();
        // myNumber.number = 5;
        // System.out.println(myNumber.isTriangular());

        //
        // How to check if an integer is a perfect square [duplicate]
        // int input = 4;
        // int x = (int) Math.sqrt(input);
        // if (Math.pow(x, 2) == input) {
        // }
        // Do stuff
    }
}