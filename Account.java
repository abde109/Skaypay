import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
      
      private final List<Transaction> transactions = new ArrayList<>();
      private int balance = 0;
      
      private record Transaction(String date, int amount, int balance) {
      }
      
      @Override
      public void deposit(int amount) {
             if (amount <= 0) {
                  throw new IllegalArgumentException("Deposit amount must be positive.");
            }
            balance += amount;
            transactions.add(new Transaction(getCurrentDate(), amount, balance));
      }

      @Override
      public void withdraw(int amount) {
            if (amount <= 0) {
                  throw new IllegalArgumentException("Withdrawal amount must be positive.");
            }
            balance -= amount;
            transactions.add(new Transaction(getCurrentDate(), -amount, balance));
      }

      @Override
      public void printStatement() {
            System.out.println("Date       || Amount || Balance");
            
            for (int i = transactions.size() - 1; i >= 0; i--) {
                  Transaction t = transactions.get(i);
                  System.out.printf("%s || %d     || %d%n", t.date(), t.amount(), t.balance());
            }
      }
      
     private String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
      
}
