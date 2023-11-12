import java.util.ArrayList;
import java.util.Scanner;


class Food {
    final String name;
    final double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void describe() {
        System.out.println(name + " - $" + price);
    }
}


class Drink extends Food {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public void describe() {
        System.out.print("Напій: ");
        super.describe();
    }
}

class Dish extends Food {
    public Dish(String name, double price) {
        super(name, price);
    }

    @Override
    public void describe() {
        System.out.print("Страва: ");
        super.describe();
    }
}

class Customer {
    private String name;
    public ArrayList<Food> order;

    public Customer(String name) {
        this.name = name;
        this.order = new ArrayList<>();
    }

    public void addToOrder(Food food) {
        order.add(food);
    }

    public void addToOrder(Food[] foods) {
        for (Food food : foods) {
            order.add(food);
        }
    }

    public double calculateOrderTotal() {
        double total = 0;
        for (Food food : order) {
            total += food.getPrice();
        }
        if (getName().equalsIgnoreCase("olexandr volodymyrovych")) {
            total /= 2;
        }
        return total;
    }

    public String getName() {
        return name;
    }
}

public class CafeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Customer> customers = new ArrayList<>();

        // Запит імен клієнтів
        System.out.print("Скільки клієнтів у кафе? ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Введіть ім'я клієнта " + (i + 1) + ": ");
            String customerName = scanner.nextLine();
            Customer customer = new Customer(customerName);
            customers.add(customer);
        }

        // Відображення меню
        System.out.println("\nМеню кафе:");
        Food[] menu = {
                new Drink("Кава", 2.5),
                new Drink("Чай", 1.5),
                new Dish("Паста", 8.0),
                new Dish("Піца", 10.0),
                new Dish("Чізкейк", 12.3)
        };

        for (int i = 0; i < menu.length; i++) {
            menu[i].describe();
        }

        // Замовлення клієнтів
        for (Customer customer : customers) {
            orderFood(scanner, customer, menu);
        }

        // Підрахунок і відображення загальної вартості та доходу кафе
        double totalIncome = 0;
        for (Customer customer : customers) {
            double orderTotal = customer.calculateOrderTotal();
            totalIncome += orderTotal;
            System.out.println("\nЗамовлення для " + customer.getName() + ":");
            for (Food food : customer.order) {
                food.describe();
            }
            System.out.println("Загальна вартість: $" + orderTotal);
            if (customer.getName().equalsIgnoreCase("olexandr volodymyrovych")) {
                System.out.println("Це велика честь, що Ви завітали до нашого кафе. Для Вас діє знижка: вартість замовлення зменшується вдвічі");
            }
        }

        System.out.println("\nЗагальний дохід кафе: $" + totalIncome);
        System.out.println("Дякуємо, що завітали! Гарного дня та смачного!");
        scanner.close();
    }

    private static void orderFood(Scanner scanner, Customer customer, Food[] menu) {
        System.out.println("\nЗамовлення для " + customer.getName());
        orderFoodRecursive(scanner, customer, menu);
    }

    private static void orderFoodRecursive(Scanner scanner, Customer customer, Food[] menu) {
        System.out.print("Виберіть номер страви (або 0, щоб завершити замовлення): ");
        int choice = scanner.nextInt();
        if (choice == 0) {
            return;
        } else if (choice >= 1 && choice <= menu.length) {
            customer.addToOrder(menu[choice - 1]);
        } else {
            System.out.println("Недійсний вибір.");
        }
        orderFoodRecursive(scanner, customer, menu);
    }

}