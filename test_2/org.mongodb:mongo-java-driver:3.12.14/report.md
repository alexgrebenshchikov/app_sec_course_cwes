### CVE

Package: org.mongodb:mongo-java-driver:3.12.14

[не исправлено] CWE-27: Path Traversal: 'dir/../../filename'
Продукт использует внешние входные данные для создания пути, который должен находиться в пределах каталога с ограниченным доступом, но он не нейтрализует должным образом множественные внутренние последовательности "../", которые могут быть преобразованы в местоположение, находящееся за пределами этого каталога.

### CodeQL
Public методы, куда можно передать любой путь до файла, даже содержащий "..", и в результате по этому пути будет создан файл:

**class GridFSDBFile**

```public long writeTo(final String filename) throws IOException```

```public long writeTo(final File file) throws IOException```

Sink:

https://github.com/mongodb/mongo-java-driver/blob/99a6f32d0a7f75f43e0b2931d30479154ded9ef1/driver-legacy/src/main/com/mongodb/gridfs/GridFSDBFile.java#L67

### POC

В директории cwe27_poc представлен пример программы, которая создает файл вне директории проекта, передавая путь в аргумент публичного метода из библиотеки.

### Fix
Я бы предложил проверять предоставляемый пользователем путь до файла на наличие секций ".." и кидать исключение если таковые обнаружены. Например так:
```
public static void validateNoParentDirReferences(File outputFile) {
        String path = outputFile.getPath();

        // Split the path by either / or \
        String[] parts = path.split("[/\\\\]");

        // Check if any part is ".."
        for (String part : parts) {
            if (part.equals("..")) {
                throw new IllegalArgumentException("Path contains illegal parent directory references: " + path);
            }
        }
    }
```