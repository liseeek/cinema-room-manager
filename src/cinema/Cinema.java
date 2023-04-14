package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        int totalIncome = 0;
        if (rows * seatsInRow <= 60) {
            totalIncome = rows * seatsInRow * 10;
        } else {
            int frontHalfRows = rows / 2;
            int frontHalfSeats = frontHalfRows * seatsInRow;
            int backHalfSeats = rows * seatsInRow - frontHalfSeats;
            totalIncome = frontHalfSeats * 10 + backHalfSeats * 8;
        }

        char[][] cinema = new char[rows][seatsInRow];
        int purchasedTickets = 0;
        int currentIncome = 0;

        // Intialization of cinema array
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }


        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    System.out.println("Cinema:");
                    System.out.print("  ");
                    for (int j = 1; j <= seatsInRow; j++) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                    for (int i = 0; i < cinema.length; i++) {
                        for (int j = 0; j < cinema[i].length; j++) {
                            if (j == 0) {
                                System.out.print((i + 1) + " ");
                            }
                            System.out.print(cinema[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    break;
                case 2:
                    while (true) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();

                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();

                        if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seatsInRow) {
                            System.out.println("Wrong input!");
                        } else if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            int totalSeats = rows * seatsInRow;
                            int ticketPrice;
                            purchasedTickets++;

                            if (totalSeats <= 60) {
                                ticketPrice = 10;
                            } else {
                                int frontHalfRows = rows / 2;
                                if (rowNumber <= frontHalfRows) {
                                    ticketPrice = 10;
                                } else {
                                    ticketPrice = 8;
                                }
                            }
                            currentIncome += ticketPrice;
                            System.out.println("Ticket price: $" + ticketPrice);

                            cinema[rowNumber - 1][seatNumber - 1] = 'B';
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Number of purchased tickets: " + purchasedTickets);
                    double percentage = (double) purchasedTickets / (double) (rows * seatsInRow) * 100;
                    System.out.printf("Percentage: %.2f%%", percentage);
                    System.out.println();
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;
                case 0:
                    return;

                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }
}
