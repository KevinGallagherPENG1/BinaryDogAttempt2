import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //binary files:
        // .bin, .dat, .exe
        /*
        Files that end in .dat are binary data, understood by machines, fast and efficient
        With binary:
            Reading: ObjectInputStream()
            Writing: ObjectOutputStream()

         */
        Scanner keyboard = new Scanner(System.in);
        String name, breed;
        int age, count = 0;
        Dog[] dogPound = new Dog[10];



        //read from binary file Dogs.dat
        File binaryFile = new File("Dogs.dat");
        System.out.println("Previously saved Dogs from binary file:" );
        //check to see if file exists, AND if it has non zero size
        //1L is one Long, is 64 bits, not 32 bits
        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            //read from binary file if it exists and has a length greater than 1L
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                //read the entire array into dogPound
                //readObject returns Object datatype
                //dog[] = Object (typecast?)
                //           vv this makes sure the data source is the same as the data destination
                dogPound = (Dog[]) fileReader.readObject();
                //loop through array until we hit null and print objects
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);

                fileReader.close(); //make sure to close the file before the reader reads it!
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else
            System.out.println("[none, please enter new Dog Data]");




       do {
           System.out.print("\nEnter Dog's name (or \"quit\" to exit): ");
           name = keyboard.nextLine();
           if (name.equalsIgnoreCase("quit"))
               break;

           System.out.print("Enter Dog's breed: ");
           breed = keyboard.nextLine();
           System.out.print("Enter Dog's age: ");
           age = keyboard.nextInt();

           /*
           Dog d = new Dog(name, breed, age);
           dogPound[count] = d;
           count++;
           ^^ this can all be consolidated below:
            */
           dogPound[count++] = new Dog(name, breed, age);


           keyboard.nextLine(); //from reading number (int/double) to string, need an extra keyboard.nextLine();

       }while(true);

       //after user enters quit, write the dogPound array to the binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close(); //make sure to close!
        } catch (IOException e) {
            System.out.println("Error! " + e.getMessage());
        }


    }
}
