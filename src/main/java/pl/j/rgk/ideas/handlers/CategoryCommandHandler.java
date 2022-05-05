package pl.j.rgk.ideas.handlers;

import pl.j.rgk.ideas.dao.CategoryDao;
import pl.j.rgk.ideas.input.UserInputCommand;
import pl.j.rgk.ideas.model.Category;

import java.util.List;
import java.util.logging.Logger;

public class CategoryCommandHandler extends BaseCommandHandler {

    private static final String COMMAND_NAME = "category";

    private static final Logger LOG = Logger.getLogger(CategoryCommandHandler.class.getName());

    private final CategoryDao categoryDao;

    public CategoryCommandHandler() {
        categoryDao = new CategoryDao();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public void handle(UserInputCommand command) {

        if (command.getAction() == null) {
            throw new IllegalArgumentException("action can't be null (akcja musi być podana)");
        }

        switch (command.getAction()) {
            case LIST -> {
                LOG.info("List of category (Lista kategorii) ...");
                if (!command.getParam().isEmpty()) {
                    throw new IllegalArgumentException("'category list' doesn't support any additional params (polecenie 'category list' nie wspiera dodatkowych parametrów)");
                }
                List<Category> categories = categoryDao.findAll();
                categories.forEach(System.out::println);    //metoda forEach przeiteruje po categories i wykona na nich "println" z System.out
            }
            case ADD -> {
                LOG.info("Add category (Dodawanie kategorii) ...");
                if (command.getParam().size() != 1) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information (Zły format polecenia. Sprawdź help aby uzyskać więcej informacji.)");
                }
                String categoryName = command.getParam().get(0);
                categoryDao.add(new Category(categoryName));
            }
            case DEL -> {
                LOG.info("Delete category (Usuwanie kategorii) ...");
                if (command.getParam().size() != 1 ) {
                    throw new IllegalArgumentException("Wrong command format. Check help for more information (Zły format polecenia. Sprawdź help aby uzyskać więcej informacji.)");
                }
                String categoryName = command.getParam().get(0);
                categoryDao.del(new Category(categoryName));
            }
            default -> {
                throw new IllegalArgumentException(String.format("Unknown action: %s from command: %s (Nieznana akcja: %s dla komendy: %s)",
                        command.getAction(), command.getCommand(), command.getAction(), command.getCommand()));
            }
        }
    }
}
