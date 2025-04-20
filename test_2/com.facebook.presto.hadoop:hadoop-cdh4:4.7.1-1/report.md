### CVE

Package: com.facebook.presto.hadoop:hadoop-cdh4:4.7.1-1

[не исправлено] CWE-27: Path Traversal: 'dir/../../filename'
Продукт использует внешние входные данные для создания пути, который должен находиться в пределах каталога с ограниченным доступом, но он не нейтрализует должным образом множественные внутренние последовательности "../", которые могут быть преобразованы в местоположение, находящееся за пределами этого каталога.

### CodeQL
Public методы, куда можно передать любой путь до файла, даже содержащий "..", и в результате по этому пути будет создан файл, при этом ещe будет создана родительская директория файла, если она не существует:

**Конструкторы класса DownloadPackage**

```public DownloadPackage(S3Object object, File outputFile)```

```public DownloadPackage(S3Object object, File outputFile, boolean isUnzipping, EncryptionUtil encryptionUtil)```

```public DownloadPackage(String signedUrl, File outputFile, boolean isUnzipping, EncryptionUtil encryptionUtil)```

Sink:

https://github.com/dougm/jets3t/blob/master/src/org/jets3t/service/multithread/DownloadPackage.java#L124

### POC

В директории cwe27_poc представлен пример программы, которая создает файл вне директории проекта, передавая путь в аргумент публичного конcтруктора класса из библиотеки.

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