### CVE

org.redline-rpm:redline:1.2.10

[не исправлено] Arbitrary file read.

### CodeQL
Public методы, куда можно передать объект типа File и позднее прочитать содержимое файла:

**class org.redline_rpm.Builder**

```public void addTrigger( final File script, final String prog, final Map< String, IntString> depends, final int flag) throws IOException```


Sink:
[code link](https://github.com/craigwblake/redline/blob/15afff553f65e3c9d0e9d904fd1e8ce46c4d1515/src/main/java/org/redline_rpm/Builder.java#L548)

### POC

В директории redline_poc представлен пример программы, которая читает содержимое произвольного файла и выводит его содержимое в консоль. Это возможно потому, что после чтения, содержимое файла попадает в поле ```triggerscripts```, а это поле имеет видимость ```protected``` в классе ```Builder``` и этот класс не помечен ```final```, поэтому мы можем в собственной реализации ```Builder``` легко получить доступ к ```triggerscripts```.


### Fix
Возможные фиксы:
- Сделать поле ```triggerscripts``` приватным.
- Сделать класс ```Builder``` ```final```
- Добавить проверку на путь файла, который передается в ```addTrigger``` (список разрешенных директорий, проверка на наличие .., на абсолютность пути).

