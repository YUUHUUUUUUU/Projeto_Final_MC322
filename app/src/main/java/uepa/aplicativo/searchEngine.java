package uepa.aplicativo;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class searchEngine {
  public static <T> Optional <T> searchByName(List<T> list, String name, Function<T, String> extractorName) {
    if (list == null || name == null || extractorName == null) {
      return Optional.empty();
    }
    String formattedName = name.trim();
    return list.stream().filter(objeto -> {
      String nomeObjeto = extractorName.apply(objeto);
    return nomeObjeto != null && nomeObjeto.equalsIgnoreCase(formattedName);
    })
    .findFirst();

  }

}