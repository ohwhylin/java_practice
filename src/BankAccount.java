import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String ownerName;
    private int balance;
    private LocalDateTime openingDate;
    private boolean isBlocked;
    private String number;

    public BankAccount (String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isBlocked = false;
        this.number = generateAccountNumber();
    }

    public boolean deposit(int amount){
        if (amount <= 0 || isBlocked){
            return false;
        }

        balance += amount;
        return true;
    }

    public boolean withdraw(int amount){
        if (amount <= 0 || amount > balance || isBlocked) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAccount, int amount){
        if (otherAccount == null || amount <= 0 || isBlocked || otherAccount.isBlocked) {
            return false;
        }

        if (amount > balance){
            return false;
        }

        if (this.withdraw(amount)){
            return otherAccount.deposit(amount);
        }

        return false;
    }

    @Override
    public String toString(){
        return String.format(
                "Информация о счёте: номер: '%s', владелец: '%s', баланс: '%s', открыт: '%s', заблокирован: '%s'",
                number, ownerName, balance, openingDate, isBlocked
        );
    }

    private String generateAccountNumber(){
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();

        for (int i = 0; i < 8; i++){
            accountNumber.append(random.nextInt(10));
        }

        return accountNumber.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BankAccount that = (BankAccount) obj;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public String getOwnerName(){
        return ownerName;
    }

    public int getBalance(){
        return balance;
    }

    public LocalDateTime getOpeningDate(){
        return openingDate;
    }

    public boolean isBlocked(){
        return isBlocked;
    }

    public String getNumber(){
        return number;
    }

    public void setBlocked(boolean blocked){
        isBlocked = blocked;
    }
}
