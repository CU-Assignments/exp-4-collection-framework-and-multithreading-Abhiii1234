import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Employee\n2. Update Employee\n3. Remove Employee\n4. Search Employee\n5. Display All\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    employees.add(new Employee(id, name, salary));
                    break;
                case 2:
                    System.out.print("Enter ID to update: ");
                    id = scanner.nextInt();
                    for (Employee emp : employees) {
                        if (emp.id == id) {
                            scanner.nextLine(); // consume newline
                            System.out.print("Enter New Name: ");
                            emp.name = scanner.nextLine();
                            System.out.print("Enter New Salary: ");
                            emp.salary = scanner.nextDouble();
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to remove: ");
                    id = scanner.nextInt();
                    employees.removeIf(emp -> emp.id == id);
                    break;
                case 4:
                    System.out.print("Enter ID to search: ");
                    id = scanner.nextInt();
                    for (Employee emp : employees) {
                        if (emp.id == id) {
                            System.out.println(emp);
                        }
                    }
                    break;
                case 5:
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
