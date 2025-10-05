public class Main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("Зинаида Зинаидова");
        BankAccount account2 = new BankAccount("Инга Ингова");

        account1.deposit(1000);
        System.out.println(account1);

        account1.withdraw(500);
        System.out.println(account1);

        account1.transfer(account2, 300);
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("Счета равны? " + account1.equals(account2));
        System.out.println("Счета равны? " + account1.equals(account1));
    }
}