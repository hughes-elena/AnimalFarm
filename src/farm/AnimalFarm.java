/* Класс AnimalFarm (ферма). У него одно поле — список строк farmAnimals.
Создай конструктор с этим параметром. В этом списке лежат данные по всем
животным на ферме в виде двух слов, разделенных пробелом:
Вид_животного Имя_животного. Например, DOG Жучка.
Задание1.
В классе AnimalFarm реализуй метод countedAnimals. Он должен по полю farmAnimals формировать хеш-таблицу, в которой ключ — это вид животного (Animal), а значение — количество животных этого вида на ферме.
Если какая-то строка в списке не содержит первым словом валидный вид животного, метод должен вывести в консоль фразу: Please correct string [Здесь вывести полностью ошибочную строку]. Incorrect input data.
Метод возвращает сформированную хеш-таблицу.
Задание2.
В классе AnimalFarm реализуй метод uniqueNames. Он должен возвращать коллекцию всех уникальных имён животных на ферме. Подходящий тип коллекции подбери самостоятельно.
Если в какой-то строке списка нет второго слова, метод должен вывести в консоль фразу: Please correct string [Здесь вывести полностью ошибочную строку]. Incorrect input data.
Задание3.
В классе AnimalFarm реализуй три метода для разных вариантов добавления нового животного на ферму:
по переданному виду животного и имени;
по переданному виду животного. В этом случае имя животного равно N;
по переданному имени животного. В этом случае вид животного равен NOT_DEFINED.
Задание4.
В классе AnimalFarm переопредели метод toString так, чтобы он выводил информацию по животным на ферме в виде:
Вид_животного Имя_животного
Вид_животного Имя_животного
Вид_животного Имя_животного
*/

import java.util.*; // Импортируем все классы из пакета java.util

public class AnimalFarm {
    private List<String> farmAnimals; // Одно Поле класса: список животных в формате "ВИД ИМЯ"

    /* Конструктор класса — вызывается при создании объекта AnimalFarm
    принимает список животных и сохраняет его в поле farmAnimals.  В этом списке лежат данные по всем
    животным на ферме в виде двух слов, разделенных пробелом
     */
    public AnimalFarm(List<String> farmAnimals) {
        this.farmAnimals = farmAnimals; // Присваиваем переданный список полю класса
    }

    // Метод countedAnimals() возвращает карту (Map), где ключ — это тип животного (enum Animal), а значение — количество таких животных
    public Map<Animal, Integer> countedAnimals() {
        Map<Animal, Integer> animalMap = new HashMap<>(); // Создаём пустую карту для подсчёта животных
// Перебираем всех животных в списке
        for (String farmAnimal : farmAnimals) {
            Animal animal; // Объявляем переменную типа Animal (enum)
            try {
                // Получаем тип животного из строки и приводим его к формату enum
                animal = Animal.valueOf(farmAnimal.split(" ")[0].toUpperCase());

                // Получаем текущее количество животных этого типа
                Integer currentNumber = animalMap.get(animal);
                // Если такого животного ещё нет в карте, записываем 1. Иначе увеличиваем счётчик на 1
                animalMap.put(animal, currentNumber == null ? 1 : currentNumber + 1);
            } catch (Exception e) {
                // Если формат строки неверный — выводим сообщение об ошибке
                System.out.printf("Please correct string %s. Incorrect input data. %n", farmAnimal);
            }
        }
        return animalMap; // Возвращаем итоговую карту
    }
    // Метод uniqueNames() возвращает множество (Set) уникальных имён животных
    public Set<String> uniqueNames() {
        Set<String> uniqueNames = new HashSet<>(); // Создаём пустое множество

        for (String farmAnimal : farmAnimals) {
            String name; // Имя животного
            try {
                // Разделяем строку и берём вторую часть (имя животного)
                name = farmAnimal.split(" ")[1];
                uniqueNames.add(name); // Добавляем имя в множество (дубликаты автоматически игнорируются)
            } catch (Exception e) {
                // Если ошибка в формате строки — выводим сообщение
                System.out.printf("Please correct string %s. Incorrect input data. %n", farmAnimal);
            }
        }
        return uniqueNames; // Возвращаем множество имён
    }

    // Перегруженный метод addFarmAnimal() — добавляет животное с указанным типом и именем
    public void addFarmAnimal(Animal animal, String name) {
        farmAnimals.add(animal.name() + " " + name); // Добавляем строку в формате "ТИП Имя"
    }

    // Перегруженный метод addFarmAnimal() — добавляет животное с указанным типом, но без имени (имя по умолчанию "N")
    public void addFarmAnimal(Animal animal) {
        farmAnimals.add(animal.name() + " N"); // N — имя по умолчанию
    }

    // Перегруженный метод addFarmAnimal() — добавляет животное без типа, только имя (тип NOT_DEFINED)
    public void addFarmAnimal(String name) {
        farmAnimals.add(Animal.NOT_DEFINED + " " + name); // Тип животного указывается как NOT_DEFINED
    }

    // Переопределение метода toString() — используется для красивого вывода списка животных
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(); // Создаём объект StringBuilder для сборки строки
        for (String farmAnimal : farmAnimals) {
            // Меняем пробел на двоеточие (для читаемости) и добавляем к результату
            String printFarmAnimal = farmAnimal.replace(" ", ":");
            stringBuilder.append(printFarmAnimal).append("\n");
        }

        return stringBuilder.toString(); // Возвращаем итоговую строку
    }
}
