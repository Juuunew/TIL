package day02.generic;

public class GenericPrinterTest {
    public static void main(String[] args) {

        Powder powder = new Powder();

        GenericPrinter<Powder> powderPrinter = new GenericPrinter<>();
        powderPrinter.setMaterial(powder);

        Powder p = powderPrinter.getMaterial();
        System.out.println("p.toString() = " + p.toString());

        GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<>();
        plasticPrinter.setMaterial(new Plastic());

        System.out.println("plasticPrinter = " + plasticPrinter);
    }
}
