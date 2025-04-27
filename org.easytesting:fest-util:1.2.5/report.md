### CVE

org.easytesting:fest-util:1.2.5

[не исправлено] Arbitrary directory create.

### CodeQL
Public методы, куда можно передать путь до директории в виде строки и эта директория будет создана:

**class org.fest.util.Files**

```public static File newFolder(String path)```


Sink:
[code link](https://github.com/alexruiz/fest-util/blob/master/src/main/java/org/fest/util/Files.java#L178)

### POC

В директории easytesting_poc представлен пример программы, которая создает произвольную директорию, путь до которой передается в публичный метод библиотеки.


### Fix
Возможные фиксы:
- Добавить проверку на путь файла, который передается в ```newFolder``` (список разрешенных директорий, проверка на наличие .., на абсолютность пути).

