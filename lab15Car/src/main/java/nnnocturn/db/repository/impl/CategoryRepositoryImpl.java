package nnnocturn.db.repository.impl;

import org.apache.log4j.Logger;
import nnnocturn.db.DBManager;
import nnnocturn.db.dao.BrandDAORepository;
import nnnocturn.db.dao.CategoryDAORepository;
import nnnocturn.db.entity.Category;
import nnnocturn.db.repository.CategoryRepository;
import nnnocturn.exception.DBException;

import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final Logger LOGGER = Logger.getLogger(BrandRepositoryImpl.class);

    private final DBManager dbManager;

    private final CategoryDAORepository categoryDAORepository;

    public CategoryRepositoryImpl(DBManager dbManager, CategoryDAORepository categoryDAORepository) {
        this.dbManager = dbManager;
        this.categoryDAORepository = categoryDAORepository;
    }

    /**
     * @return list category
     */
    @Override
    public List<Category> getCategoryList() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with category was get");
                return categoryDAORepository.getCategoryList();
            } catch (DBException e) {
                LOGGER.error("Cannot get list category " + e);
            }
            return null;
        });
    }
}
