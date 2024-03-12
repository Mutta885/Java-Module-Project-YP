
import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;


public class Main {

    static class Item {
        String name;
        double price;
        Item (String name, double price){
            this.name = name;
            this.price = price;
        }
    }

    static class Check {
        Scanner scanner = new Scanner(System.in);
        int numberOfPersons = 0;
        double total = 0;
        ArrayList<Item> itemList = new ArrayList<>();

        void printFrame(){
            System.out.println("********************************************");
            System.out.println("*            Калькулятор счёта             *");
            System.out.println("********************************************");
        }
        void setNumberOfPersons(){
            while (true) {
                System.out.println("* На сколько человек разделить счёт?");
                System.out.print("* ");
                if (scanner.hasNextInt()) {
                    numberOfPersons = scanner.nextInt();
                    if (numberOfPersons > 1) {
                        break;
                    }
                }
                else {
                    scanner.next();
                }
                System.out.print("* ");
                System.out.println("* Ошибка ввода! Введите целое число больше 1");
                System.out.print("* ");
            }
        }

        void fillIn(){
            int position = 1;
            String positionName;   // = "";
            double positionPrice;  // = 0;
            while (true){
                System.out.println("********************************************");
                System.out.println("* Введите название товара " + position + " позиции счёта:");
                System.out.print("* ");
                positionName = scanner.next();

                while (true){
                    System.out.println("* Введите стоимость товара '" + positionName + "': ");
                    System.out.print("* ");
                    if (scanner.hasNextDouble()){
                        positionPrice = scanner.nextDouble();
                        if (positionPrice >= 0){
                            break;
                        }
                    }
                    else {
                        scanner.next();
                    }
                    System.out.println("*");
                    System.out.println("* Ошибка ввода! Введите неотрицательную сумму товара");
                    System.out.println("* в формате: [рубли],[копейки]");
                    System.out.println("*");
                }

                Item item = new Item(positionName, positionPrice);
                this.itemList.add(item);
                this.total += positionPrice;
                position++;
                System.out.println("* Товар '" + positionName + "' успешно добавлен.");
                System.out.println("* Вы хотите добавить еще один товар?");
                System.out.println("* Введите любой символ чтобы продолжить ввод товаров или 'Завершить' для расчёта суммы");
                System.out.print("* ");
                if (scanner.next().equalsIgnoreCase("Завершить")){
                    break;
                }

            }

        }

        double calculate(){
            if (numberOfPersons > 1) {
                return (this.total / numberOfPersons);
            }
            else return -1;
        }

        void printResult(){
            System.out.println();
            System.out.println("********************************************");
            System.out.println("*          Добавленные товары:             *");
            System.out.println("********************************************");
            Formatter formatter = new Formatter();
            for (Item item : itemList){
                System.out.print("* - " + item.name);
                System.out.println(" по цене " + formatter.format(item.price));
            }
            System.out.println("********************************************");
            System.out.println("*            Общая сумма счёта:            *");
            System.out.println("* " + formatter.format(this.total));
            System.out.println("********************************************");
            System.out.println("* Каждый из " + this.numberOfPersons + " человек должен заплатить:");
            System.out.println("* " + formatter.format(this.calculate()));
            System.out.println("********************************************");
            System.out.println("*            Программа завершена.          *");

        }

    }

    static class Formatter {

        String format(double rub){

            String s = Double.toString(Math.floor(rub));
            boolean numberteen = ((s.length() > 3) && (s.charAt(s.length()-4) == '1')); //11, 12, 13, 14, 111, 112, ...
            if (s.length() > 2){
                switch (s.charAt(s.length()-3)){
                    case '0':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        return (String.format("%.2f рублей", rub));

                    case '1':
                        if (numberteen){
                            return (String.format("%.2f рублей", rub));
                        }
                        else {
                            return (String.format("%.2f рубль", rub));
                        }

                    case '2':
                    case '3':
                    case '4':
                        if (numberteen){
                            return (String.format("%.2f рублей", rub));
                        }
                        else {
                            return (String.format("%.2f рубля", rub));
                        }

                }

            }
            return ("Ошибка вывода");
        }

    }

    public static void main(String[] args) {

        Check check = new Check();
        check.printFrame();
        check.setNumberOfPersons();
        check.fillIn();
        check.printResult();

    }

}