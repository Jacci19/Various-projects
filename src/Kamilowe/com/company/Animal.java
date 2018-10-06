package Kamilowe.com.company;

import java.util.Objects;

public class Animal implements RepresentableByFullName, SoundEmitable {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public String getFullName() {
        return this.name;
    }

    @Override
    public void emitSound() {
        System.out.println("Wrrrrrrr");
    }
}
