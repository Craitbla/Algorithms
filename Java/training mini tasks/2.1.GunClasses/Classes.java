import java.util.Scanner;

//Мы можем наследовать только от одного класса, в отличие, например, от языка С++, где имеется множественное наследование.
//Enum class — это настоящий класс со всеми вытекающими из этого возможностями, но от него невозможно наследоваться.

public class Classes { // loader
    public static void main(String[] args) {
        Player p1 = new Player();
        p1.weaponChoise();
    }
}

interface Weapon {
    default void shot() {
        System.out.println("У оружия не прописали звук");
    }
}

class Pistol implements Weapon {
    public void shot() {
        System.out.println("Пиу");
    }
}

class MachineGun implements Weapon {
    public void shot() {
        System.out.println("Ра-та-та-та");
    }
}

class RPG implements Weapon {
    public void shot() {
        System.out.println("Буууууум");
    }
}

class Slingshot implements Weapon {
    public void shot() {
        System.out.println("Птююю");
    }
}

class WaterPistol implements Weapon {
    public void shot() {
        System.out.println("Вшшшшшшшшш");
    }
}

class Player {
    public enum WeaponType { // по идее чисто для weaponChoise()
        PISTOL("Пистолет"),
        MACHINEGUN("Автомат"),
        RPG("РПГ"),
        SLINGSHOT("Рогатка"),
        WATERPISTOL("Водный пистолет");

        private final String weaponName_;

        WeaponType(String weaponName) {
            weaponName_ = weaponName;
        }

        @Override
        public String toString() {
            return weaponName_;
        }
    }

    public void weaponChoise() {
        Scanner scanner = new Scanner(System.in);
        int inputInt = 0;

        for (int i = 0; i < WeaponType.values().length; i++) { // WeaponType.values() делает массив
            System.out.println((i + 1) + " " + WeaponType.values()[i]);
        }

        inputInt = scanner.nextInt();
        if (inputInt > 0 && inputInt <= WeaponType.values().length) {
            WeaponType weapon = WeaponType.values()[inputInt - 1];
            System.out.println("Вы выбрали: " + weapon);
            shot();
        } else {
            System.out.println("Неверный выбор!");
        }

        scanner.close();

    }

    public void shot() {
        weapon.shot();
    }

    private Weapon weapon;
}
