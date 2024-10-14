package gadv.desafiocompras.main;

import gadv.desafiocompras.modules.CreditCard;
import gadv.desafiocompras.modules.Shopped;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escribe el limite de la tarjeta:");
        double credit = scanner.nextDouble();
        CreditCard creditCard = new CreditCard(credit);
        int menu = 1;
        String productDescription;
        double productValue;
        while (menu != 0 && creditCard.getCredit() > 0){
            System.out.println("Escriba la descripcion de la compra:");
            scanner.nextLine();
            productDescription = scanner.nextLine();
            System.out.println("Escriba el valor de la compra:");
            productValue = scanner.nextDouble();
            if(creditCard.buy(productDescription, productValue)) {
                System.out.println("Compra Realizada!");
                System.out.println("Escriba 0 para Salir o 1 para Continuar");
                menu = scanner.nextInt();
            } else {
                System.out.println("Saldo Insuficiente!");
                menu = 0;
            }
        }
        //creditCard.sortShoppingList(Comparator.comparing(Shopped::getValue));
        Collections.sort(creditCard.getList());
        System.out.println("********************");
        System.out.println("COMPRAS REALIZADAS:\n");
        for (Shopped shopped : creditCard.getList()) {
            System.out.println(shopped.getProductDescription() + " - " + shopped.getValue());
        }
        System.out.println("\n********************");
        System.out.println("\nSaldo de la tarjeta: " + creditCard.getCredit());
        scanner.close();
    }
}
