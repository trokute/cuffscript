this is my ground for my test of oop in cs. heres little snippet i wrote. im also working on a vscode extension for syntax color and others.

```
set class Animal do:
    set function init(name, sound) do:
        change self.name to name
        change self.sound to sound
    end

    set function speak() do:
        print(f"{self.name} says {self.sound}")
    end

    set returnable function describe() do:
        return f"{self.name} is an animal"
    end
endclass

set class Dog extends Animal do:
    set function init(name) do:
        change self.name to name
        change self.sound to "Woof"
    end

    set function speak() do:
        super.speak()
        print(f"{self.name} wags its tail")
    end

    set returnable function describe() do:
        return f"{self.name} is a good dog"
    end
endclass

set Animal generic to Animal("Creature", "...")
set Dog rex to Dog("Rex")

generic.speak()
rex.speak()

print(generic.describe())
print(rex.describe())

set list animals to [generic, rex, Dog("Buddy")]
loop repeat i to 1 ~ 3 do:
    print(animals[i].describe())
end

print(rex)
```
