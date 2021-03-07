import java.util.Scanner;
public class KeeperPet{
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args){
        Keeper user=new Keeper(args[0],args[1]);
        Pet pets=new Pet(args[0],args[1],args[2]);
        pets.display();
        user.display();
        pets.speak();
    }
}