import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Classes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player p1 = new Player();

        p1.weaponChoise(scanner);
        p1.shot();

        Weapon newW = new WaterPistol();
        p1.addWeapon(newW);
        p1.weaponChoise(scanner);
        p1.shot();
        scanner.close();
    }
}

interface Weapon {
    default void shot() {
        System.out.println("У оружия не прописали звук");
    }

    String getName();
}

class Pistol implements Weapon {
    @Override
    public void shot() {
        System.out.println("Пиу");
    }

    @Override
    public String getName() {
        return "Пистолет";
    }
}

class MachineGun implements Weapon {
    @Override
    public void shot() {
        System.out.println("Ра-та-та-та");
    }

    @Override
    public String getName() {
        return "Автомат";
    }
}

class RPG implements Weapon {
    @Override
    public void shot() {
        System.out.println("Буууууум");
    }

    @Override
    public String getName() {
        return "РПГ";
    }
}

class Slingshot implements Weapon {
    @Override
    public void shot() {
        System.out.println("Птююю");
    }

    @Override
    public String getName() {
        return "Рогатка";
    }
}

class WaterPistol implements Weapon {
    @Override
    public void shot() {
        System.out.println("Вшшшшшшшшш");
    }

    @Override
    public String getName() {
        return "Водный пистолет";
    }
}

class Player {
    public Player() {
        weapons.add(new Pistol());
        weapons.add(new MachineGun());
        weapons.add(new RPG());
        weapons.add(new Slingshot());
    }

    private final List<Weapon> weapons = new ArrayList<>();

    private Weapon curWeapon;

    public void shot() {
        if (curWeapon == null) {
            System.out.println("Оружие не выбрано!");
            return;
        }
        curWeapon.shot();
    }

    public void addWeapon(Weapon newWeapon) {
        weapons.add(newWeapon);
    }

    public void setWeapon(Weapon weapon) {
        curWeapon = weapon;
    }

    public void printWeapons() {
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + " " + weapons.get(i).getName());
        }
    }

    public void weaponChoise(Scanner scanner) {
        printWeapons();
        int inputInt = 0;

        try {
            inputInt = scanner.nextInt();
            setWeapon(weapons.get(inputInt - 1));
            System.out.println("Вы выбрали: " + curWeapon.getName());
        } catch (Exception e) {
            System.out.println("Неверный выбор!");
        }

    }
}
