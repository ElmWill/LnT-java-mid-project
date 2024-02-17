package Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import Data.Employee;
import java.util.Collections;


public class Main {
	ArrayList<Employee> EmployeeData = new ArrayList<Employee>();
	Scanner scan = new Scanner(System.in);
	private final String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final int num = 26;
	Random random = new Random();
	private final int PAY_MANAGER = 8000000;
	private final int PAY_SUPERVISOR = 6000000;
	private final int PAY_ADMIN = 4000000;
	int countManager = 0;
	int countSupervisor = 0;
	int countAdmin = 0;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		while(true) {
			int choice = Menu(scan);
			scan.nextLine();
			switch(choice) {
				case 1:
					InputData();
					Return();
					break;
				case 2:
					ViewData();
					Return();
					break;
				case 3:
					UpdateData();
					Return();
					break;
				case 4:
					DeleteData();
					Return();
					break;
				case 5:
					System.out.println("Thank you for using this program");
					System.exit(0);
					break;
				default:
					System.out.println("that is not an option");
					break;
			}
			
		}
	}
	
	public void Return() {
		String console = null;
		do{
			System.out.println("ENTER to return");
			console = scan.nextLine();
		}while(!console.equals(""));
		
		for(int i = 0; i < 50; i++) {
			System.out.println("");
		}
		
	}
	
	public String GenerateID() {
		StringBuilder id = new StringBuilder();
		for(int i = 0; i < 7; i++) {
			if(i < 2) id.append(Alphabet.charAt(random.nextInt(num)));
			else if(i == 2) id.append("-");
			else if(i > 2) id.append(random.nextInt(10));
		}
		return id.toString();
	}
	
	public void InputData() {
		String name;
		String gender;
		String rank;
		double pay = 0;
		String id = GenerateID();
		
		System.out.print("Enter Employee Name [>= 3]: ");
		name = scan.nextLine();
		
		System.out.print("Enter the Gender [Man | Woman] (Case Sensitive): ");
		gender = scan.nextLine();
		if(!(gender.equals("Man") || gender.equals("Woman"))) return;
		
		System.out.print("Enter the Rank [Manager | Supervisor | Admin] (Case Sensitive): ");
		rank = scan.nextLine();
		if(rank.equals("Manager")) {
			countManager += 1;
			pay = PAY_MANAGER;
		}
		else if(rank.equals("Supervisor")) {
			countSupervisor += 1;
			pay = PAY_SUPERVISOR;
		}
		else if(rank.equals("Admin")) {
			countAdmin += 1;
			pay = PAY_ADMIN;
		}
		else return;
		
		System.out.println("Succsessfully Added Employee With the Id " + id);
		Employee employee = new Employee(name, gender, rank, id, pay);
		EmployeeData.add(employee);
		
		if((countManager - 1) % 3 == 0 && countManager - 1 != 0 && rank.equals("Manager")) {
			int counter = 1;
			System.out.print("a 10% bonus has been added to the employee with the id");
			for(Employee employees: EmployeeData) {
				if(counter > (countManager - 1)) break;
				if(employees.getRank().equals("Manager")) {
					employees.setPay(employees.getPay() * 1.10);
					if(counter == 1) System.out.print(" " + employees.getId());
					else if(counter > 1) System.out.print(", " + employees.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
		else if((countSupervisor - 1) % 3 == 0 && countSupervisor - 1 != 0 && rank.equals("Supervisor")) {
			int counter = 1;
			System.out.print("a 7.5% bonus has been added to the employee with the id");
			for(Employee employees: EmployeeData) {
				if(counter > (countSupervisor - 1)) break;
				if(employees.getRank().equals("Supervisor")) {
					employees.setPay(employees.getPay() * 1.075);
					if(counter == 1) System.out.print(" " + employees.getId());
					else if(counter > 1) System.out.print(", " + employees.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
		else if((countAdmin - 1) % 3 == 0 && countAdmin -1 != 0 && rank.equals("Admin")) {
			int counter = 1;
			System.out.print("a 5% bonus has been added to the employee with the id");
			for(Employee employees: EmployeeData) {
				if(counter > (countAdmin - 1)) break;
				if(employees.getRank().equals("Admin")) {
					employees.setPay(employees.getPay() * 1.05);
					if(counter == 1) System.out.print(" " + employees.getId());
					else if(counter > 1) System.out.print(", " + employees.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
	}
	
	//to sort by name in ascending order
	public void BubbleSort(ArrayList<Employee> EmployeeData) {
		int n = EmployeeData.size();
		for(int i = 1; i < n; i++) {
			for(int j = i; j < n; j++) {
				Employee employee1 = EmployeeData.get(j-1);
				Employee employee2= EmployeeData.get(j);
				if(employee1.getName().compareTo(employee2.getName()) > 0) {
					Collections.swap(EmployeeData, j-1, j);
				}
			}
		}
	}
	//function to view the data
	public void ViewData() {
		BubbleSort(EmployeeData);
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		System.out.println("|No  |Employee id      |Employee Name                 |Gender         |Rank           |Pay            |");
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		int count = EmployeeData.size();
		for(int i = 1; i <= count; i++) {
			Employee employees = EmployeeData.get(i-1);
			System.out.printf("|%4d|%17s|%30s|%15s|%15s|%15d|%n",
			i, employees.getId(), employees.getName(), employees.getGender(), employees.getRank(), (int)employees.getPay());
		}
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
	}
	//function to update the data
	public void UpdateData() {
		ViewData();
		int num;
		String name;
		String gender;
		String rank;
		double pay = 0;
		String id = null;
		
		System.out.println("Enter the number of the Employee you want to update: ");
		num = scan.nextInt();
		Employee employees = EmployeeData.get(num-1);
		id = employees.getId();
		scan.nextLine();
		
		System.out.print("Enter Employee Name [>= 3]: ");
		name = scan.nextLine();
		if(name.equals("0")) name = employees.getName();
		
		System.out.print("Enter the Gender [Man | Woman] (Case Sensitive): ");
		gender = scan.nextLine();
		if(gender.equals("0")) gender = employees.getGender();
		else if(!gender.equals("Man") || !gender.equals("Woman")) return;
		
		System.out.print("Enter the Rank [Manager | Supervisor | Admin] (Case Sensitive): ");
		rank = scan.nextLine();
		if(rank.equals("0")) {
			rank = employees.getRank();
			if(employees.getRank().equals("Manager")) {
				pay = PAY_MANAGER;
			}
			else if(employees.getRank().equals("Supervisor")) {
				pay = PAY_SUPERVISOR;
			}
			else if(employees.getRank().equals("Admin")) {
				pay = PAY_ADMIN;
			}
		}
		else if(rank.equals("Supervisor") && employees.getRank().equals("Manager")) {
			countSupervisor += 1;
			countManager -= 1;
			pay = PAY_SUPERVISOR;
		}
		else if(rank.equals("Admin") && employees.getRank().equals("Manager")) {
			countAdmin += 1;
			countManager -= 1;
			pay = PAY_ADMIN;
		}
		else if(rank.equals("Manager") && employees.getRank().equals("Supervisor")) {
			countManager += 1;
			countSupervisor -= 1;
			pay = PAY_MANAGER;
		}
		else if(rank.equals("Admin") && employees.getRank().equals("Supervisor")) {
			countAdmin += 1;
			countSupervisor -= 1;
			pay = PAY_ADMIN;
		}
		else if(rank.equals("Manager") && employees.getRank().equals("Admin")) {
			countManager += 1;
			countAdmin -= 1;
			pay = PAY_MANAGER;
		}
		else if(rank.equals("Supervisor") && employees.getRank().equals("Admin")) {
			countSupervisor += 1;
			countAdmin -= 1;
			pay = PAY_SUPERVISOR;
		}
		else return;
		
		if((countManager - 1) % 3 == 0 && countManager - 1 != 0 && rank.equals("Manager")) {
			int counter = 1;
			System.out.print("a 10% bonus has been added to the employee with the id");
			for(Employee employeess: EmployeeData) {
				if(counter > (countManager - 1)) break;
				if(employeess.getRank().equals("Manager")) {
					employeess.setPay(employeess.getPay() * 1.10);
					if(counter == 1) System.out.print(" " + employeess.getId());
					else if(counter > 1) System.out.print(", " + employeess.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
		else if((countSupervisor - 1) % 3 == 0 && countSupervisor - 1 != 0 && rank.equals("Supervisor")) {
			int counter = 1;
			System.out.print("a 7.5% bonus has been added to the employee with the id");
			for(Employee employeess: EmployeeData) {
				if(counter > (countSupervisor - 1)) break;
				if(employeess.getRank().equals("Supervisor")) {
					employeess.setPay(employeess.getPay() * 1.075);
					if(counter == 1) System.out.print(" " + employeess.getId());
					else if(counter > 1) System.out.print(", " + employeess.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
		else if((countAdmin - 1) % 3 == 0 && countAdmin - 1 != 0 && rank.equals("Admin")) {
			int counter = 1;
			System.out.print("a 5% bonus has been added to the employee with the id");
			for(Employee employeess: EmployeeData) {
				if(counter > (countAdmin - 1)) break;
				if(employeess.getRank().equals("Admin")) {
					employeess.setPay(employeess.getPay() * 1.05);
					if(counter == 1) System.out.print(" " + employeess.getId());
					else if(counter > 1) System.out.print(", " + employeess.getId());
					counter += 1;
				}
			}
			System.out.println("");
		}
		
		System.out.println("Successfully Updated Employee With the Id " + id);
			
		Employee newEmployee = new Employee(name, gender, rank, id, pay);
		EmployeeData.set(num-1, newEmployee);
	}
	
	public void DeleteData() {
		ViewData();
		int num;
		
		System.out.println("Enter the Number of the Employee You Want to Delete: ");
		num = scan.nextInt();
		scan.nextLine();
		EmployeeData.remove(num-1);
		System.out.println("Successfully Deleted");
	}
	
	public int Menu(Scanner scan) {
		System.out.println("Welcome to PT ChipiChapa");
		System.out.println("What do you want to do?");
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit program");
		return scan.nextInt();
	}
}
