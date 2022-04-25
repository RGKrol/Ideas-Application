package pl.j.rgk.ideas.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.j.rgk.ideas.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoryDao {

    private static final String PATH = "./categories.txt";

    private static Logger LOG = Logger.getLogger(CategoryDao.class.getName());

    private ObjectMapper objectMapper;  //służy do mapowania obiektu

    public CategoryDao() {
        this.objectMapper = new ObjectMapper();
    }

    private List<Category> getCategories() {
        try {
            return objectMapper.readValue(Files.readString(Paths.get(PATH)), new TypeReference<>() {
            });
        } catch (IOException e) {
            // e.printStackTrace(); //wyświetlenie błędu/wyjątku
            LOG.log(Level.WARNING, "Error on getCategories", e);    //tworzy loga na poziomie ostrzeżeń (warning) i wysyła dalej wyjątek
            return new ArrayList<>();
        }
    }


    public List<Category> findAll() {
        return getCategories();
    }

    public void add(Category category) {
        try {
            List<Category> categories = getCategories();

            //czy nazwa kategorii nie jest powtórzona?
            boolean repeat = false;
            for (Category cat : categories) {
                if (Objects.equals(cat.getName(), category.getName())) {
                    repeat = true;
                }
            }
            if (!repeat) {
                categories.add(category);
            } else {
                LOG.log(Level.INFO, "This category exists. (Ta kategoria już istnieje.)");
            }

            Files.writeString(Paths.get(PATH), objectMapper.writeValueAsString(categories));

        } catch (IOException e) {
            LOG.log(Level.WARNING, "Error on addCategory", e);
        }
    }

    public Optional<Category> findOne(String categoryName) {
        return getCategories().stream()         //korzystamy z metody na "strumieniu"
                .filter(c -> c.getName().equals(categoryName))
                //filter() -> działa tak, że dostajemy po kolei wszystkie obiekty z listy i możemy je odfiltrować
                //tak aby zawierała tylko pasujące kategorie
                .findAny();     //nam potrzeba dowolny z wyników
    }
}
